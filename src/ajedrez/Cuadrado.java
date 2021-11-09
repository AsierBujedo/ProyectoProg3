package ajedrez;

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
	
	// Constructor sin argumentos de la clase Cuadrado
		public Cuadrado() {
		}
	
	// Constructor con argumentos de la clase Cuadrado
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
	
	// Método estaOcupado()
	/** Asigna un valor no nulo a piezaCuadrado para indicar que el cudrado está ocupado por una pieza
	 * @return Estado del cuadrado
	 */
	public boolean estaOcupado() {
		return (this.piezaCuadrado != null);
	}
	
	// Método mostrarPieza()
	/** Muestra o no una pieza dependiendo el parametro que recibe
	 * @param b	Valor booleano
	 */
	public void mostrarPieza(boolean b) {
		this.piezaVisible = b;
	}
	
	// Método ponerPieza()
	/** Coloca la pieza que recibe como argumento en el cuadrado
	 * @param pieza	Objeto del tipo Pieza
	 */
	public void ponerPieza(Pieza pieza) {
		this.piezaCuadrado = pieza;
		// LLamar al método establecerPosición de la clase Pieza
	}
	
	// Método eliminarPieza()
	/** Elimina la pieza del cuadrado
	 * @return Pieza con valor nulo
	 */
	public Pieza eliminarPieza() {
		Pieza pieza = this.piezaCuadrado; // Guarda en una nueva variable pieza del tipo Pieza la pieza que está ocupando el cuadrado 
		this.piezaCuadrado = null; // Le asigna valor nulo a esa pieza
		return pieza; // Devuelve la variable pieza con valor nulo
	}
	
	// Método comerPieza()
	/** Sustituye la pieza que está en el cuadrado por la pieza p que recibe como parámetro
	 * @param pieza	Objeto del tipo Pieza que va a sustituir a la pieza del cuadrado
	 */
	public void comerPieza(Pieza pieza) {
		Pieza piezaEnCuadrado = getPiezaCuadrado();
		 if (piezaEnCuadrado.getColor() == 0) tablero.piezasNegras.remove(pieza); // Si el color de la pieza es 0 (Negro) elimina la pieza del ArrayList de piezas Negras
	     if (piezaEnCuadrado.getColor() == 1) tablero.piezasBlancas.remove(pieza); // Si el color de la pieza es 1 (Blanco) elimina la pieza del ArrayList de piezas Blancas
	     this.piezaCuadrado = pieza;
	}
	
	// Método paintComponent()
	/**
	 * 
	 */
	public void paintComponent(Graphics graf) {
        super.paintComponent(graf); // ¿?
        
        if (this.color == 1) {
        	graf.setColor(new Color(238,238,210,255)); // El parámetro A (alfa) es un número entre 0.0 (totalmente transparente) y 1.0 (nada transparente)
        } else {
        	graf.setColor(new Color(118,150,86,255));
        }
        
        graf.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        
        if(piezaCuadrado != null && piezaVisible) {
        	piezaCuadrado.dibuja(graf);
        }
    }
	
	// Método hashCode() generado automáticamente por Eclipse
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + xPos;
        result = prime * result + yPos;
		return result;
	}	
	
}
