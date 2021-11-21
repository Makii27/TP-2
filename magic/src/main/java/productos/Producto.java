package productos;

import java.util.List;

public abstract class Producto {

	private int id;
	private String nombre;
	protected double costo;
	protected double tiempo;
	protected int cupo;
	
	public Producto(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	public int getId() {
		return this.id;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public double getCosto() {
		return this.costo;
	} // promocion calcula el costo TOTAL en otro metodo (setCostoSinDescuento)
	
	public double getTiempo() {
		return this.tiempo;
	}
	// en promos devuelve la suma del tiempo de todas las atracciones
	
	public int getCupo() {
		return this.cupo;  //promociones sobreescribe este metodo
	}                      // para devolver el cupo de la atraccion con menor

	public abstract void setCupo();
	
	public abstract List<Atraccion> getAtracciones();
		
}
