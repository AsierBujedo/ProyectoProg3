package Tienda;

/**
 * Interfaz Vendible.
 * @author GR08
 */
public interface Vendible {
	/**Método que devuelve un {@link Boolean} si el producto mismo está en ese momento
	 * en la cesta.
	 * @return {@link Boolean}
	 */
	public boolean inCesta();
}