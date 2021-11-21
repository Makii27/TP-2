package productos;

import java.util.List;

public class PromoPorcentual extends Promocion {

	private double porcentajeDescuento;
	
    public PromoPorcentual(int id, String nombre, List<Atraccion> atracciones, double porcentaje) {
		super(id, nombre, atracciones);
		this.porcentajeDescuento = porcentaje/100; // 0.15
		setCostoConDescuento();
    }

	@Override
	protected void setCostoConDescuento() {
		this.costo = 0;
		double descuento = this.getCostoSinDescuento() * this.porcentajeDescuento;
		
		this.costo = this.getCostoSinDescuento() - descuento;		
	}
}
