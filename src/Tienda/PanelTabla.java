package Tienda;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class PanelTabla extends JPanel {
	
	public PanelTabla() {
		this.setLayout(new BorderLayout());
		
		String[] nomColumnas = {"Columna 1", "Columna 2", "Columna 3"};
		ArrayList<DatoParaTabla> datos = new ArrayList<DatoParaTabla>();
		
		datos.add(new PersonaPrueba("Asier", "Bujedo", 19, "00000000E"));
		datos.add(new PersonaPrueba("Eduardo", "Larrinaga", 19, "11111111E"));
		datos.add(new PersonaPrueba("Iker", "López", 21, "22222222E"));
		datos.add(new PersonaPrueba("Mikel", "Lambarri", 19, "33333333E"));
		
		JTable tabla = new JTable(new CustomTableModel(nomColumnas, datos)); // Crea la tabla pasándole el modelo personalizado
		JScrollPane panelScroll = new JScrollPane(tabla); // Crea el ScrollPane que va a contener la tabla
		
		this.add(panelScroll); // Añade el ScrollPane al panel
	}
	
	/**
	 * Devuelve un panel que contiene una JTable con modelo CustomTableModel
	 * @return panelTabla Panel que contiene la JTable
	 */
	public static JPanel getPanelTabla() {
		JPanel panelTabla = new PanelTabla();
		return panelTabla;
		
	}

}
