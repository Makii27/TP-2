package tests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import dao.UserDAOImpl;
import productos.Atraccion;
import usuario.Usuario;

public class UserDAOtest {

	@Test
	public void findAllTest() throws SQLException {
		List<Usuario> usuariosEsperados = new ArrayList<Usuario>();
		Map<Integer, Atraccion> atraccionesAdquiridas= new HashMap<Integer, Atraccion>();
		
		usuariosEsperados.add(new Usuario(1, "Mickey_Mouse", 50, 10, atraccionesAdquiridas));
		usuariosEsperados.add(new Usuario(2, "Minnie_Mouse", 45, 5.5, atraccionesAdquiridas));
		usuariosEsperados.add(new Usuario(3, "Pato_Donald", 20, 7, atraccionesAdquiridas));
		usuariosEsperados.add(new Usuario(4, "Goofy", 48, 15, atraccionesAdquiridas));
		usuariosEsperados.add(new Usuario(5, "Blancanieves", 33.5, 8, atraccionesAdquiridas));
		usuariosEsperados.add(new Usuario(6, "Pinocho", 30, 8, atraccionesAdquiridas));
		usuariosEsperados.add(new Usuario(7, "Peter_Pan", 50, 9, atraccionesAdquiridas));
		
		//Lista de usuarios obtenida de BD:
		UserDAOImpl userDAOImpl = new UserDAOImpl();
		List<Usuario> usuariosObtenidos = userDAOImpl.findAll();
		
		assertEquals(usuariosEsperados.get(1), usuariosObtenidos.get(1));
		
		//esto no nos funciona y no sabemos porque si los elementos de las listas son iguales :(
		
	}

}
