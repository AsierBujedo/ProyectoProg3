package Tienda;

import java.awt.Color;

public class Sudadera extends Producto {
	protected Talla talla;
	protected Color color;
	protected boolean capucha;
	protected Pais paisMadeIn;
	
	// Constructor sin argumentos de la clase Sudadera
	public Sudadera() {
		super();
	}
	
	// Constructor con argumentos de la clase Sudadera
	public Sudadera(int ID, String nombre, double precio, String marca, Talla talla, Color color,
			boolean capucha, Pais paisMadeIn) {
		super(ID, nombre, precio, marca);
		this.talla = talla;
		this.color = color;
		this.capucha = capucha;
		this.paisMadeIn = paisMadeIn;
	}
	
	//Getters
	public Talla getTalla() {
		return talla;
	}
	
	public Color getColor() {
		return color;
	}
	
	public boolean isCapucha() {
		return capucha;
	}
	
	public Pais getPaisMadeIn() {
		return paisMadeIn;
	}

	//Setters
	public void setTalla(Talla talla) {
		this.talla = talla;
	}


	public void setColor(Color color) {
		this.color = color;
	}


	public void setCapucha(boolean capucha) {
		this.capucha = capucha;
	}

	public void setPaisMadeIn(Pais paisMadeIn) {
		this.paisMadeIn = paisMadeIn;
	}

	@Override
	public String toString() {
		return "Sudadera: [talla=" + talla + ", color=" + color + ", capucha=" + capucha + ", paisMadeIn=" + paisMadeIn
				+ ", ID=" + ID + ", nombre=" + nombre + ", precio=" + precio + ", marca=" + marca + "]";
	}
	
}
