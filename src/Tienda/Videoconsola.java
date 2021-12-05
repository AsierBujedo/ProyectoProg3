package Tienda;

public class Videoconsola extends Producto {
	protected String modelo;
	protected int almacenamiento;
	protected int RAM;
	protected int memGraf;
	protected boolean esPortatil;
	protected boolean versionDigital;
	protected boolean internetConex;
	
	// Constructor sin argumentos de la clase Videoconsola
	public Videoconsola() {
		super();
	}
	
	// Constructor con argumentos de la clase Videoconsola
	public Videoconsola(int ID, String nombre, double precio, String marca, String modelo, int almacenamiento,
			int rAM, int memGraf, boolean esPortatil, boolean versionDigital, boolean internetConex) {
		super(ID, nombre, precio, marca);
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
		return "Videoconsola: [modelo=" + modelo + ", almacenamiento=" + almacenamiento + ", RAM=" + RAM + ", memGraf="
				+ memGraf + ", esPortatil=" + esPortatil + ", versionDigital=" + versionDigital + ", internetConex="
				+ internetConex + ", ID=" + ID + ", nombre=" + nombre + ", precio=" + precio + ", marca=" + marca + "]";
	}
	
}
