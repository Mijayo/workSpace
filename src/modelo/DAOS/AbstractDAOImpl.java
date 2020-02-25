package modelo.DAOS;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public abstract class AbstractDAOImpl<E, K> implements CrudGenerico<E, K> {
	
	protected EntityManagerFactory emf;
	protected EntityManager em;
	protected EntityTransaction tx;
	protected String sql;
	protected Query query;
	
	public AbstractDAOImpl() {
		emf = Persistence.createEntityManagerFactory("04_Libreria2.0");
		em = emf.createEntityManager();
		tx = em.getTransaction();
	}

	@Override
	public abstract int insert(E entidad);
	
	@Override
	public abstract int update(E entidad);

	@Override
	public abstract int delete(K clave);

	@Override
	public abstract E findById(K clave);

	@Override
	public abstract List<E> findAll();
	
	
}
