package Tienda;

/**
 * Interface DatoParaTabla.
 * Métodos que tendrán que implementar aquellos objetos que se quieran añadir a la tabla.
 * @author GR08
 *
 */
public interface DatoParaTabla {
	
	/**
	 * Devuelve el tipo de objeto de la columna recibida.
	 * @param col Columna.
	 * @return tipo de objeto de la columna.
	 */
	public Object getValor(int col);
	
	/**
	 * Asigna tipo de objeto a la columna recibida.
	 * @param valor Tipo de objeto.
	 * @param col Columna.
	 */
    public void setValor(Object valor, int col);

}
