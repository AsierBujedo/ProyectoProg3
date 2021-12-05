package Tienda;

import java.awt.Color;
import java.util.logging.Level;

@SuppressWarnings("serial")
public class Sudadera extends Producto {
	protected Talla talla;
	protected Color color;
	protected boolean capucha;
	protected Pais paisMadeIn;
	
	public Sudadera() {
		super();
	}
	
	public Sudadera(String codigoProducto, String nombre, double precio, String marca, int ID) {
		super(codigoProducto, nombre, precio, marca, ID);
	}

	public Sudadera(String codigoProducto, String nombre, double precio, String marca, int ID, Talla talla, Color color,
			boolean capucha, Pais paisMadeIn) {
		super(codigoProducto, nombre, precio, marca, ID);
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
	    	}
    	} catch (Exception e) {
    		// Error en conversión, intentando asignar un tipo incorrecto
    		VentanaTienda.logger.log(Level.SEVERE, "Error al establecer un valor");		
    	}
	}
	
}
