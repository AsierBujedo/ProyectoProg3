package Tienda;

public abstract class Producto implements Vendible {
	protected int codigoProd;
	protected String nombre;
	protected double precio;
	protected String marca;
	
	// Constructor sin argumentos de la clase Producto
	public Producto() {
		super();
	}
		
	// Constructor con argumentos de la clase Producto
	public Producto(int codigoProd, String nombre, double precio, String marca) {
		super();
		this.codigoProd = codigoProd;
		this.nombre = nombre;
		this.precio = precio;
		this.marca = marca;
	}
	
	// Constructor copia de la clase Producto
	public Producto(Producto p) {
		super();
		this.codigoProd = p.codigoProd;
		this.nombre = p.nombre;
		this.precio = p.precio;
		this.marca = p.marca;
	}
	
	// Getters
	public int getCodigoProd() {
		return codigoProd;
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public String getMarca() {
		return marca;
	}
	
	public String getNombre() {
		return nombre;
	}

	// Setters
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public void setCodigoProd(int codigoProd) {
		this.codigoProd = codigoProd;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
