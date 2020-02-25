package modelo.DAOS;

import java.util.List;

import modelo.beans.Libro;

public class LibroDAOImpl extends AbstractDAOImpl<Libro, Long> {

	@Override
	public int insert(Libro entidad) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Libro entidad) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Long clave) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Libro findById(Long clave) {

		sql = "select l from Libro l where l.isbn = :lib";
		query = em.createQuery(sql);
		query.setParameter("lib", clave);
		try {
			return (Libro) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public List<Libro> findAll() {
		query = em.createNamedQuery("Libro.findAll");

		return query.getResultList();
	}

	public List<Libro> findLibrosPorTemas(int idTema) {
		sql = "select l from Libro l where l.tema.idTema = :id";
		query = em.createQuery(sql);
		query.setParameter("id", idTema);

		return query.getResultList();
	}

	@Override
	public Libro findEstado(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
