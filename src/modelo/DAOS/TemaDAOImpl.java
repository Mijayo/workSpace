package modelo.DAOS;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.beans.Tema;

public class TemaDAOImpl implements TemaDAO {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction tx;
	private String sql;
	private Query query;
	
	public TemaDAOImpl() {
		emf = Persistence.createEntityManagerFactory("04_Libreria2.0");
		em = emf.createEntityManager();
		tx = em.getTransaction();
	}

	@Override
	public int insert(Tema tema) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Tema tema) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String idTema) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Tema findById(String idTema) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tema> findAll() {
	
		query = em.createNamedQuery("Tema.findAll");
		
		return query.getResultList();
		
	}
}
