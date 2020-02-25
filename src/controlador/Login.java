package controlador;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.DAOS.PedidoDAOImpl;
import modelo.DAOS.UsuarioDAOImpl;
import modelo.beans.Libro;
import modelo.beans.LineaPedido;
import modelo.beans.LineaPedidoPK;
import modelo.beans.Pedido;
import modelo.beans.Usuario;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HashMap<Libro, Integer> cantidadLibros = (HashMap<Libro, Integer>) request.getSession().getAttribute("cantidad");

		HttpSession sesion = request.getSession();

		UsuarioDAOImpl udao = new UsuarioDAOImpl();
		List<LineaPedido> pe = null;
		PedidoDAOImpl ped = new PedidoDAOImpl();
		Usuario usu = null;

		switch (request.getParameter("option")) {

		case "validar":

			usu = udao.findLogin(request.getParameter("usuario"), request.getParameter("pwd"));

			if (usu != null) {

				System.out.println("usuario aqui");
				System.out.println(usu.getIdUsuario());

				pe = ped.findEstadoCarrito(usu);

				if (pe != null) {

					if (cantidadLibros == null) {
						cantidadLibros = new HashMap<Libro, Integer>();
					}

					for (LineaPedido peds : pe) {

						cantidadLibros.put(peds.getLibro(), Integer.valueOf(peds.getCantidad()));

					}

					request.getSession().setAttribute("usuario", usu);
					request.getSession().setAttribute("cantidad", cantidadLibros);
					request.getRequestDispatcher("verCarritoUsu.jsp").forward(request, response);

				} else {

					request.getRequestDispatcher("index.jsp").forward(request, response);
				}

			} else {

				request.setAttribute("mensaje_error", "** Usuario no existe");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}

			break;

		case "salir":

			usu = udao.findLogin(request.getParameter("usuario"), request.getParameter("pwd"));

			if (usu != null) {

				request.getSession().invalidate();
				request.getRequestDispatcher("index.jsp").forward(request, response);

			} else {

				Pedido pedido = new Pedido();
				
				pedido.setDireccionEntrega(((Usuario) sesion.getAttribute("usuario")).getDireccion());
				pedido.setEstado("carrito");
				
				// System.out.println(pedido.getEstado());
				
				pedido.setFechaAlta(new Date());
				pedido.setUsuario((Usuario) sesion.getAttribute("usuario"));

				HashMap<Libro, Integer> mapa = (HashMap<Libro, Integer>) sesion.getAttribute("cantidad");

				for (Libro libro : mapa.keySet()) {

					LineaPedidoPK lpk = new LineaPedidoPK();
					lpk.setIsbn(libro.getIsbn());

					LineaPedido lp = new LineaPedido();
					lp.setId(lpk);

					////

					lp.setCantidad(mapa.get(libro));

					/////

					lp.setFechaAlta(new Date());
					lp.setLibro(libro);
					lp.setPrecioVenta(libro.getPrecioUnitario());
					lp.setPedido(pedido);
					pedido.addLineaPedido(lp);
					// ACTUALIZA EL PEDIDO EN BBDD
					new PedidoDAOImpl().insert(pedido);
				}
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}

			break;

		case "cerrarSesion":

			request.getSession().getAttribute("usuario");
			request.getSession().removeAttribute("usuario");

			request.getRequestDispatcher("index.jsp").forward(request, response);

			break;

		}

	}

}