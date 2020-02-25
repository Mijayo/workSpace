package modelo.DAOS;

import java.util.List;

import modelo.beans.Usuario;

public class RegistroDAOImpl extends AbstractDAOImpl<Usuario, String> {

	@Override
	public int insert(Usuario entidad) {
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
	public int update(Usuario entidad) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String clave) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Usuario findById(String clave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario findEstado(String clave) {
		// TODO Auto-generated method stub
		return null;
	}

}
