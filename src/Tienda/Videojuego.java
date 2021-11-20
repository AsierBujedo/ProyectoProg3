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
	public Videojuego(int codigoProd, String nombre, double precio, String marca, String nombre2, int anyo,
			String desarrollador, boolean esDigital, int tamanyo) {
		super(codigoProd, nombre, precio, marca);
		nombre = nombre2;
		this.anyo = anyo;
		this.desarrollador = desarrollador;
		this.esDigital = esDigital;
		this.tamanyo = tamanyo;
	}

	// Constructor copia de la clase Videojuego
	public Videojuego(Videojuego v) {
		super();
		this.nombre = v.nombre;
		this.anyo = v.anyo;
		this.desarrollador = v.desarrollador;
		this.esDigital = v.esDigital;
		this.tamanyo = v.tamanyo;
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
		return "Videojuego [nombre=" + nombre + ", anyo=" + anyo + ", desarrollador=" + desarrollador + ", esDigital="
				+ esDigital + ", tamanyo=" + tamanyo + ", codigoProd=" + codigoProd + ", precio=" + precio + ", marca="
				+ marca + "]";
	}
	
}
