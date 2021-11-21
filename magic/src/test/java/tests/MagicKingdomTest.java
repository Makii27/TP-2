package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import productos.Atraccion;
import productos.PromoAbsoluta;
import productos.Promocion;
import sistema.MagicKingdom;
import usuario.Usuario;

public class MagicKingdomTest {
	Map<Integer, Atraccion> atraccionesAdquiridas;
	Usuario usuario1;
	Atraccion atraccion1;
	Atraccion atraccion2;
	List<Atraccion> atraccionesPromo1;
	Promocion promo1;
	MagicKingdom sistema;
	
	@Before
	public void crearEscenario() {
		atraccionesAdquiridas= new HashMap<Integer, Atraccion>();
		usuario1 = new Usuario(1, "Mickey_Mouse", 50, 10, atraccionesAdquiridas);
		
		atraccion1 = new Atraccion(1, "diversion", 7, 3, 5);
		atraccion2 = new Atraccion(2, "aburrida", 10, 2, 5);
		
		
		atraccionesPromo1 = new ArrayList<Atraccion>();
		atraccionesPromo1.add(atraccion1);
		atraccionesPromo1.add(atraccion2);
		
		promo1 = new PromoAbsoluta(1, "promo1", atraccionesPromo1, 2);
		
		sistema = new MagicKingdom();
	}

	@Test
	public void fueAdquiridoTrue() {
		
		usuario1.setAtraccionesAdquiridas(promo1);
		
		assertTrue(sistema.fueAdquirido(usuario1, atraccion1));
	}
	@Test
	public void fueAdquiridoFalse() {
		Atraccion atraccion3 = new Atraccion(3, "anonima", 12, 3, 8);
		
		usuario1.setAtraccionesAdquiridas(promo1);
		
		assertFalse(sistema.fueAdquirido(usuario1, atraccion3));
	}
}
