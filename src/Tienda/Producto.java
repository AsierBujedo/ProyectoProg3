package Tienda;

import java.io.Serializable;

public abstract class Producto implements Vendible, Comparable<Producto>, Serializable, DatoParaTabla {

	private static final long serialVersionUID = 1L;
	public static int cont = 1;
	protected String codigoProducto;
	protected String nombre;
	protected double precio;
	protected String marca;
	protected int ID;
	

	// Constructor sin argumentos de la clase Producto
	public Producto() {		
		super();
		this.ID = cont;
		cont++;
	}

	// Constructor con argumentos de la clase Producto
	public Producto(String codigoProducto, String nombre, double precio, String marca, int ID) {
		super();
		this.codigoProducto = codigoProducto;
		this.nombre = nombre;
		this.precio = precio;
		this.marca = marca;
		this.ID = cont;
		cont++;
	}

	// Getters
	public String getCodigoProducto() {
		return codigoProducto;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public double getPrecio() {
		return precio;
	}

	public String getMarca() {
		return marca;
	}
	
	public int getID() {
		return ID;
	}

	// Setters
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@Override
	public String toString() {
		return "Producto: [codigoProducto=" + codigoProducto + ", nombre=" + nombre + ", precio=" + precio + ", marca="
				+ marca + ", ID=" + ID + "]";
	}	

	/**Implementación de la interfaz Comparable<Producto>
	 * Compara por precio de menor a mayor
	 * @param Producto p
	 * @return Integer
	 */
	public int compareTo(Producto p){  
		   if(this.precio == p.precio)  
		      return 0;  
		   else if(this.precio>p.precio)  
		      return 1;  
		   else  
		      return -1;  
		}
}
