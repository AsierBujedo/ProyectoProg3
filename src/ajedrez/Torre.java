package ajedrez;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class Torre extends Pieza{

	public Torre(int color, Cuadrado cuadradoActual, String img) {
		super(color, cuadradoActual, img);
	}

	@Override
	public ArrayList<Cuadrado> getMovimientosLegales(Tablero tablero) {
		ArrayList<Cuadrado> movimientos=new ArrayList<Cuadrado>();
		int x=cuadradoActual.getX();
		int y=cuadradoActual.getY();
		for (int i = x+1; i < tablero.getHeight(); i++) {//movimiento a x positivo.
			if(tablero.GetCuadrados()[y][i].estaOcupado()==false) {
				movimientos.add(tablero.GetCuadrados()[y][i]);
			}else {break;
			}
		}
		for (int i = x-1; i < 0; i--) {//movimiento a x negativo
			if(tablero.GetCuadrados()[y][i].estaOcupado()==false) {
				movimientos.add(tablero.GetCuadrados()[y][i]);
			}else {break;
			}
		}
		for (int i = y+1; i < tablero.getWidth(); i++) {//movimiento a y positivo
			if(tablero.GetCuadrados()[i][x].estaOcupado()==false) {
				movimientos.add(tablero.GetCuadrados()[y][i]);
			}else {break;
			}
		}
		for (int i = y-1; i < 0; i--) {//movimiento a y negativo
			if(tablero.GetCuadrados()[i][x].estaOcupado()==false) {
				movimientos.add(tablero.GetCuadrados()[y][i]);
			}else {break;
			}
		}

		return movimientos;
	}

}