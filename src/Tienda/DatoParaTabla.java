package Tienda;

import BD.COLS;

/**
 * Interface DatoParaTabla.
 * M�todos que tendr�n que implementar aquellos objetos que se quieran a�adir a la tabla.
 * @author GR08
 *
 */
public interface DatoParaTabla {
	
	/**
	 * Devuelve el tipo de objeto de la columna recibida.
	 * @param col {@link COLS}.
	 * @return {@link Object}.
	 */
	public Object getValor(int col);
	
	/**
	 * Asigna tipo de objeto a la columna recibida.
	 * @param valor {@link Object}.
	 * @param col {@link COLS}.
	 */
    public void setValor(Object valor, int col);

}
