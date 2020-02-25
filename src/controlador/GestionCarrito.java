package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.DAOS.LibroDAOImpl;
import modelo.DAOS.PedidoDAOImpl;
import modelo.DAOS.RegistroDAOImpl;
import modelo.DAOS.UsuarioDAOImpl;
import modelo.beans.Libro;
import modelo.beans.LineaPedido;
import modelo.beans.LineaPedidoPK;
import modelo.beans.Pedido;
import modelo.beans.Usuario;

/**
 * Servlet implementation class GestionCarrito
 */
@WebServlet("/GestionCarrito")
public class GestionCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GestionCarrito() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sesion = request.getSession();
		HttpSession sesionCantidad = request.getSession();
		
		HashMap<Libro, Integer> cantidadLibros = (HashMap<Libro, Integer>) request.getSession().getAttribute("cantidad");
		PedidoDAOImpl ped = new PedidoDAOImpl();
		

		// HASHMAP

		Usuario usu = null;

		usu = (Usuario) request.getSession().getAttribute("usuario");

		switch (request.getParameter("option")) {

		case "carrito":

			if (usu != null) {

				request.getRequestDispatcher("verCarritoUsu.jsp").forward(request, response);
			}

			request.getRequestDispatcher("verCarritoUsu.jsp").forward(request, response);

			break;

		case "vaciar":

			((ArrayList<Libro>) request.getAttribute("carrito")).clear();

			request.getRequestDispatcher("verCarrito.jsp").forward(request, response);

			break;

		case "eliminar":
			
			LibroDAOImpl ldao = new LibroDAOImpl();
			
			System.out.println(ldao.findById( (long) Integer.valueOf(request.getParameter("isbn"))));

			System.out.println(Integer.parseInt(request.getParameter("isbn")));
			
			ped.delete(Integer.parseInt(request.getParameter("isbn")));
			
			cantidadLibros.remove(ped);
			
			request.getSession().setAttribute("cantidad", cantidadLibros);
			request.getRequestDispatcher("verCarrito.jsp").forward(request, response);
			
			break;

		case "comprar":

			if (usu != null) {

				// HASHMAP
				// System.out.println(sesionCantidad.getAttribute("cantidad"));

				Pedido pedido = new Pedido();
				pedido.setDireccionEntrega(((Usuario) sesion.getAttribute("usuario")).getDireccion());
				pedido.setEstado("terminado");
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
					new PedidoDAOImpl().update(pedido);
				}

			} else {
				// request.setAttribute("mensaje_error", "** Usuario no existe");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}

			request.getRequestDispatcher("GestionLibros?option=cerrar").forward(request, response);

			break;

		case "salir":

			if (usu != null) {

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

				request.getSession().invalidate();
				request.getRequestDispatcher("index.jsp").forward(request, response);

			} else {
				request.getSession().invalidate();
				request.getRequestDispatcher("index.jsp").forward(request, response);

			}

			break;

		default:
			request.getRequestDispatcher("verCarrito.jsp").forward(request, response);
			System.out.println("por defecto");
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
