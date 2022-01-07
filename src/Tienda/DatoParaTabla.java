package Tienda;

/**
 * Interface DatoParaTabla.
 * 
 * @author GR08
 *
 */
public interface DatoParaTabla {
	/**
	 * 
	 * @param col
	 * @return
	 */
	public Object getValor(int col);
	
	/**
	 * 
	 * @param valor
	 * @param col
	 */
    public void setValor(Object valor, int col);

}
