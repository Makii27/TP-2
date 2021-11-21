package productos;

import java.util.Comparator;

public class ComparadorCupoDisponible implements Comparator<Atraccion>{

	public int compare(Atraccion atraccion1, Atraccion atraccion2) {
		return Integer.compare(atraccion1.getCupo(), atraccion2.getCupo());
	}

}
