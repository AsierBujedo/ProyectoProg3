package Tienda;

import java.util.logging.Level;

@SuppressWarnings("serial")
public class Videojuego extends Producto {
	protected String nombre;
	protected int anyo;
	protected String desarrollador;
	protected boolean esDigital;
	protected int tamanyo; // Tamaño en GB del juego
	
	public Videojuego() {
		super();
	}
	
	public Videojuego(String codigoProducto, String nombre, double precio, String marca) {
		super(codigoProducto, nombre, precio, marca);
	}
	
	public Videojuego(String codigoProducto, String nombre, double precio, String marca, int anyo,
			String desarrollador, boolean esDigital, int tamanyo) {
		super(codigoProducto, nombre, precio, marca);
		this.nombre = nombre;
		this.anyo = anyo;
		this.desarrollador = desarrollador;
		this.esDigital = esDigital;
		this.tamanyo = tamanyo;
	}

	//Getters
	public String getNombre() {
		return nombre;
	}
	
	public int getAnyo() {
		return anyo;
	}
	
	public String getDesarrollador() {
		return desarrollador;
	}
	
	public boolean isEsDigital() {
		return esDigital;
	}
	
	public int getTamanyo() {
		return tamanyo;
	}

	//Setters
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}

	public void setDesarrollador(String desarrollador) {
		this.desarrollador = desarrollador;
	}

	public void setEsDigital(boolean esDigital) {
		this.esDigital = esDigital;
	}

	public void setTamanyo(int tamanyo) {
		this.tamanyo = tamanyo;
	}

	@Override
	public String toString() {
		return "Videojuego: [nombre=" + nombre + ", anyo=" + anyo + ", desarrollador=" + desarrollador + ", esDigital="
				+ esDigital + ", tamanyo=" + tamanyo + ", codigoProducto=" + codigoProducto + ", precio=" + precio
				+ ", marca=" + marca + ", ID=" + ID + "]";
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
