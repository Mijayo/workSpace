package modelo.DAOS;

import java.util.List;

import modelo.beans.Tema;

public interface TemaDAO {

	public int insert(Tema tema);

	public int update(Tema tema);

	public int delete(String idTema);

	public Tema findById(String idTema);

	public List<Tema> findAll();

}
