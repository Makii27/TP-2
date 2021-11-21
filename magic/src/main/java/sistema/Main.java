package sistema;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws IOException, SQLException {
		
		ObtenerDatos input = new ObtenerDatos();
		
		MagicKingdom sistema = new MagicKingdom();
		
		sistema.inicio(input.usuarios(), input.productos());	
		
	}
}
