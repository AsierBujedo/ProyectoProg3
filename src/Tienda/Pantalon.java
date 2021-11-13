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
	public Pantalon(Talla talla, Color color, Pais paisMadeIn) {
		super();
		this.talla = talla;
		this.color = color;
		this.paisMadeIn = paisMadeIn;
	}
	
	// Constructor copia de la clase Pantalon
	public Pantalon(Pantalon p) {
		super();
		this.talla = p.talla;
		this.color = p.color;
		this.paisMadeIn = p.paisMadeIn;
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
	
}
