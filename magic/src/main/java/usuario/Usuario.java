package usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import productos.Atraccion;
import productos.Producto;

public class Usuario {
	private int id;
	private String nombre;
	private double dinero;
	private double tiempo;
	private List<Producto> productosAdquiridos;
	private Map<Integer, Atraccion> atraccionesAdquiridas= new HashMap<Integer, Atraccion>();
	
	public Usuario(int id, String nombre, double dinero, double tiempo, Map<Integer, Atraccion> atraccionesAdquiridas) {
		this.id = id;
		this.nombre = nombre;
		this.dinero = dinero;
		this.tiempo = tiempo;
		this.productosAdquiridos = new ArrayList<Producto>();
		this.atraccionesAdquiridas = atraccionesAdquiridas;
	}
	
	public int getId() {
		return id;
	}
	public double getDinero() {
		return dinero;
	}
	public String getNombre() {
		return nombre;
	}
	public double getTiempo() {
		return tiempo;
	}
	public List<Producto> getProductosAdquiridos() {
		return productosAdquiridos;
	}	
	public void setDinero(Producto producto) {
		this.dinero -= producto.getCosto();
	}
	public void setTiempo(Producto producto) {
		this.tiempo -= producto.getTiempo();
	}
	public void setProductosAdquiridos(Producto producto) {
		this.productosAdquiridos.add(producto);
	}
	public void setAtraccionesAdquiridas(Producto producto) {
		for (Atraccion atraccion : producto.getAtracciones()) {
			atraccionesAdquiridas.put(atraccion.getId(), atraccion);
		}
	}
	public Map<Integer, Atraccion> getAtraccionesAdquiridas(){
		return atraccionesAdquiridas;
	}
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", dinero=" + dinero + ", tiempo=" + tiempo + "]";
	}

}