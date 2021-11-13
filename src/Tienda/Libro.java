package Tienda;

import java.awt.Color;

public class Libro extends Producto {
	protected String autor;
	protected String editorial;
	protected Color color;
	protected boolean tapaDura;
	
	// Constructor sin argumentos de la clase Libro
	public Libro() {
		super();
	}
	
	// Constructor con argumentos de la clase Libro
	public Libro(String autor, String editorial, Color color, boolean tapaDura) {
		super();
		this.autor = autor;
		this.editorial = editorial;
		this.color = color;
		this.tapaDura = tapaDura;
	}
	
	// Constructor copia de la clase Libro
	public Libro(Libro l) {
		super();
		this.autor = l.autor;
		this.editorial = l.editorial;
		this.color = l.color;
		this.tapaDura = l.tapaDura;
	}
	
	//Getters
	public String getAutor() {
		return autor;
	}
	
	public String getEditorial() {
		return editorial;
	}
	
	public Color getColor() {
		return color;
	}
	
	public boolean isTapaDura() {
		return tapaDura;
	}

	//Setters
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setTapaDura(boolean tapaDura) {
		this.tapaDura = tapaDura;
	}
	
}
