package Tienda;

import java.util.logging.Level;

@SuppressWarnings("serial")
public class Ordenador extends Producto {
	protected String modelo;
	protected int memInterna;
	protected int RAM;
	protected int memGraf;
	
	public Ordenador() {
		super();
	}
	
	public Ordenador(String codigoProducto, String nombre, double precio, String marca) {
		super(codigoProducto, nombre, precio, marca);
	}

	public Ordenador(String codigoProducto, String nombre, double precio, String marca, String modelo, int memInterna, int rAM,
			int memGraf) {
		super(codigoProducto, nombre, precio, marca);
		this.modelo = modelo;
		this.memInterna = memInterna;
		RAM = rAM;
		this.memGraf = memGraf;
	}
	
	//Getters
	public String getModelo() {
		return modelo;
	}
	
	public int getMemInterna() {
		return memInterna;
	}
	
	public int getRAM() {
		return RAM;
	}
	
	public int getMemGraf() {
		return memGraf;
	}

	//Setters
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public void setMemInterna(int memInterna) {
		this.memInterna = memInterna;
	}

	public void setRAM(int rAM) {
		RAM = rAM;
	}
	public void setMemGraf(int memGraf) {
		this.memGraf = memGraf;
	}
	
	@Override
	public String toString() {
		return "Ordenador | Código: "+codigoProducto+ "\tNombre: "+ nombre+ "\tPrecio: "+ precio+ "€\tMarca: "+ marca;
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
