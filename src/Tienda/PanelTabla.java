package Tienda;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class PanelTabla extends JPanel {
	
	/** Crea un panel y le añade una tabla
	 * @param nomColumnas Array con los nombres de las columnas de la tabla
	 * @param datos ArrayList<DatoParaTabla> con los datos para la tabla
	 * @return Panel que contiene la tabla
	 */
	public static JPanel getPanelTabla(String[] nomColumnas,  ArrayList<DatoParaTabla> datos, Color backgroundColorHeader) {
		JPanel panelTabla = new PanelTabla();
		panelTabla.setLayout(new BorderLayout());
		JTable tabla = new JTable(new CustomTableModel(nomColumnas, datos)); // Crea la tabla pasándole el modelo personalizado
		tabla.setOpaque(true);
		tabla.setFont(new Font("Uni Sans Heavy", Font.BOLD, 15));
		tabla.setForeground(Color.BLACK);
		tabla.getTableHeader().setBackground(backgroundColorHeader);
		tabla.getTableHeader().setFont(new Font("Uni Sans Heavy", Font.BOLD, 15));
		tabla.getTableHeader().setForeground(Color.WHITE);
		tabla.getTableHeader().setReorderingAllowed(false);
		
		for (int i = 0; i < nomColumnas.length; i++) {
			tabla.getColumnModel().getColumn(i).setResizable(false);
		}
		
		//Añadir botón "añadir a la cesta" en esta clase, devolveremos el panel con el ActionListener ya creado desde aquí
		//De esta forma, podemos acceder a los datos de la tabla sin problemas.
		//Poniendo el listener desde VentanaTienda no puedes acceder a los datos, por tanto, no puedes añadir nada a la cesta.
		//tabla.getvalueAt(int row, int column);
		//Mas información en https://stackoverflow.com/questions/29345792/java-jtable-getting-the-data-of-the-selected-row
		
		
		JScrollPane panelScroll = new JScrollPane(tabla); // Crea un ScrollPane que contendrá la tabla
		panelTabla.add(panelScroll); // Añade el ScrollPane al panel
		
		return panelTabla;
		
	}

}
