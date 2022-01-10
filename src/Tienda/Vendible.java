package Tienda;

/**
 * Interfaz Vendible.
 * @author GR08
 */
public interface Vendible {
	/**M�todo que devuelve un {@link Boolean} si el producto mismo est� en ese momento
	 * en la cesta.
	 * @return {@link Boolean}
	 */
	public boolean inCesta();
}