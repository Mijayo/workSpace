package modelo.DAOS;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import modelo.beans.Libro;
import modelo.beans.LineaPedido;
import modelo.beans.Pedido;
import modelo.beans.Usuario;

public class PedidoDAOImpl extends AbstractDAOImpl<Pedido, Integer> {

	List<LineaPedido> lpedido = null;
	List<Pedido> pedido = null;

	@Override
	public int insert(Pedido entidad) {
		try {
			tx.begin();
			em.persist(entidad);
			tx.commit();
			return 1;
		} catch (Exception e) {
			return 0;
		}

	}

	@Override
	public int update(Pedido entidad) {
		try {
			tx.begin();
			em.merge(entidad);
			tx.commit();
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int delete(Integer clave) {

		// DELETE FROM `libreria`.`linea_pedidos` WHERE `ID_PEDIDO`='1'
		// and`ISBN`='10002';

		LibroDAOImpl ldao = new LibroDAOImpl();
		Libro libro = null;

		libro = ldao.findById((long) Integer.valueOf(clave));
		System.out.println(libro);

		sql = "delete from LineaPedido lp where lp.libro.isbn = :isbn";
int i = 0;
		try {
			
			query = em.createQuery(sql);
			query.setParameter("isbn", libro.getIsbn());
			System.out.println(query.getSingleResult());
			return query.executeUpdate();

			// query.getSingleResult();

		

			

		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}

		/*
		 * try { tx.begin(); em.remove(clave); tx.commit(); return 1; } catch (Exception
		 * e) { return 0; }
		 */

	}

	@Override
	public Pedido findById(Integer clave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pedido> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<LineaPedido> findEstadoCarrito(Usuario usuario) {

		sql = "select p from Pedido p where p.usuario.idUsuario = :usu and p.estado = 'carrito'";

		try {

			query = em.createQuery(sql);

			query.setParameter("usu", usuario.getIdUsuario());

			System.out.println(query.getResultList());

			pedido = new ArrayList<Pedido>();

			pedido = query.getResultList();

			sql = "SELECT l FROM LineaPedido l where l.pedido.idPedido=:usu";

			query = em.createQuery(sql);

			lpedido = new ArrayList<LineaPedido>();

			for (Pedido pe : pedido) {

				query.setParameter("usu", ((Pedido) pe).getIdPedido());

				List<LineaPedido> listaDeLineasDePedidos = (List<LineaPedido>) query.getResultList();
				for (LineaPedido lineaPedido : listaDeLineasDePedidos) {
					lpedido.add(lineaPedido);
				}
			}

			return lpedido;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;

	}

	@Override
	public Pedido findEstado(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
