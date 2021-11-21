package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jdbc.ConnectionProvider;
import productos.Producto;
import productos.Promocion;
import usuario.Usuario;

public class ItinerarioDAOImpl implements ItinerarioDAO<Object>{
	int id_itinerario;

	public int insert(Usuario usuario) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();

		String sql = "INSERT INTO itinerarios (ID_usuario) VALUES (?)";
		PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		statement.setInt(1, usuario.getId());

		int affectedRows = statement.executeUpdate();

		try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				id_itinerario = generatedKeys.getInt(1);
			} else {
				throw new SQLException("Error al obtener el ID del itinerario correspondiente al usuario");
			}
		}

		agregarProductosComprados(usuario);
		return affectedRows;
	}

	public int agregarProductosComprados(Usuario user) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();
		int affectedRows = 0;

		for (Producto producto : user.getProductosAdquiridos()) {
			String sql;
			if (producto instanceof Promocion) {
				 sql = "INSERT INTO itinerarios_detalle (ID_itinerario, ID_promociones) VALUES (?, ?)";
			} else {
				 sql = "INSERT INTO itinerarios_detalle (ID_itinerario, ID_atracciones) VALUES (?, ?)";
			}
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id_itinerario);
			statement.setInt(2, producto.getId());

			affectedRows += statement.executeUpdate(); 
		}

		return affectedRows;
	}

}
