package Ajedrez;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class Cuadrado extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int color;
	protected Tablero tablero;
	protected Pieza piezaCuadrado;
	protected boolean piezaVisible;
	protected int xPos;
	protected int yPos;
	
	// Constructor de la clase Cuadrado
	public Cuadrado(int color, Tablero tablero, Pieza pieza, boolean piezaVisible, int xPos, int yPos) {
		this.color = color;
		this.tablero = tablero;
		this.piezaCuadrado = pieza;
		this.piezaVisible = piezaVisible;
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	// Getters
	
	public int getColor() {
		return color;
	}
	
	public Tablero getTablero() {
		return tablero;
	}

	public Pieza getPiezaCuadrado() {
		return piezaCuadrado;
	}

	public boolean isPiezaVisible() {
		return piezaVisible;
	}
	
	public int getxPos() {
		return xPos;
	}
	
	public int getyPos() {
		return yPos;
	}
	
	// Setters
	public void setColor(int color) {
		this.color = color;
	}
	
	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}

	public void setPiezaCuadrado(Pieza piezaCuadrado) {
		this.piezaCuadrado = piezaCuadrado;
	}

	public void setPiezaVisible(boolean piezaVisible) {
		this.piezaVisible = piezaVisible;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	
	// Métodos propios de la clase Cuadrado
	
	// Método estaOcupado() para saber si el cuadrado está ocupado o no
	public boolean estaOcupado() {
		return (this.piezaCuadrado != null);
	}
	
	// Método establecerPieza() que hace visible la pieza para la cual se haya llamado la función 
	public void establecerPieza(boolean b) {
		this.piezaVisible = b;
	}
	
	// Método ponerPieza() que establece la posición de la pieza p
	public void ponerPieza(Pieza p) {
		this.piezaCuadrado = p;
		// LLamar al método establecerPosición de la clase Pieza
	}
	
	// Método eliminarPieza() que asigna null a la pieza a eliminar
	public Pieza eliminarPieza() {
		Pieza p = this.piezaCuadrado;
		this.piezaCuadrado = null;
		return p;
	}
	
	// Método comerPieza() que elimina la pieza actual del cuadrado y la sustituye por la pieza que se la ha comido
	public void comerPieza(Pieza p) {
		Pieza piezaEnCuadrado = getPiezaCuadrado();
		// Eliminar la piezaEnCuadrado blanca o negra de los ArrayLists de Piezas
		this.piezaCuadrado = p;
	}
	
	public void paintComponent(Graphics graf) {
        super.paintComponent(graf);
        
        if (this.color == 1) {
        	graf.setColor(new Color(221,192,127));
        } else {
        	graf.setColor(new Color(101,67,33));
        }
        
        graf.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        
        if(piezaCuadrado != null && piezaVisible) {
        	piezaCuadrado.dibuja(graf);
        }
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + xPos;
        result = prime * result + yPos;
		return result;
	}	
	
}
