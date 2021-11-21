package sistema;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import jdbc.ConnectionProvider;
import productos.Atraccion;
import productos.Producto;
import usuario.Usuario;


public class MagicKingdom {
	private List<Usuario> usuarios;	
	private List<Producto> productosDisponibles;
	
	public  void inicio(List<Usuario> usuarios, List<Producto> productosDisponibles) throws IOException, SQLException {
		this.usuarios = usuarios;
		this.productosDisponibles = productosDisponibles;

		System.out.println("Sistema de Ventas Turistico Magic Kingdom");
		
		for (Usuario usuario : usuarios) {
	
			System.out.println("Bienvenido: " + usuario.getNombre());
			ofrecerProductos(usuario);			
			escribirItinerario(usuario);
			System.out.println("Muchas gracias por su compra!");
			System.out.println("---------------------------------------------------------------------------");			
		}
		
		cerrarBaseDeDatos();
	}
	

	public void ofrecerProductos(Usuario usuario) {
	
		Scanner teclado = new Scanner(System.in);
		String decision = "";		 
		
		for (Producto producto : productosDisponibles) {
			 if ( producto.getCosto() <= usuario.getDinero() && producto.getTiempo() <= usuario.getTiempo()
					 && producto.getCupo() > 0 && !fueAdquirido(usuario, producto)) {
					 // si el costo es <= dinero usuario Y tiempo <= tiempo usuario Y cupo > 0 Y no fue adquirido) 

				System.out.println(producto.toString() + "\r\n Desea comprar este producto?" );

				do {
					System.out.println("Presione A para Aceptar o R para Rechazar");
					decision = teclado.nextLine(); // guarda la respuesta de usuario
					decision = decision.toUpperCase();
				} while (0 != decision.compareTo("A") && 0 != decision.compareTo("R")); 
				
				if (0 == decision.compareTo("A"))  {
					// resta el cupo al producto obtenido 
					producto.setCupo();
					// agrega producto adquirido al usuario
					usuario.setProductosAdquiridos(producto);
					usuario.setAtraccionesAdquiridas(producto);
					// resta el dinero y tiempo al usuario
					usuario.setDinero(producto);
					usuario.setTiempo(producto);
					
					}
				}
			}
		}	
	
		public boolean fueAdquirido(Usuario usuario, Producto productoAofrecer) { 
			
			for(Atraccion atraccion : productoAofrecer.getAtracciones()) {
				if (usuario.getAtraccionesAdquiridas().containsKey(atraccion.getId())) {
					return true;
				}	
			}
		 return false;	
	}

	public void escribirItinerario(Usuario usuario) throws SQLException {
		EscribirDatos escribirDatos = new EscribirDatos(usuario);
		
		escribirDatos.updateUsuario();
		escribirDatos.updateAtracciones();
		escribirDatos.escribirItinerario(usuario);
	}
	
	private void cerrarBaseDeDatos() throws SQLException {
		ConnectionProvider.close();
		
	}
	
}
