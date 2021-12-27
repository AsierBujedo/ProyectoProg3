package Tienda;

import java.util.logging.Level;

@SuppressWarnings("serial")
public class Telefono extends Producto {
	protected String modelo;
	protected int almacenamiento;
	protected int RAM;
	protected int Hz;
	protected int mAh;
	
	public Telefono() {
		super();
	}
	
	public Telefono(String codigoProducto, String nombre, double precio, String marca) {
		super(codigoProducto, nombre, precio, marca);
	}

	public Telefono(String codigoProducto, String nombre, double precio, String marca, String modelo, int almacenamiento,
			int rAM, int hz, int mAh) {
		super(codigoProducto, nombre, precio, marca);
		this.modelo = modelo;
		this.almacenamiento = almacenamiento;
		RAM = rAM;
		Hz = hz;
		this.mAh = mAh;
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
	
	public int getHz() {
		return Hz;
	}
	
	public int getmAh() {
		return mAh;
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

	public void setHz(int hz) {
		Hz = hz;
	}

	public void setmAh(int mAh) {
		this.mAh = mAh;
	}

	@Override
	public String toString() {
		return "Telefono: [modelo=" + modelo + ", almacenamiento=" + almacenamiento + ", RAM=" + RAM + ", Hz=" + Hz
				+ ", mAh=" + mAh + ", codigoProducto=" + codigoProducto + ", nombre=" + nombre + ", precio=" + precio
				+ ", marca=" + marca + ", ID=" + ID + "]";
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
