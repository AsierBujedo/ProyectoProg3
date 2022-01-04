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
	
	public Sudadera(String codigoProducto, String nombre, double precio, String marca) {
		super(codigoProducto, nombre, precio, marca);
	}

	public Sudadera(String codigoProducto, String nombre, double precio, String marca, Talla talla, Color color,
			boolean capucha, Pais paisMadeIn) {
		super(codigoProducto, nombre, precio, marca);
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
	public String toString() {
		return "Sudadera | Código: "+codigoProducto+ "\tNombre: "+ nombre+ "\tPrecio: "+ precio+ "€\tMarca: "+ marca;
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
