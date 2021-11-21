package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import productos.Atraccion;
import productos.PromoAbsoluta;
import productos.PromoAxB;
import productos.PromoPorcentual;
import productos.Promocion;
import jdbc.ConnectionProvider;


public class PromocionesDAOImpl implements PromocionesDAO<Promocion>{
	Map<Integer, Atraccion> atracciones;
	
	public PromocionesDAOImpl(Map<Integer, Atraccion> atracciones){
		this.atracciones = atracciones;
	}
	
// obtener lista de todas las promociones	
	public List<Promocion> findAll() throws SQLException {
		
		Connection connection = ConnectionProvider.getConnection(); 
		
		String sql = "SELECT * FROM promociones";                         // preparo la
		PreparedStatement statement = connection.prepareStatement(sql);  //  sentencia
		
		ResultSet result = statement.executeQuery();   // ejecuto la consulta
		
		List<Promocion> promociones = new ArrayList<Promocion>();   // creo la lista donde voy a guardar los usuarios obtenidos
		
		while (result.next()) {
			promociones.add(toPromo(result));			
		}
					
		return promociones ;
	}

// obtener promocion para agregar a la lista	
	private Promocion toPromo(ResultSet result) throws SQLException {
		int id = result.getInt("ID_promociones");
		String tipo = result.getString("tipo");
		String nombre = result.getString("nombre");
		double descuento = result.getDouble("descuento");
		
		if (0 == (tipo.compareTo("absoluta"))) {
			return new PromoAbsoluta(id, nombre, atraccionesEnPromo(id), descuento);
		}  else if (0 == (tipo.compareTo("porcentual"))) {
			return new PromoPorcentual(id, nombre, atraccionesEnPromo(id), descuento);
		} else {
			return new PromoAxB(id, nombre, atraccionesEnPromo(id));
		} 
				
	}
	
//  obtener lista de atracciones de una promo en particular
	public List<Atraccion> atraccionesEnPromo(int idPromo) throws SQLException {
		Connection connection = ConnectionProvider.getConnection(); 
		
		String sql = "SELECT ID_atracciones FROM promociones_atracciones WHERE promociones_atracciones.ID_promociones = ?";                         
		PreparedStatement statement = connection.prepareStatement(sql);  
		statement.setInt(1, idPromo);
		
		ResultSet result = statement.executeQuery();   
		
		List<Atraccion> atraccionesEnPromo = new ArrayList<Atraccion>();   
		
		while (result.next()) {
			atraccionesEnPromo.add(toAtraccionesEnPromo(result));			
		}
					
		return atraccionesEnPromo ;
	}
	
//	obtener cada atraccion para la lista que compone a una promocion
	private Atraccion toAtraccionesEnPromo(ResultSet result) throws SQLException {
		int idAtraccion = result.getInt("ID_atracciones");
	
		return atracciones.get(idAtraccion);
	
	}
}
