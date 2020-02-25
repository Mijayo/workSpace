package modelo.DAOS;

import java.util.List;

import modelo.beans.Usuario;

public interface UsuarioDAO {

	public int insert(Usuario usuario);

	public int update(Usuario usuario);

	public int delete(String idUsuario);

	public Usuario findById(String idUsuario);

	public List<Usuario> findAll();
	
	// Esto se hace con JPQL
	public Usuario findLogin(String idUsuario, String pwd);
	
}
