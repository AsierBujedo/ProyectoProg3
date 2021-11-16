package Tienda;

import java.awt.Color;

public class Zapatilla extends Producto {
	protected String modelo;
	protected double talla;
	protected Color color;
	protected Pais paisMadeIn;
	
	// Constructor sin argumentos de la clase Zapatilla
	public Zapatilla() {
		super();
	}
	
	// Constructor con argumentos de la clase Zapatilla
	public Zapatilla(String modelo, double talla, Color color, Pais paisMadeIn) {
		super();
		this.modelo = modelo;
		this.talla = talla;
		this.color = color;
		this.paisMadeIn = paisMadeIn;
	}
	
	// Constructor copia de la clase Zapatilla
	public Zapatilla(Zapatilla z) {
		super();
		this.modelo = z.modelo;
		this.talla = z.talla;
		this.color = z.color;
		this.paisMadeIn = z.paisMadeIn;
	}
	
	//Getters
	public String getModelo() {
		return modelo;
	}
	
	public double getTalla() {
		return talla;
	}
	
	public Color getColor() {
		return color;
	}
	
	public Pais getPaisMadeIn() {
		return paisMadeIn;
	}

	//Setters
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public void setTalla(double talla) {
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
		return "Zapatilla [modelo=" + modelo + ", talla=" + talla + ", color=" + color + ", paisMadeIn=" + paisMadeIn
				+ ", codigoProd=" + codigoProd + ", nombre=" + nombre + ", precio=" + precio + ", marca=" + marca + "]";
	}
	
}
