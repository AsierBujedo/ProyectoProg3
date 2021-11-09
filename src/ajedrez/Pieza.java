package ajedrez;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

abstract public class Pieza {
	protected int color;
	protected Cuadrado cuadradoActual;
	protected BufferedImage imagen;
	
	// Constructor sin argumentos de la clase Pieza
		public Pieza() {			
		}
		
	// Constructor con argumentos de la clase Pieza
	public Pieza(int color, Cuadrado cuadradoActual, String img) {
		super();
		this.color = color;
		this.cuadradoActual = cuadradoActual;
		
		try {
			this.imagen = ImageIO.read(getClass().getResource(img));

		} catch (IOException e) {
			System.out.println("Archivo no encontrado: " + e.toString());
		}
		
	}
	
	// Getters
	public int getColor() {
		return color;
	}
	
	public Cuadrado getCuadradoActual() {
		return cuadradoActual;
	}
	
	public BufferedImage getImagen() {
		return imagen;
	}
	
	// Setters
	public void setColor(int color) {
		this.color = color;
	}

	public void setCuadradoActual(Cuadrado cuadradoActual) {
		this.cuadradoActual = cuadradoActual;
	}

	public void setImagen(BufferedImage imagen) {
		this.imagen = imagen;
	}
	
	// M�todos propios de la clase Pieza
	
	//M�todo getPosicion()
	/**
	 * Devuelve la posici�n de la pieza
	 * @return Cuadrado en el que est� colocada la pieza
	 */
	public Cuadrado getPosicion() {
		return cuadradoActual;
	}
	
	//M�todo setPosicion()
	/**
	 * Coloca la pieza en el cuadrado que recibe como argumento
	 * @param cuadrado Posici�n en la que queremos colocar la pieza
	 */
	public void setPosicion(Cuadrado cuadrado) {
		cuadradoActual = cuadrado;
	}
	
	// M�todo dibuja()
	/**
	 * ***************
	 * @param graf
	 */
	public void dibuja(Graphics graf) {
        int x = cuadradoActual.getX();
        int y = cuadradoActual.getY();
        
        graf.drawImage(this.imagen, x, y, null);
    }
	
	// M�todo moverPieza()
	/**
	 * ***************
	 * @param cuadrado
	 * @return
	 */
	public boolean moverPieza(Cuadrado cuadrado) {
		Pieza piezaEnCuadrado = cuadrado.getPiezaCuadrado();
		
		if(piezaEnCuadrado != null) {
			if(cuadrado.getColor() == this.color) {
				return false;
			}
		} else {
			cuadrado.comerPieza(this);
		}
		
		cuadradoActual.eliminarPieza();
		this.cuadradoActual = cuadrado;
		cuadradoActual.ponerPieza(this);
		return true;
	}
	
	// M�todo getMovimeintosLegales() a implementar por las clases hijas
	/**
	 * M�todo abstracto que devuelve los movimientos legales de la pieza
	 * @param tablero
	 * @return Los movimientos legales de la pieza
	 */
	public abstract ArrayList<Cuadrado> getMovimientosLegales(Tablero tablero);
	
	
	
	
}
