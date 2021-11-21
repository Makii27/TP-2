package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import productos.Atraccion;
import usuario.Usuario;

public interface UserDAO<T>{

	public List<Usuario> findAll() throws SQLException;
	public Map<Integer, Atraccion> findAtraccionesAdquiridas(int idUsuario) throws SQLException;
	public int update(Usuario user) throws SQLException;
}
