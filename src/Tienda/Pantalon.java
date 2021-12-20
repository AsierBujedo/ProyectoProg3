package Tienda;

import java.awt.Color;
import java.util.logging.Level;

@SuppressWarnings("serial")
public class Pantalon extends Producto {
	protected Talla talla;
	protected Color color;
	protected Pais paisMadeIn;
	
	public Pantalon() {
		super();
	}
	
	public Pantalon(String codigoProducto, String nombre, double precio, String marca) {
		super(codigoProducto, nombre, precio, marca);
	}

	public Pantalon(String codigoProducto, String nombre, double precio, String marca, Talla talla, Color color, Pais paisMadeIn) {
		super(codigoProducto, nombre, precio, marca);
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
		return "Pantalon: [talla=" + talla + ", color=" + color + ", paisMadeIn=" + paisMadeIn + ", codigoProducto="
				+ codigoProducto + ", nombre=" + nombre + ", precio=" + precio + ", marca=" + marca + ", ID=" + ID
				+ "]";
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
	
}
