package sistema;

import java.sql.SQLException;

import dao.AtraccionesDAOImpl;
import dao.ItinerarioDAOImpl;
import dao.UserDAOImpl;
import usuario.Usuario;

public class EscribirDatos {
	Usuario usuario;

	public EscribirDatos(Usuario usuario) {
		this.usuario = usuario;
	}

	// actualiza usuario 
	public void updateUsuario() throws SQLException {
		
		UserDAOImpl userDAOImpl = new UserDAOImpl();
		userDAOImpl.update(usuario);
	}
	
	// actualiza atracciones compradas
	public void updateAtracciones() throws SQLException {
		AtraccionesDAOImpl atraccionesDao = new AtraccionesDAOImpl();
		
		usuario.getAtraccionesAdquiridas().forEach((k, v) -> {
			try {
				atraccionesDao.update(v);
			} catch (SQLException e) {
				e.printStackTrace();
			}
					
		} );
	}
	
	public void escribirItinerario(Usuario usuario) throws SQLException {
		ItinerarioDAOImpl itinerarioDao = new ItinerarioDAOImpl();
		itinerarioDao.insert(usuario);
	}	
	
}
