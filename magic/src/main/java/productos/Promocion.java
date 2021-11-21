package productos;

import java.util.Collections;
import java.util.List;

public abstract class Promocion extends Producto {

	protected double costoSinDescuento;
	protected List<Atraccion> atracciones;

	public Promocion(int id, String nombre, List<Atraccion> atracciones) {
		super(id, nombre);
		this.atracciones = atracciones;
		setCostoSinDescuento();
		setTiempo();
	}

	private void setCostoSinDescuento() { 
		this.costoSinDescuento = 0;
		for (Atraccion atraccion : atracciones) {
			costoSinDescuento += atraccion.getCosto();
		}
	}

	public double getCostoSinDescuento() {
		return this.costoSinDescuento;
	}

	protected abstract void setCostoConDescuento();

	public void setTiempo() {
		this.tiempo = 0;
		for (Atraccion atraccion : atracciones) {
			tiempo += atraccion.getTiempo();
		}
	}

	@Override
	public int getCupo() {
		ComparadorCupoDisponible comparadorCupo = new ComparadorCupoDisponible();
		this.cupo = Collections.min(atracciones, comparadorCupo).getCupo();
		return cupo;
	}
	
	@Override
	public void setCupo() {
		for(Atraccion atraccion : atracciones) {
			atraccion.setCupo();
		}
	}
	
	@Override
	public List<Atraccion> getAtracciones() {
		return atracciones;
	}

	@Override
	public String toString() {
		return "\r\n Promocion: " + getNombre() + " Precio total: " + 
				getCostoSinDescuento() + " Precio con descuento: "
				+ getCosto() + " Tiempo: " + tiempo + " horas" 
				+ " Atracciones incluidas: " + atracciones.toString() +
				"\r\n ----------------------------------------------------";
	}
}
