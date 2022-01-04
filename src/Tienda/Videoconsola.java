package Tienda;

import java.util.logging.Level;

@SuppressWarnings("serial")
public class Videoconsola extends Producto {
	protected String modelo;
	protected int almacenamiento;
	protected int RAM;
	protected int memGraf;
	protected boolean esPortatil;
	protected boolean versionDigital;
	protected boolean internetConex;
	
	public Videoconsola() {
		super();
	}
	
	public Videoconsola(String codigoProducto, String nombre, double precio, String marca) {
		super(codigoProducto, nombre, precio, marca);
	}

	public Videoconsola(String codigoProducto, String nombre, double precio, String marca, String modelo, int almacenamiento,
			int rAM, int memGraf, boolean esPortatil, boolean versionDigital, boolean internetConex) {
		super(codigoProducto, nombre, precio, marca);
		this.modelo = modelo;
		this.almacenamiento = almacenamiento;
		RAM = rAM;
		this.memGraf = memGraf;
		this.esPortatil = esPortatil;
		this.versionDigital = versionDigital;
		this.internetConex = internetConex;
	}

	//Getters
	public String getModelo() {
		return modelo;
	}
	
	public int getAlmacenamiento() {
		return almacenamiento;
	}
	
	public int getRAM() {
		return RAM;
	}
	
	public int getMemGraf() {
		return memGraf;
	}
	
	public boolean isEsPortatil() {
		return esPortatil;
	}
	
	public boolean isVersionDigital() {
		return versionDigital;
	}
	
	public boolean isInternetConex() {
		return internetConex;
	}

	//Setters
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public void setAlmacenamiento(int almacenamiento) {
		this.almacenamiento = almacenamiento;
	}

	public void setRAM(int rAM) {
		RAM = rAM;
	}

	public void setMemGraf(int memGraf) {
		this.memGraf = memGraf;
	}

	public void setEsPortatil(boolean esPortatil) {
		this.esPortatil = esPortatil;
	}

	public void setVersionDigital(boolean versionDigital) {
		this.versionDigital = versionDigital;
	}

	public void setInternetConex(boolean internetConex) {
		this.internetConex = internetConex;
	}
	
	@Override
	public String toString() {
		return "Videoconsola | Código: "+codigoProducto+ "\tNombre:"+ nombre+ "\tPrecio: "+ precio+ "€\tMarca: "+ marca;
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
