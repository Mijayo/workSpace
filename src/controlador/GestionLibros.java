package controlador;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.DAOS.LibroDAOImpl;
import modelo.DAOS.TemaDAO;
import modelo.DAOS.TemaDAOImpl;
import modelo.beans.Libro;

/**
 * Servlet implementation class GestionLibros
 */
@WebServlet("/GestionLibros")
public class GestionLibros extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GestionLibros() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession misesion = request.getSession();

		HttpSession sesionCantidad = request.getSession();

		TemaDAO tdao = new TemaDAOImpl();

		LibroDAOImpl ldao = new LibroDAOImpl();

		HashMap<Libro, Integer> cantidadLibros = null;

		switch (request.getParameter("option")) {

		case "temas":

			request.setAttribute("temas", tdao.findAll());
			request.getRequestDispatcher("verTemas.jsp").forward(request, response);
			System.out.println(tdao.findAll());

			break;

		case "libros":

			List<Libro> lista = ldao.findAll();
			request.setAttribute("libros", lista);
			request.getRequestDispatcher("verLibros.jsp").forward(request, response);

			break;

		case "addLibro":

			if (request.getParameterValues("isbn") != null) {
				cantidadLibros = (HashMap<Libro, Integer>) misesion.getAttribute("cantidad");

				if (cantidadLibros == null) {
					cantidadLibros = new HashMap<Libro, Integer>();
				}

				for (String ele : request.getParameterValues("isbn")) {

					Libro libro = new Libro();

					libro = ldao.findById(Long.valueOf(ele));

					// HashMap con la cantidad
					cantidadLibros.put(libro, Integer.valueOf(request.getParameter("cantidad" + ele)));

					System.out.println("dentro");
					System.out.println(cantidadLibros);
					System.out.println(cantidadLibros.values());

				}
				sesionCantidad.setAttribute("cantidad", cantidadLibros);
				request.setAttribute("mensaje_carrito", "** Libros añadidos al carrito");
				request.getRequestDispatcher("verCarrito.jsp").forward(request, response);
			}
			

			break;

		case "cerrar":

			System.out.println("aqui");

			misesion.removeAttribute("cesta");
			misesion.removeAttribute("usuario");
			misesion.invalidate();

			request.getRequestDispatcher("index.jsp").forward(request, response);

			break;

		default:
			System.out.println("Opcion erronea");
		}

	}

}
