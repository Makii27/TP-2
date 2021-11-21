package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jdbc.ConnectionProvider;
import productos.Atraccion;
import usuario.Usuario;

public class UserDAOImpl implements UserDAO<Usuario>{

	public List<Usuario> findAll() throws SQLException {

		Connection connection = ConnectionProvider.getConnection();

		String sql = "SELECT * FROM usuarios";                          // preparo la
		PreparedStatement statement = connection.prepareStatement(sql); // sentencia

		ResultSet result = statement.executeQuery(); // ejecuto la consulta

		List<Usuario> usuarios = new ArrayList<Usuario>(); // creo la lista donde voy a guardar los usuarios obtenidos

		while (result.next()) {
			usuarios.add(toUser(result));
		}

		return usuarios;
	}

	private Usuario toUser(ResultSet result) throws SQLException {
		int id = result.getInt("ID_usuario");
		String nombre = result.getString("nombre");
		double dinero = result.getDouble("dinero");
		double tiempo = result.getDouble("tiempo");

		return new Usuario(id, nombre, dinero, tiempo, findAtraccionesAdquiridas(id));
	}

	public Map<Integer, Atraccion> findAtraccionesAdquiridas(int idUsuario) throws SQLException {
		Connection connection = ConnectionProvider.getConnection(); 
		
		String sql = "SELECT \r\n"
				+ "    atracciones.*\r\n"
				+ "FROM\r\n"
				+ "    itinerarios,\r\n"
				+ "    itinerarios_detalle,\r\n"
				+ "    promociones,\r\n"
				+ "    atracciones,\r\n"
				+ "    promociones_atracciones \r\n"
				+ "WHERE\r\n"
				+ "    itinerarios.ID_itinerario = itinerarios_detalle.ID_itinerario\r\n"
				+ "        AND itinerarios_detalle.ID_promociones = promociones.ID_promociones\r\n"
				+ "        AND promociones_atracciones.ID_promociones = promociones.ID_promociones\r\n"
				+ "        AND atracciones.ID_atracciones = promociones_atracciones.ID_atracciones\r\n"
				+ "        AND itinerarios.ID_usuario = ? \r\n"
				+ "UNION SELECT \r\n"
				+ "    atracciones.*\r\n"
				+ "FROM\r\n"
				+ "    itinerarios,\r\n"
				+ "    itinerarios_detalle,\r\n"
				+ "    atracciones \r\n"
				+ "WHERE\r\n"
				+ "    itinerarios.ID_itinerario = itinerarios_detalle.ID_itinerario\r\n"
				+ "        AND itinerarios_detalle.ID_atracciones = atracciones.ID_atracciones\r\n"
				+ "        AND itinerarios.ID_usuario = ?; ";                         
		PreparedStatement statement = connection.prepareStatement(sql); 
		statement.setInt(1, idUsuario);
		statement.setInt(2, idUsuario);
		ResultSet result = statement.executeQuery();   
		
		Map<Integer, Atraccion> atraccionesAdquiridas = new HashMap<Integer, Atraccion>();   
		
		while (result.next()) {
			atraccionesAdquiridas.put(toAtraccion(result).getId(), toAtraccion(result));			
		}
					
		return atraccionesAdquiridas ;
	}

	private Atraccion toAtraccion(ResultSet result) throws SQLException {
		int id = result.getInt("ID_atracciones");
		String nombre = result.getString("nombre");
		double costo = result.getDouble("costo");
		double tiempo = result.getDouble("tiempo");
		int cupo = result.getInt("cupo");
		
		return new Atraccion(id, nombre, costo, tiempo, cupo);
	}	

	public int update(Usuario user) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();

		String sql = "UPDATE usuarios SET dinero = ?, tiempo = ? WHERE ID_usuario = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setDouble(1, user.getDinero());
		statement.setDouble(2, user.getTiempo());
		statement.setInt(3, user.getId());

		int affectedRows = statement.executeUpdate();
		return affectedRows;
	}
	
}
