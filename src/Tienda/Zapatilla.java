package Tienda;

import java.awt.Color;
import java.util.logging.Level;

@SuppressWarnings("serial")
public class Zapatilla extends Producto {
	protected String modelo;
	protected double talla;
	protected Color color;
	protected Pais paisMadeIn;
	
	public Zapatilla() {
		super();
	}
	
	public Zapatilla(String codigoProducto, String nombre, double precio, String marca) {
		super(codigoProducto, nombre, precio, marca);
	}

	public Zapatilla(String codigoProducto, String nombre, double precio, String marca, String modelo, double talla,
			Color color, Pais paisMadeIn) {
		super(codigoProducto, nombre, precio, marca);
		this.modelo = modelo;
		this.talla = talla;
		this.color = color;
		this.paisMadeIn = paisMadeIn;
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
		return "Zapatilla: [modelo=" + modelo + ", talla=" + talla + ", color=" + color + ", paisMadeIn=" + paisMadeIn
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
