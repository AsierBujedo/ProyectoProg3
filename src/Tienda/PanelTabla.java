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
					String codigoProducto = tabla.getValueAt(tabla.getSelectedRow(), 0).toString();
					String nombre = tabla.getValueAt(tabla.getSelectedRow(), 1).toString();
					String precio = tabla.getValueAt(tabla.getSelectedRow(), 2).toString();
					String marca = tabla.getValueAt(tabla.getSelectedRow(), 3).toString();
					
					if (codigoProducto.contains("I")) {
						Cesta.cesta.add(new Impresora(codigoProducto, nombre, Double.valueOf(precio), marca));
					} else if (codigoProducto.contains("L")) {
						Cesta.cesta.add(new Libro(codigoProducto, nombre, Double.valueOf(precio), marca));
					} else if (codigoProducto.contains("O")) {
						Cesta.cesta.add(new Ordenador(codigoProducto, nombre, Double.valueOf(precio), marca));
					} else if (codigoProducto.contains("P")) {
						Cesta.cesta.add(new Pantalon(codigoProducto, nombre, Double.valueOf(precio), marca));
					} else if (codigoProducto.contains("S")) {
						Cesta.cesta.add(new Sudadera(codigoProducto, nombre, Double.valueOf(precio), marca));
					} else if (codigoProducto.contains("T")) {
						Cesta.cesta.add(new Telefono(codigoProducto, nombre, Double.valueOf(precio), marca));
					} else if (codigoProducto.contains("VC")) {
						Cesta.cesta.add(new Videoconsola(codigoProducto, nombre, Double.valueOf(precio), marca));
					} else if (codigoProducto.contains("VJ")) {
						Cesta.cesta.add(new Videojuego(codigoProducto, nombre, Double.valueOf(precio), marca));
					} else if (codigoProducto.contains("Z")) {
						Cesta.cesta.add(new Zapatilla(codigoProducto, nombre, Double.valueOf(precio), marca));
					} else if (codigoProducto.contains("VJ")) {
						Cesta.cesta.add(new Videojuego(codigoProducto, nombre, Double.valueOf(precio), marca));
					}
					
					System.out.println(Cesta.cesta);
					
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
	public static JPanel getPanelTablaCesta(String[] nomColumnas,  ArrayList<Producto> datos) {
		JPanel panelTablaCesta = new PanelTabla();
		panelTablaCesta.setLayout(new BorderLayout());
		panelTablaCesta.setBackground(Color.WHITE);
		
		ArrayList<DatoParaTabla> datosTabla = new ArrayList<DatoParaTabla>();
		
		for (Producto p : datos) {
			datosTabla.add(p);
		}
		
		JTable tabla = new JTable(new CustomTableModel(nomColumnas, datosTabla)); // Crea la tabla pasándole el modelo personalizado
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
		
		JButton actualizar = new JButton("Actualizar cesta");
		actualizar.setFont(new Font("Uni Sans Heavy", Font.BOLD, 15));
		actualizar.setForeground(Color.WHITE);
		actualizar.setBackground(Color.GREEN);

		realizarCompra.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				realizarCompra.setBorderPainted(false);
				realizarCompra.setBackground(new Color(92, 156, 180).darker());
			}

			public void mouseExited(MouseEvent evt) {
				realizarCompra.setBackground(new Color(92, 156, 180));
			}
		});
		
		actualizar.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				actualizar.setBorderPainted(false);
				actualizar.setBackground(Color.GREEN.darker());
			}

			public void mouseExited(MouseEvent evt) {
				actualizar.setBackground(Color.GREEN);
			}
		});

		realizarCompra.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tabla.getColumnCount() != 0 && tabla.getRowCount() != 0) {
					int reply = JOptionPane.showConfirmDialog(null, "¿Quieres realizar la compra?", "Mensaje", JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
					    JOptionPane.showMessageDialog(null, "Compra realizada");
					} else {
					    JOptionPane.showMessageDialog(null, "La compra ha sido cancelada");
					    // Aquí hay que limpiar la tabla (Eliminar todos los datos)
					}
				}
			}
				
		});
		
		actualizar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaTienda.panelTablaCesta = PanelTabla.getPanelTablaCesta(nomColumnas, Cesta.cesta);
				// Falla cesta
			}
				
		});
		
		botoneraBuscar.add(actualizar);
		botoneraComprar.add(realizarCompra, BorderLayout.EAST);
		
		panelTablaCesta.add(botoneraBuscar, BorderLayout.NORTH);
		panelTablaCesta.add(botoneraComprar, BorderLayout.SOUTH);
		
		JScrollPane panelScroll = new JScrollPane(tabla); // Crea un ScrollPane que contendrá la tabla
		panelTablaCesta.add(panelScroll); // Añade el ScrollPane al panel
		
		return panelTablaCesta;
		
	}

}
