package Tienda;

public class Telefono extends Producto {
	protected String modelo;
	protected int almacenamiento;
	protected int RAM;
	protected int Hz;
	protected int mAh;
	
	// Constructor sin argumentos de la clase Telefono
	public Telefono() {
		super();
	}
	
	// Constructor con argumentos de la clase Telefono
	public Telefono(String modelo, int almacenamiento, int rAM, int hz, int mAh) {
		super();
		this.modelo = modelo;
		this.almacenamiento = almacenamiento;
		RAM = rAM;
		Hz = hz;
		this.mAh = mAh;
	}
	
	// Constructor copia de la clase Telefono
	public Telefono(Telefono t) {
		super();
		this.modelo = t.modelo;
		this.almacenamiento = t.almacenamiento;
		RAM = t.RAM;
		Hz = t.Hz;
		this.mAh = t.mAh;
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
	
}