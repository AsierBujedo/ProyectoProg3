package Tienda;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

/**
 * Clase CustomTableModel.
 * 
 * @author GR08
 *
 */
@SuppressWarnings("serial")
class CustomTableModel extends AbstractTableModel {
	String[] nomColumnas;
	ArrayList<DatoParaTabla> datos = new ArrayList<DatoParaTabla>();
	
	// Constructor provisional
	public CustomTableModel(String[] nomColumnas, ArrayList<DatoParaTabla> datos) {
		super();
		this.nomColumnas = nomColumnas;
		this.datos = datos;
	}
	
	/*
	 * Método propio del modelo: Devuelve el número de filas.
	 */
	@Override
	public int getRowCount() {
		return datos.size();
	}

	/*
	 * Método propio del modelo: Devuelve el número de columnas.
	 */
	@Override
	public int getColumnCount() {
		return nomColumnas.length;
	}
	
	/*
	 * Método propio del modelo: Devuelve el valor de la celda que recibe.
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return datos.get(rowIndex).getValor(columnIndex);
	}
	
	/*
	 * Método propio del modelo: Devuelve el nombre de la columna.
	 */
	public String getColumnName(int columnIndex) {
        return nomColumnas[columnIndex];
    }
	
	/*
	 * Método propio del modelo: Establece el valor recibido como argumento en la celda especificada.
	 */
	public void setValueAt(Object valor, int row, int col) {
        datos.get(row).setValor( valor, col );
        fireTableCellUpdated(row, col);  // Notifica a escuchadores de cambio de celda
    }
	
	/*
	 * Método propio del modelo: Devuelve el renderer/editor por defecto para cada celda, identificado por la columna. 
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (datos.size()==0) return String.class;
        return datos.get(0).getValor(columnIndex).getClass();
	}
	
	/** 
	 * Elimina un dato del modelo indicado por su posición.
	 * @param row
	 */
	public void removeRow(int row) {
		datos.remove(row);   
	}
	
	/**
	 * Añade una fila nueva a la tabla.
	 * @param data lista con los datos a añadir a la tabla.
	 */
	public void newRow(String[] data) {
		String cod = data[0];
		String nombre = data[1];
		String precio = data[2];
		String marca = data[3];
		datos.add(new Generico(cod, nombre, Double.valueOf(precio), marca));
	}
}