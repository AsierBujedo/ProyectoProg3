package Tienda;

public class Videojuego extends Producto {
	protected String nombre;
	protected int anyo;
	protected String desarrollador;
	protected boolean esDigital;
	protected int tamanyo; // Tamaño en GB del juego
	
	// Constructor sin argumentos de la clase Videojuego
	public Videojuego() {
		super();
	}
	
	// Constructor con argumentos de la clase Videojuego
	public Videojuego(int ID, String nombre, double precio, String marca, String nombre2, int anyo,
			String desarrollador, boolean esDigital, int tamanyo) {
		super(ID, nombre, precio, marca);
		nombre = nombre2;
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
				+ esDigital + ", tamanyo=" + tamanyo + ", ID=" + ID + ", precio=" + precio + ", marca=" + marca + "]";
	}
	
}
