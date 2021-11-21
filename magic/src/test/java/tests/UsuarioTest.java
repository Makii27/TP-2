package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import productos.Atraccion;
import productos.Producto;
import productos.PromoAbsoluta;
import productos.Promocion;
import usuario.Usuario;

public class UsuarioTest {
	Usuario usuario1;
	Atraccion atraccion1;
	@Before 
		public void crearUsuarioYatraccion() {
		Map<Integer, Atraccion> atraccionesAdquiridas= new HashMap<Integer, Atraccion>();
		usuario1 = new Usuario(1, "Mickey_Mouse", 50, 10, atraccionesAdquiridas);
		
		atraccion1 = new Atraccion(1, "diversion", 7, 3, 5);
	}	
	@Test
	public void setDineroTest() {
		
		usuario1.setDinero(atraccion1);
		
		assertEquals(43, usuario1.getDinero(), 0);			
	}
	@Test
	public void setTiempoTest() {
		usuario1.setTiempo(atraccion1);
		
		assertEquals(7, usuario1.getTiempo(), 0);
	}
	@Test
	public void setProductosAdquiridosTest() {
	
		List<Producto> productosEsperados = new ArrayList<Producto>();
		productosEsperados.add(atraccion1);
		
		usuario1.setProductosAdquiridos(atraccion1);
		
		assertEquals(productosEsperados, usuario1.getProductosAdquiridos());
	}
	@Test
	public void setAtraccionesAdquiridasTest() {
		Atraccion atraccion2 = new Atraccion(2, "aburrida", 10, 2, 5);
		Map<Integer, Atraccion> atraccionesEsperadas = new HashMap<Integer, Atraccion>();
		atraccionesEsperadas.put(atraccion1.getId(), atraccion1);
		atraccionesEsperadas.put(atraccion2.getId(), atraccion2);
		
		
		List<Atraccion> atraccionesPromo1 = new ArrayList<Atraccion>();
		atraccionesPromo1.add(atraccion1);
		atraccionesPromo1.add(atraccion2);
		Promocion promo1 = new PromoAbsoluta(1, "promo1", atraccionesPromo1, 2);
		
		usuario1.setAtraccionesAdquiridas(promo1);
		
		assertEquals(atraccionesEsperadas, usuario1.getAtraccionesAdquiridas());
	}
}
