package Tienda;

import java.util.logging.Level;

@SuppressWarnings("serial")
public class Impresora extends Producto {
	protected String modelo;
	protected boolean esColor;
	protected boolean esLaser;
	
	public Impresora() {
		super();
	}
	
	public Impresora(String codigoProducto, String nombre, double precio, String marca) {
		super(codigoProducto, nombre, precio, marca);
	}

	public Impresora(String codigoProducto, String nombre, double precio, String marca, String modelo, boolean esColor,
			boolean esLaser) {
		super(codigoProducto, nombre, precio, marca);
		this.modelo = modelo;
		this.esColor = esColor;
		this.esLaser = esLaser;
	}

	// Getters
	public String getModelo() {
		return modelo;
	}

	public boolean isEsColor() {
		return esColor;
	}
	
	public boolean isEsLaser() {
		return esLaser;
	}

	// Setters
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public void setEsColor(boolean esColor) {
		this.esColor = esColor;
	}

	public void setEsLaser(boolean esLaser) {
		this.esLaser = esLaser;
	}

	@Override
	public String toString() {
		return "Impresora: [modelo=" + modelo + ", esColor=" + esColor + ", esLaser=" + esLaser + ", codigoProducto="
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
