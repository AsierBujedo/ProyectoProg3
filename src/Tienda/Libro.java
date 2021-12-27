package Tienda;

import java.awt.Color;
import java.util.logging.Level;

@SuppressWarnings("serial")
public class Libro extends Producto {
	protected String autor;
	protected String editorial;
	protected Color color;
	protected boolean tapaDura;
	
	public Libro() {
		super();
	}
	
	public Libro(String codigoProducto, String nombre, double precio, String marca) {
		super(codigoProducto, nombre, precio, marca);
	}

	public Libro(String codigoProducto, String nombre, double precio, String marca, String autor, String editorial,
			Color color, boolean tapaDura) {
		super(codigoProducto, nombre, precio, marca);
		this.autor = autor;
		this.editorial = editorial;
		this.color = color;
		this.tapaDura = tapaDura;
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

	@Override
	public String toString() {
		return "Libro: [autor=" + autor + ", editorial=" + editorial + ", color=" + color + ", tapaDura=" + tapaDura
				+ ", codigoProducto=" + codigoProducto + ", nombre=" + nombre + ", precio=" + precio + ", marca="
				+ marca + ", ID=" + ID + "]";
	}
	
	@Override
	public Object getValor(int col) {
		switch (col) {
    	case 0: { return codigoProducto; }
    	case 1: { return nombre; }
    	case 2: { return precio; }
    	case 3: { return marca; }
    	case 4: { return ID; }
	}
		return null;
	}

	@Override
	public void setValor(Object valor, int col) {
		try {
	    	switch (col) {
		    	case 0: { codigoProducto = (String) valor; break; }
		    	case 1: { nombre = (String) valor; break; }
		    	case 2: { precio = (double) valor; break; }
		    	case 3: { marca = (String) valor; break; }
		    	case 4: { ID = (int) valor; break; }
	    	}
    	} catch (Exception e) {
    		// Error en conversión, intentando asignar un tipo incorrecto
    		VentanaTienda.logger.log(Level.SEVERE, "Error al establecer un valor");		
    	}
	}
	
	@Override
	public boolean inCesta() {
		if (Cesta.cesta.contains(this)) {
			return true;
		} else {
			return false;
		}	
	}
	
}
