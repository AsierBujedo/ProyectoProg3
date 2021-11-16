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
	 * M�todo propio del modelo: Devuelve el n�mero de filas
	 */
	@Override
	public int getRowCount() {
		return datos.size();
	}

	/*
	 * M�todo propio del modelo: Devuelve el n�mero de columnas
	 */
	@Override
	public int getColumnCount() {
		return nomColumnas.length;
	}
	
	/*
	 * M�todo propio del modelo: Devuelve el valor de la celda que recibe
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return datos.get(rowIndex).getValor(columnIndex);
	}
	
	/*
	 * M�todo propio del modelo: Devuelve el nombre de la columna
	 */
	public String getColumnName(int columnIndex) {
        return nomColumnas[columnIndex];
    }
	/*
	 * M�todo propio del modelo: 
	 */
	public void setValueAt(Object valor, int row, int col) {
        datos.get(row).setValor( valor, col );
//        fireTableCellUpdated(row, col);  // Notifica a escuchadores de cambio de celda
    }
}