package Tienda;

public class Impresora extends Producto {
	protected String modelo;
	protected boolean esColor;
	protected boolean esLaser;
	
	// Constructor sin argumentos de la clase Impresora
	public Impresora() {
		super();

	}
	
	// Constructor con argumentos de la clase Impresora
	public Impresora(int ID, String nombre, double precio, String marca, String modelo, boolean esColor,
			boolean esLaser) {
		super(ID, nombre, precio, marca);
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
	public void setEsColor(boolean esColor) {
		this.esColor = esColor;
	}

	public void setEsLaser(boolean esLaser) {
		this.esLaser = esLaser;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	@Override
	public String toString() {
		return "Impresora: [modelo=" + modelo + ", esColor=" + esColor + ", esLaser=" + esLaser + ", ID=" + ID
				+ ", nombre=" + nombre + ", precio=" + precio + ", marca=" + marca + "]";
	}
	
}
