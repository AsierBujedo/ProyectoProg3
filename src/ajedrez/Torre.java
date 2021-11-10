package ajedrez;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Torre extends Pieza{

	public Torre(int color, Cuadrado cuadradoActual, String img) {
		super(color, cuadradoActual, img);
	}

	@Override
	public ArrayList<Cuadrado> getMovimientosLegales(Tablero tablero) {
		ArrayList<Cuadrado> movimientos=new ArrayList<Cuadrado>();
		int x=cuadradoActual.getX();
		int y=cuadradoActual.getY();
		return null;
	}

}
