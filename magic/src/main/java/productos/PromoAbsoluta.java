package productos;

import java.util.List;

public class PromoAbsoluta extends Promocion {

	private double descuento;

	public PromoAbsoluta(int id, String nombre, List<Atraccion> atracciones, double descuento) {
		super(id, nombre, atracciones);
		this.descuento = descuento;
		setCostoConDescuento();
	}

	@Override
	protected void setCostoConDescuento() {
		this.costo = 0;
		this.costo = this.getCostoSinDescuento() - this.descuento;
	}

	
}
