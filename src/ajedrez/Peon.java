package ajedrez;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Peon extends Pieza{
	boolean seHaMovido;

	public Peon(int color, Cuadrado cuadradoActual, String img, boolean seHaMovido) {
		super(color, cuadradoActual, img);
		this.seHaMovido=seHaMovido;
	}

	@Override
	public ArrayList<Cuadrado> getMovimientosLegales(Tablero tablero) {
		ArrayList<Cuadrado> movimientos=new ArrayList<Cuadrado>();
		int x=cuadradoActual.getX();
		int y=cuadradoActual.getY();
		if(this.color==0) {//casos con los peones abajo (están "subiendo")
			if(tablero.GetCuadrados()[y+1][x].estaOcupado()==false) {//casilla de enfrente
				movimientos.add(tablero.GetCuadrados()[y+1][x]);
			}
			if(tablero.GetCuadrados()[y+1][x+1].estaOcupado()==true) {//casilla diagonal derecha, para comer pieza
				movimientos.add(tablero.GetCuadrados()[y+1][x+1]);
			}
			if(tablero.GetCuadrados()[y+1][x-1].estaOcupado()==true) {//casilla diagonal izquierda, para comer pieza
				movimientos.add(tablero.GetCuadrados()[y+1][x-1]);
			}
			if(this.seHaMovido==false) {
				if(tablero.GetCuadrados()[y+2][x].estaOcupado()==false) {//casilla dos enfrente, solo si no se ha movido antes
					movimientos.add(tablero.GetCuadrados()[y+1][x]);
				}
			}
		}
		if(this.color==1) {//casos con los peones arriba (están "bajando")
			if(tablero.GetCuadrados()[y-1][x].estaOcupado()==false) {//casilla de enfrente
				movimientos.add(tablero.GetCuadrados()[y+1][x]);
			}
			if(tablero.GetCuadrados()[y-1][x+1].estaOcupado()==true) {//casilla diagonal derecha, para comer pieza
				movimientos.add(tablero.GetCuadrados()[y+1][x+1]);
			}
			if(tablero.GetCuadrados()[y-1][x-1].estaOcupado()==true) {//casilla diagonal izquierda, para comer pieza
				movimientos.add(tablero.GetCuadrados()[y+1][x-1]);
			}
			if(this.seHaMovido==false) {
				if(tablero.GetCuadrados()[y-2][x].estaOcupado()==false) {//casilla dos enfrente, solo si no se ha movido antes
					movimientos.add(tablero.GetCuadrados()[y+1][x]);
				}
				
			}
		}
		return movimientos;
	}

}
