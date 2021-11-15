package Tienda;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
class CustomTableModel extends AbstractTableModel{
	String[] nomColumnas;
	ArrayList<DatoParaTabla> datos = new ArrayList<DatoParaTabla>();
	
	// Constructor provisional
	public CustomTableModel(String[] nomColumnas, ArrayList<DatoParaTabla> datos) {
		super();
		this.nomColumnas = nomColumnas;
		this.datos = datos;
	}
	
	/*
	 * Método propio del modelo: Devuelve el número de filas
	 */
	@Override
	public int getRowCount() {
		return datos.size();
	}

	/*
	 * Método propio del modelo: Devuelve el número de columnas
	 */
	@Override
	public int getColumnCount() {
		return nomColumnas.length;
	}
	
	/*
	 * Método propio del modelo: Devuelve el valor de la celda que recibe
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return datos.get(rowIndex).getValor(columnIndex);
	}
	
	/*
	 * Método propio del modelo: Devuelve el nombre de la columna
	 */
	public String getColumnName(int columnIndex) {
        return nomColumnas[columnIndex];
    }
	/*
	 * Método propio del modelo: 
	 */
	public void setValueAt(Object valor, int row, int col) {
        datos.get(row).setValor( valor, col );
//        fireTableCellUpdated(row, col);  // Notifica a escuchadores de cambio de celda
    }
}