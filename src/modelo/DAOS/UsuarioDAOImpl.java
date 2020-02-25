package modelo.DAOS;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.beans.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction tx;
	private String sql;
	private Query query;

	public UsuarioDAOImpl() {
		emf = Persistence.createEntityManagerFactory("04_Libreria2.0");
		em = emf.createEntityManager();
		tx = em.getTransaction();
	}

	@Override
	public int insert(Usuario usuario) {
		// TODO Auto-generated method stub

		Usuario usu = null;

		try {
			usu = em.find(Usuario.class, usuario.getIdUsuario());
			if (usu != null) {
				tx.begin();
				em.persist(usuario);
				tx.commit();
				return 1;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;

	}

	@Override
	public int update(Usuario usuario) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String idUsuario) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Usuario findById(String idUsuario) {
		// TODO Auto-generated method stub
		return em.find(Usuario.class, idUsuario);
	}

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario findLogin(String idUsuario, String pwd) {
		// TODO Auto-generated method stub
		sql = "select u from Usuario u where u.idUsuario = :usu and u.password = :pwd";

		try {
			query = em.createQuery(sql);
			query.setParameter("usu", idUsuario);
			query.setParameter("pwd", pwd);
			return (Usuario) query.getSingleResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;

	}

}
