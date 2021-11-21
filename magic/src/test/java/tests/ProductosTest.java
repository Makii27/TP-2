package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import productos.Atraccion;
import productos.Producto;
import productos.PromoAbsoluta;
import productos.PromoAxB;
import productos.Promocion;
import sistema.MagicKingdom;
import usuario.Usuario;

public class ProductosTest {
	
	@Before
	


	@Test
	public void setCupoTest() throws SQLException  {
		Map<Integer, Atraccion> atraccionesAdquiridas= new HashMap<Integer, Atraccion>();
		Usuario usuario1 = new Usuario(1, "Mickey_Mouse", 50, 10, atraccionesAdquiridas);
		
		Atraccion atraccion1 = new Atraccion(1, "diversion", 7, 3, 5);
		Atraccion atraccion2 = new Atraccion(2, "aburrida", 5, 1, 10);
		
		List<Atraccion> atraccionesPromo1 = new ArrayList();
		atraccionesPromo1.add(atraccion1);
		atraccionesPromo1.add(atraccion2);
		
		Promocion promo1 = new PromoAbsoluta(1, "promo1", atraccionesPromo1, 2);
		
		//usuario compra atraccion
		promo1.setCupo();
		
		//comprobar cupo en atracciones de la promo
		assertEquals(4, atraccion1.getCupo());
		assertEquals(9, atraccion2.getCupo());
		
		//comprobar que la promo devuelve el cupo menor
		assertEquals(4, promo1.getCupo());
	}
	

		
	/*	List<Usuario> usuarios = new ArrayList<Usuario>();	
		List<Producto> productosDisponibles = new ArrayList<Producto>();
		
		Usuario usuario1 = new Usuario(1, "usuario1", 50, 8);
		Usuario usuario2 = new Usuario(2, "usuario2", 40, 9);
		usuarios.add(usuario1);
		usuarios.add(usuario2); 
		
		// crear atracciones
		                                  // id, nombre, costo, tiempo, cupo
		Atraccion atraccion1 = new Atraccion(1, "diversion", 7, 3, 5);
		Atraccion atraccion2 = new Atraccion(2, "aburrida", 5, 1, 10);
		Atraccion atraccion3 = new Atraccion(3, "lenta", 9, 2, 20);
		Atraccion atraccion4 = new Atraccion(4, "confusa", 3, 2, 5);
		Atraccion atraccion5 = new Atraccion(5, "locura", 10, 3, 6);
		Atraccion atraccion6 = new Atraccion(6, "mimir", 8, 1, 15);
		
		// crear promos
		List<Atraccion> atraccionesPromo1 = new ArrayList();
		atraccionesPromo1.add(atraccion1);
		atraccionesPromo1.add(atraccion2);
		
		List<Atraccion> atraccionesPromo2 = new ArrayList();
		atraccionesPromo2.add(atraccion2);
		atraccionesPromo2.add(atraccion5);

		Promocion promo1 = new PromoAbsoluta(1, "promo1", atraccionesPromo1, 2);
		Promocion promo2 = new PromoAxB(2, "promo2", atraccionesPromo2);
		
		// crear lista de productos disponibles
		productosDisponibles.add(promo1);
		productosDisponibles.add(promo2);
		productosDisponibles.add(atraccion1);
		productosDisponibles.add(atraccion2);
		productosDisponibles.add(atraccion3);
		productosDisponibles.add(atraccion4);
		productosDisponibles.add(atraccion5);
		productosDisponibles.add(atraccion6);		
		
		
		MagicKingdom turismo = new MagicKingdom();
		turismo.inicio(usuarios, productosDisponibles); 
		
		System.out.println("cupo atraccion1: " + atraccion1.getCupo());
		System.out.println("cupo atraccion2: " + atraccion2.getCupo());
		System.out.println("dinero usuario: " + usuario1.getDinero()); 

		fail("Not yet implemented"); */
	

}
