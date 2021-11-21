package productos;

import java.util.List;

public class PromoAxB extends Promocion {

	public PromoAxB(int id, String nombre, List<Atraccion> atracciones) {
		super(id, nombre, atracciones);
		setCostoConDescuento();
	}

	@Override
	protected void setCostoConDescuento() {
		this.costo = 0;
		
		for(int i = 0; i < (atracciones.size() - 1); i++) {
			this.costo += atracciones.get(i).getCosto();
		}		
	}
}
