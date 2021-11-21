package productos;
import java.util.ArrayList;
import java.util.List;

public class Atraccion extends Producto {
	
	public Atraccion(int id, String nombre, double costo, double tiempo, int cupo) {	
		super(id, nombre);
		this.costo = costo;
		this.tiempo = tiempo;
		this.cupo = cupo;
	}
		
	public static int buscarAtraccion(List<Atraccion> atracciones,String atraccionBuscada)
	{
		int i=0;
		while((i < atracciones.size()) && (0 != atracciones.get(i).getNombre().compareTo(atraccionBuscada)))	{
			i++;
		}
		return i;
	}

	@Override
	public void setCupo() {
		this.cupo -= 1;
		
	}

	@Override
	public List<Atraccion> getAtracciones() {
		List<Atraccion> atraccionEnLista = new ArrayList<Atraccion>();
		atraccionEnLista.add(this);
		return atraccionEnLista;
	}
	
	@Override
	public String toString() {
		return "\r\n Atraccion: " + this.getNombre() + "\r\n Costo: " + this.getCosto() + "\r\n Tiempo: "
				+ this.getTiempo() + " horas" + "\r\n cupo: " + cupo + "\r\n ---------";
	}
	
}