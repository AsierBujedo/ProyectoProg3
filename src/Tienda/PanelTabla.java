package Tienda;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import BD.BaseDeDatos;

@SuppressWarnings("serial")
public class PanelTabla extends JPanel {
	public static ArrayList<DatoParaTabla> datosCesta = new ArrayList<DatoParaTabla>();
	public static String[] nomColumnasCesta = {};
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
		CustomTableModel ctm = new CustomTableModel(nomColumnas, datos);
		JTable tabla = new JTable(ctm); // Crea la tabla pasándole el modelo personalizado
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
		JButton anyadir = new JButton("Añadir a la cesta", new ImageIcon("add.png"));
		anyadir.setBorderPainted(false);
		anyadir.setFont(new Font("Uni Sans Heavy", Font.BOLD, 15));
		anyadir.setForeground(Color.WHITE);
		anyadir.setBackground(color);

		anyadir.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
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
						Impresora imp = new Impresora(codigoProducto, nombre, Double.valueOf(precio), marca);
						imp.setID(imp.getID()-30+1100);
						Cesta.cesta.add(imp);
						datosCesta.add(imp);
					} else if (codigoProducto.contains("L")) {
						Libro lib = new Libro(codigoProducto, nombre, Double.valueOf(precio), marca);
						lib.setID(lib.getID()-30+1100);
						Cesta.cesta.add(lib);
						datosCesta.add(lib);
					} else if (codigoProducto.contains("O")) {
						Ordenador o = new Ordenador(codigoProducto, nombre, Double.valueOf(precio), marca);
						o.setID(o.getID()-30+1100);
						Cesta.cesta.add(o);
						datosCesta.add(o);
					} else if (codigoProducto.contains("P")) {
						Pantalon pant = new Pantalon(codigoProducto, nombre, Double.valueOf(precio), marca);
						pant.setID(pant.getID()-30+1100);
						Cesta.cesta.add(pant);
						datosCesta.add(pant);
					} else if (codigoProducto.contains("S")) {
						Sudadera s = new Sudadera(codigoProducto, nombre, Double.valueOf(precio), marca);
						s.setID(s.getID()-30+1100);
						Cesta.cesta.add(s);
						datosCesta.add(s);
					} else if (codigoProducto.contains("T")) {
						Telefono t = new Telefono(codigoProducto, nombre, Double.valueOf(precio), marca);
						t.setID(t.getID()-30+1100);
						Cesta.cesta.add(t);
						datosCesta.add(t);
					} else if (codigoProducto.contains("VC")) {
						Videoconsola vc = new Videoconsola(codigoProducto, nombre, Double.valueOf(precio), marca);
						vc.setID(vc.getID()-30+1100);
						Cesta.cesta.add(vc);
						datosCesta.add(vc);
					} else if (codigoProducto.contains("VJ")) {
						Videojuego vj = new Videojuego(codigoProducto, nombre, Double.valueOf(precio), marca);
						vj.setID(vj.getID()-30+1100);
						Cesta.cesta.add(vj);
						datosCesta.add(new Videojuego(codigoProducto, nombre, Double.valueOf(precio), marca));
					} else if (codigoProducto.contains("Z")) {
						Zapatilla z = new Zapatilla(codigoProducto, nombre, Double.valueOf(precio), marca);
						z.setID(z.getID()-30+1100);
						Cesta.cesta.add(z);
						datosCesta.add(z);
					}
					
					CustomTableModel ctm = new CustomTableModel(nomColumnasCesta, datosCesta);
					tablaCesta.setModel(ctm);
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
	
	
	public static JTable tablaCesta;
	/** 
	 * Crea un panel que contiene un JScrollPane con una tabla y dos paneles botonera con los elementos necesarios para realizar búsquedas y compras de productos.
	 * @param nomColumnas Array con los nombres de las columnas de la tabla.
	 * @param datos ArrayList<DatoParaTabla> con los datos para la tabla.
	 * @return Panel que contiene la tabla y los paneles botonera.
	 */
	public static JPanel getPanelTablaCesta(String[] nomColumnas,  ArrayList<Producto> datos) {
		nomColumnasCesta = nomColumnas;
		for (Producto p : datos) {
			datosCesta.add(p);
		}
		JPanel panelTablaCesta = new PanelTabla();
		panelTablaCesta.setLayout(new BorderLayout());
		panelTablaCesta.setBackground(Color.WHITE);
		
		ArrayList<DatoParaTabla> datosTabla = new ArrayList<DatoParaTabla>();
		
		for (Producto p : datos) {
			datosTabla.add(p);
		}
		
		tablaCesta = new JTable(new CustomTableModel(nomColumnas, datosTabla)); // Crea la tabla pasándole el modelo personalizado
		tablaCesta.setOpaque(true);
		tablaCesta.setFont(new Font("Uni Sans Heavy", Font.PLAIN, 15));
		tablaCesta.setForeground(Color.BLACK);
		tablaCesta.getTableHeader().setBackground(new Color(162, 195, 234));
		tablaCesta.getTableHeader().setFont(new Font("Uni Sans Heavy", Font.BOLD, 15));
		tablaCesta.getTableHeader().setForeground(Color.WHITE);
		tablaCesta.getTableHeader().setReorderingAllowed(false);
		
		for (int i = 0; i < nomColumnas.length; i++) {
			tablaCesta.getColumnModel().getColumn(i).setResizable(false);
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
		realizarCompra.setBorderPainted(false);
		realizarCompra.setFont(new Font("Uni Sans Heavy", Font.BOLD, 15));
		realizarCompra.setForeground(Color.WHITE);
		realizarCompra.setBackground(new Color(92, 156, 180));
		
		JButton actualizar = new JButton("Actualizar cesta", new ImageIcon("loader.gif"));
		actualizar.setBorderPainted(false);
		actualizar.setFont(new Font("Uni Sans Heavy", Font.BOLD, 15));
		actualizar.setForeground(Color.WHITE);
		actualizar.setBackground(new Color(146, 201, 142));

		realizarCompra.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				realizarCompra.setBackground(new Color(92, 156, 180).darker());
			}

			public void mouseExited(MouseEvent evt) {
				realizarCompra.setBackground(new Color(92, 156, 180));
			}
		});
		
		actualizar.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				actualizar.setBackground(new Color(146, 201, 142).darker());
			}

			public void mouseExited(MouseEvent evt) {
				actualizar.setBackground(new Color(146, 201, 142));
			}
		});

		realizarCompra.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tablaCesta.getColumnCount() != 0 && tablaCesta.getRowCount() != 0) {
					int reply = JOptionPane.showConfirmDialog(null, "¿Quieres realizar la compra?", "Mensaje", JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						// Aquí hay que limpiar la tabla (Eliminar todos los datos)
					    JOptionPane.showMessageDialog(null, "Compra realizada");
					} else {
					    JOptionPane.showMessageDialog(null, "La compra ha sido cancelada");
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
		
		JScrollPane panelScroll = new JScrollPane(tablaCesta); // Crea un ScrollPane que contendrá la tabla
		panelTablaCesta.add(panelScroll); // Añade el ScrollPane al panel
		
		return panelTablaCesta;
		
	}

}
