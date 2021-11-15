package Tienda;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class PanelTabla extends JPanel {
	
	public PanelTabla() {
		this.setLayout(new BorderLayout());
		
		JTable tabla = new JTable(new CustomTableModel()); // Crea la tabla pasándole el modelo personalizado
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
