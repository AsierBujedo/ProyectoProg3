package Tienda;

import java.util.logging.Level;

/**
 * Clase Videojuego.
 * @author GR08
 *
 */
@SuppressWarnings("serial")
public class Videojuego extends Producto {
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
		this.anyo = anyo;
		this.desarrollador = desarrollador;
		this.esDigital = esDigital;
		this.tamanyo = tamanyo;
	}

	//Getters
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
		return "Videojuego | Código: "+codigoProducto+ "\tNombre: "+ nombre+ "\tPrecio: "+ precio+ "€\tMarca: "+ marca;
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
