package dao;

import java.sql.SQLException;
import java.util.Map;

import productos.Atraccion;

public interface AtraccionesDAO<T> {
	
	public Map<Integer, Atraccion> findAll() throws SQLException;
	public int update(Atraccion atraccion) throws SQLException;
}
