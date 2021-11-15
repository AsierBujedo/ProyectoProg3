package Tienda;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
class CustomTableModel extends AbstractTableModel{
	String[] nomColumnas = {"Columna 1", "Columna 2", "Columna 3"};
	Object[][] datos= {
		    {"Asier", "Bujedo", "AB"},
			{"Eduardo", "Larrinaga", "EL"},
			{"Iker", "López", "IL"},
			{"Mikel", "Lambarri", "ML"},
		    }; 
	// Siendo el atributo datos un Array bidimiensional
	//ArrayList<Object> datos = new ArrayList<Object>(); // Tipo de objeto Object provisional hasta elección de tipo
	
	
	/*
	 * Método propio del modelo: Devuelve el número de filas
	 */
	@Override
	public int getRowCount() {
		return datos.length; // Siendo el atributo datos un Array bidimiensional
		//return datos.size(); Esto si usamos un ArrayList
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
		return datos[rowIndex][columnIndex]; // Siendo el atributo datos un Array bidimiensional
		//return datos.get(rowIndex).getXXXX(columnIndex); Esto si usamos un ArrayList
	}
	
	/*
	 * Método propio del modelo:Devuelve el nombre de la columna
	 */
	public String getColumnName(int columnIndex) {
        return nomColumnas[columnIndex];
    }
}