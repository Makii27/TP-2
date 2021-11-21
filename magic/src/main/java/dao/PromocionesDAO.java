package dao;

import java.sql.SQLException;
import java.util.List;

import productos.Atraccion;
import productos.Promocion;

public interface PromocionesDAO<T>{
	
	public List<Promocion> findAll() throws SQLException;
	public List<Atraccion> atraccionesEnPromo(int idPromo) throws SQLException;
}
