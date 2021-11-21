package dao;

import java.sql.SQLException;

import usuario.Usuario;

public interface ItinerarioDAO<T>{
	public int insert(Usuario usuario) throws SQLException;
	public int agregarProductosComprados(Usuario user) throws SQLException;
}
