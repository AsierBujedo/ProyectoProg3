package Tienda;

/**
 * Clase Genérica, permite la creación de productos no distinguibles usando los
 * atributos por defecto de la clase Producto.
 * 
 * @author GR08
 *
 */
public class Generico extends Producto {
	String codigoProducto;
	String nombre;
	double precio;
	String marca;
	private static final long serialVersionUID = -2358735812777990436L;

	public Generico(String codigoProducto, String nombre, double precio, String marca) {
		super(codigoProducto, nombre, precio, marca);
		this.codigoProducto = codigoProducto;
		this.nombre = nombre;
		this.precio = precio;
		this.marca = marca;
	}

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@Override
	public Object getValor(int col) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValor(Object valor, int col) {
		// TODO Auto-generated method stub

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
