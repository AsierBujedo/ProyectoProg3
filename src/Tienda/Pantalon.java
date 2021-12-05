package Tienda;

import java.awt.Color;

public class Pantalon extends Producto {
	protected Talla talla;
	protected Color color;
	protected Pais paisMadeIn;
	
	// Constructor sin argumentos de la clase Pantalon
	public Pantalon() {
		super();
	}
	
	// Constructor con argumentos de la clase Pantalon
	public Pantalon(int ID, String nombre, double precio, String marca, Talla talla, Color color, Pais paisMadeIn) {
		super(ID, nombre, precio, marca);
		this.talla = talla;
		this.color = color;
		this.paisMadeIn = paisMadeIn;
	}

	//Getters
	public Talla getTalla() {
		return talla;
	}
	
	public Color getColor() {
		return color;
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

	public void setPaisMadeIn(Pais paisMadeIn) {
		this.paisMadeIn = paisMadeIn;
	}

	@Override
	public String toString() {
		return "Pantalon: [talla=" + talla + ", color=" + color + ", paisMadeIn=" + paisMadeIn + ", ID=" + ID
				+ ", nombre=" + nombre + ", precio=" + precio + ", marca=" + marca + "]";
	}
	
}
