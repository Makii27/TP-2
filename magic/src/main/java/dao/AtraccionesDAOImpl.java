package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import jdbc.ConnectionProvider;
import productos.Atraccion;


public class AtraccionesDAOImpl implements AtraccionesDAO<Atraccion> {

public Map<Integer, Atraccion> findAll() throws SQLException {
		
		Connection connection = ConnectionProvider.getConnection(); 
		
		String sql = "SELECT * FROM atracciones";                         // preparo la
		PreparedStatement statement = connection.prepareStatement(sql);  //  sentencia
		
		ResultSet result = statement.executeQuery();   // ejecuto la consulta
		
		Map<Integer, Atraccion> atracciones = new HashMap<Integer, Atraccion>();   // creo la lista donde voy a guardar los usuarios obtenidos
		
		while (result.next()) {
			atracciones.put(toAtraccion(result).getId(), toAtraccion(result));			
		}
					
		return atracciones;
	}

	private Atraccion toAtraccion(ResultSet result) throws SQLException {
		int id = result.getInt("ID_atracciones");
		String nombre = result.getString("nombre");
		double costo = result.getDouble("costo");
		double tiempo = result.getDouble("tiempo");
		int cupo = result.getInt("cupo");
		
		return new Atraccion(id, nombre, costo, tiempo, cupo);
	}
	
	public int update(Atraccion atraccion) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();

		String sql = "UPDATE atracciones SET cupo = ? WHERE ID_atracciones = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setDouble(1, atraccion.getCupo());
		statement.setDouble(2, atraccion.getId());

		int affectedRows = statement.executeUpdate();
		return affectedRows;
	}
}
