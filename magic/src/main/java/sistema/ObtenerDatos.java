package sistema;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import dao.AtraccionesDAOImpl;
import dao.PromocionesDAOImpl;
import dao.UserDAOImpl;
import productos.Atraccion;
import productos.Producto;
import productos.Promocion;
import usuario.Usuario;

public class ObtenerDatos {

	public List<Usuario> usuarios() throws SQLException {
		UserDAOImpl dao = new UserDAOImpl();
		List<Usuario> listaUsuarios = dao.findAll();

		return listaUsuarios;
	}

	public List<Producto> productos() throws SQLException {
//     OBTENER ATRACCIONES:
		AtraccionesDAOImpl atraccionesDao = new AtraccionesDAOImpl();
		Map<Integer, Atraccion> atracciones = atraccionesDao.findAll();

//	   OBTENER PROMOCIONES:
		PromocionesDAOImpl promocionesDao = new PromocionesDAOImpl(atracciones);
		List<Promocion> promociones = promocionesDao.findAll();


//	   AGREGAR PROMOCIONES Y ATRACCIONES A LISTA PRODUCTOS para que trabaje el sistema
		List<Producto> productosOfertables = new ArrayList<Producto>();
		productosOfertables.addAll(promociones);
		
		for (int clave : atracciones.keySet()) {
			productosOfertables.add(atracciones.get(clave));
		}
		
//    	ORDENAR LA COLECCION POR PRECIO Y TIEMPO REQUERIDO		
		Collections.sort(productosOfertables,
				Comparator.comparing(Producto::getCosto).thenComparing(Producto::getTiempo));

		Collections.reverse(productosOfertables);

		return productosOfertables;
	}

}
