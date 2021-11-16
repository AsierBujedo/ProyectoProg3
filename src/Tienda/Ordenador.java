package Tienda;

public class Ordenador extends Producto {
	protected String modelo;
	protected int memInterna;
	protected int RAM;
	protected int memGraf;
	
	// Constructor sin argumentos de la clase Ordenador
	public Ordenador() {
		super();
	}
	
	// Constructor con argumentos de la clase Ordenador
	public Ordenador(String modelo, int memInterna, int rAM, int memGraf) {
		super();
		this.modelo = modelo;
		this.memInterna = memInterna;
		RAM = rAM;
		this.memGraf = memGraf;
	}
	
	// Constructor copia de la clase Ordenador
	public Ordenador(Ordenador o) {
		super();
		this.modelo = o.modelo;
		this.memInterna = o.memInterna;
		RAM = o.RAM;
		this.memGraf = o.memGraf;
	}
	
	//Getters
	public String getModelo() {
		return modelo;
	}
	
	public int getMemInterna() {
		return memInterna;
	}
	
	public int getMemGraf() {
		return memGraf;
	}
	
	public int getRAM() {
		return RAM;
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
	
}