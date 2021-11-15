package Tienda;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
class CustomTableModel extends AbstractTableModel{
	String[] nomColumnas = {"Columna 1", "Columna 2", "Columna 3"};
	Object[][] datos= {
		    {"Asier", "Bujedo", "AB"},
			{"Eduardo", "Larrinaga", "EL"},
			{"Iker", "L�pez", "IL"},
			{"Mikel", "Lambarri", "ML"},
		    }; 
	// Siendo el atributo datos un Array bidimiensional
	//ArrayList<Object> datos = new ArrayList<Object>(); // Tipo de objeto Object provisional hasta elecci�n de tipo
	
	
	/*
	 * M�todo propio del modelo: Devuelve el n�mero de filas
	 */
	@Override
	public int getRowCount() {
		return datos.length; // Siendo el atributo datos un Array bidimiensional
		//return datos.size(); Esto si usamos un ArrayList
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
		return datos[rowIndex][columnIndex]; // Siendo el atributo datos un Array bidimiensional
		//return datos.get(rowIndex).getXXXX(columnIndex); Esto si usamos un ArrayList
	}
	
	/*
	 * M�todo propio del modelo:Devuelve el nombre de la columna
	 */
	public String getColumnName(int columnIndex) {
        return nomColumnas[columnIndex];
    }
}