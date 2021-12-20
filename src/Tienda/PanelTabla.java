package Tienda;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import BD.BaseDeDatos;

@SuppressWarnings("serial")
public class PanelTabla extends JPanel {
	
	/** 
	 * Crea un panel que contiene un JScrollPane con una tabla y un panel botonera con un botón para añadir productos a la cesta.
	 * @param nomColumnas Array con los nombres de las columnas de la tabla.
	 * @param datos ArrayList<DatoParaTabla> con los datos para la tabla.
	 * @param color Color que se le aplicará a la cabecera de la tabla.
	 * @return Panel que contiene la tabla y el panel botonera.
	 */
	public static JPanel getPanelTabla(String[] nomColumnas,  ArrayList<DatoParaTabla> datos, Color color) {
		JPanel panelTabla = new PanelTabla();
		panelTabla.setLayout(new BorderLayout());
		JTable tabla = new JTable(new CustomTableModel(nomColumnas, datos)); // Crea la tabla pasándole el modelo personalizado
		tabla.setOpaque(true);
		tabla.setFont(new Font("Uni Sans Heavy", Font.PLAIN, 15));
		tabla.setForeground(Color.BLACK);
		tabla.getTableHeader().setBackground(color);
		tabla.getTableHeader().setFont(new Font("Uni Sans Heavy", Font.BOLD, 15));
		tabla.getTableHeader().setForeground(Color.WHITE);
		tabla.getTableHeader().setReorderingAllowed(false);
		
		for (int i = 0; i < nomColumnas.length; i++) {
			tabla.getColumnModel().getColumn(i).setResizable(false);
		}
		
		// Panel botonera
		JPanel botonera = new JPanel();
		botonera.setBackground(Color.WHITE);
		
		// Botón anyadir
		JButton anyadir = new JButton("Añadir a la cesta");
		anyadir.setFont(new Font("Uni Sans Heavy", Font.BOLD, 15));
		anyadir.setForeground(Color.WHITE);
		anyadir.setBackground(color);

		anyadir.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				anyadir.setBorderPainted(false);
				anyadir.setBackground(color.darker());
			}

			public void mouseExited(MouseEvent evt) {
				anyadir.setBackground(color);
			}
		});

		anyadir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!tabla.getSelectionModel().isSelectionEmpty()) {
					String valor = (String) tabla.getValueAt(tabla.getSelectedRow(), 0);
					System.out.println(valor);
					// actionPerformed del botón aun sin terminar
					// Haciéndolo de esta forma, ¿Cómo vamos a pasarle la lista de productos seleccionados a getPanelTablaCesta(nomColumnas, ?)?
				}
			}
		});

		botonera.add(anyadir, BorderLayout.CENTER);
		panelTabla.add(botonera, BorderLayout.SOUTH);		
		
		JScrollPane panelScroll = new JScrollPane(tabla); // Crea un ScrollPane que contendrá la tabla
		panelTabla.add(panelScroll, BorderLayout.CENTER); // Añade el ScrollPane al panel
		
		return panelTabla;
		
	}
	
	/** 
	 * Crea un panel que contiene un JScrollPane con una tabla y dos paneles botonera con los elementos necesarios para realizar búsquedas y compras de productos.
	 * @param nomColumnas Array con los nombres de las columnas de la tabla.
	 * @param datos ArrayList<DatoParaTabla> con los datos para la tabla.
	 * @return Panel que contiene la tabla y los paneles botonera.
	 */
	public static JPanel getPanelTablaCesta(String[] nomColumnas,  ArrayList<DatoParaTabla> datos) {
		JPanel panelTablaCesta = new PanelTabla();
		panelTablaCesta.setLayout(new BorderLayout());
		panelTablaCesta.setBackground(Color.WHITE);
		
		JTable tabla = new JTable(new CustomTableModel(nomColumnas, datos)); // Crea la tabla pasándole el modelo personalizado
		tabla.setOpaque(true);
		tabla.setFont(new Font("Uni Sans Heavy", Font.PLAIN, 15));
		tabla.setForeground(Color.BLACK);
		tabla.getTableHeader().setBackground(new Color(162, 195, 234));
		tabla.getTableHeader().setFont(new Font("Uni Sans Heavy", Font.BOLD, 15));
		tabla.getTableHeader().setForeground(Color.WHITE);
		tabla.getTableHeader().setReorderingAllowed(false);
		
		for (int i = 0; i < nomColumnas.length; i++) {
			tabla.getColumnModel().getColumn(i).setResizable(false);
		}
		
		// Panel botoneraBuscar
		JPanel botoneraBuscar = new Cesta().panelCesta();
		botoneraBuscar.setBackground(Color.WHITE);
		
		// Panel botoneraComprar
		JPanel botoneraComprar = new JPanel();
		botoneraComprar.setLayout(new BorderLayout());
		botoneraComprar.setBackground(Color.WHITE);
		
		// Botón realizarCompra
		JButton realizarCompra = new JButton("Realizar compra");
		realizarCompra.setFont(new Font("Uni Sans Heavy", Font.BOLD, 15));
		realizarCompra.setForeground(Color.WHITE);
		realizarCompra.setBackground(new Color(92, 156, 180));

		realizarCompra.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				realizarCompra.setBorderPainted(false);
				realizarCompra.setBackground(new Color(92, 156, 180).darker());
			}

			public void mouseExited(MouseEvent evt) {
				realizarCompra.setBackground(new Color(92, 156, 180));
			}
		});

		realizarCompra.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "¿Quieres realizar la compra?", "Mensaje", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
				    JOptionPane.showMessageDialog(null, "La compra ha sido cancelada");
				} else {
				    JOptionPane.showMessageDialog(null, "Compra realizada");
				    // Aquí hay que limpiar la tabla (Eliminar todos los datos)
//				    System.exit(0);
				}
			}
		});

		botoneraComprar.add(realizarCompra, BorderLayout.EAST);
		
		panelTablaCesta.add(botoneraBuscar, BorderLayout.NORTH);
		panelTablaCesta.add(botoneraComprar, BorderLayout.SOUTH);
		
		JScrollPane panelScroll = new JScrollPane(tabla); // Crea un ScrollPane que contendrá la tabla
		panelTablaCesta.add(panelScroll); // Añade el ScrollPane al panel
		
		return panelTablaCesta;
		
	}
	
	//Añadir botón "añadir a la cesta" en esta clase, devolveremos el panel con el ActionListener ya creado desde aquí
	//De esta forma, podemos acceder a los datos de la tabla sin problemas.
	//Poniendo el listener desde VentanaTienda no puedes acceder a los datos, por tanto, no puedes añadir nada a la cesta.
	//tabla.getvalueAt(int row, int column);
	//Mas información en https://stackoverflow.com/questions/29345792/java-jtable-getting-the-data-of-the-selected-row

}
