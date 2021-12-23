package Tienda;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class PanelTabla extends JPanel {
	public static ArrayList<DatoParaTabla> datosCesta = new ArrayList<DatoParaTabla>();
	public static String[] nomColumnasCesta = {};
	public static JButton realizarCompra = new JButton("Realizar compra");
	
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
		JTable tabla = new JTable(ctm);
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
		anyadir.setBackground(Color.GRAY.brighter());
		
		anyadir.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				if (!tabla.getSelectionModel().isSelectionEmpty()) {
					anyadir.setBackground(color.darker());
				}
			}

			public void mouseExited(MouseEvent evt) {
				if (!tabla.getSelectionModel().isSelectionEmpty()) {
					anyadir.setBackground(color);
				}
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
						datosCesta.add(vj);
					} else if (codigoProducto.contains("Z")) {
						Zapatilla z = new Zapatilla(codigoProducto, nombre, Double.valueOf(precio), marca);
						z.setID(z.getID()-30+1100);
						Cesta.cesta.add(z);
						datosCesta.add(z);
					}
					
					CustomTableModel ctm = new CustomTableModel(nomColumnasCesta, datosCesta);
					tablaCesta.setModel(ctm);
					realizarCompra.setBackground(new Color(92, 156, 180));
					System.out.println(Cesta.cesta);
				}
			}
		});
		
		tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				anyadir.setBackground(color);
				
			}	        
	    });

		botonera.add(anyadir, BorderLayout.CENTER);
		panelTabla.add(botonera, BorderLayout.SOUTH);		
		
		JScrollPane panelScroll = new JScrollPane(tabla);
		panelTabla.add(panelScroll, BorderLayout.CENTER);
		
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
		botoneraComprar.setBackground(Color.WHITE);
		
		// Botón realizarCompra
		realizarCompra = new JButton("Realizar compra");
		realizarCompra.setBorderPainted(false);
		realizarCompra.setFont(new Font("Uni Sans Heavy", Font.BOLD, 15));
		realizarCompra.setForeground(Color.WHITE);
		realizarCompra.setBackground(Color.GRAY.brighter());
		
		// Botón eliminarProducto
		JButton eliminarProducto = new JButton("Eliminar producto");
		eliminarProducto.setBorderPainted(false);
		eliminarProducto.setFont(new Font("Uni Sans Heavy", Font.BOLD, 15));
		eliminarProducto.setForeground(Color.WHITE);
		eliminarProducto.setBackground(Color.GRAY.brighter());

		realizarCompra.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				if (tablaCesta.getRowCount() != 0) {
					realizarCompra.setBackground(new Color(92, 156, 180).darker());
				}
			}

			public void mouseExited(MouseEvent evt) {
				if (tablaCesta.getRowCount() != 0) {
					realizarCompra.setBackground(new Color(92, 156, 180));
				}
			}
		});

		realizarCompra.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Producto> productosCompra = new ArrayList<Producto>(); 
				// ArrayList en el que se guardarán los productos comprados
				// Este ArrayList hay que introducirlo en el HashMap lastCompra de la clase Cesta
				
				if (tablaCesta.getRowCount() != 0) {
					int reply = JOptionPane.showConfirmDialog(null, "¿Quieres realizar la compra?", "Mensaje", JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						
						while (tablaCesta.getRowCount()>0) {
							CustomTableModel model = (CustomTableModel) tablaCesta.getModel();
							model.removeRow(0);
							model.fireTableDataChanged();
							tablaCesta.repaint();
							realizarCompra.setBackground(Color.GRAY.brighter());
				        }
						// Añadir la compra al HashMap lastCompra de la clase Cesta
						
					    JOptionPane.showMessageDialog(null, "Compra realizada", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					} else {
					    JOptionPane.showMessageDialog(null, "La compra ha sido cancelada", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
				
		});
		
		eliminarProducto.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				if (!tablaCesta.getSelectionModel().isSelectionEmpty()) {
					eliminarProducto.setBackground(new Color(180, 56, 61).darker());
				}
			}

			public void mouseExited(MouseEvent evt) {
				if (!tablaCesta.getSelectionModel().isSelectionEmpty()) {
					eliminarProducto.setBackground(new Color(180, 56, 61));
				}
			}
		});
		
		eliminarProducto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tablaCesta.getRowCount() != 0) {
					int reply = JOptionPane.showConfirmDialog(null, "¿Quieres eliminar el producto de la cesta?", "Mensaje", JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						CustomTableModel model = (CustomTableModel) tablaCesta.getModel();
						model.removeRow(tablaCesta.getSelectedRow());
						model.fireTableDataChanged();						
						tablaCesta.repaint();
						eliminarProducto.setBackground(Color.GRAY.brighter());
						
						if (tablaCesta.getRowCount() == 0) {
							realizarCompra.setBackground(Color.GRAY.brighter());
						}
						
						JOptionPane.showMessageDialog(null, "Producto eliminado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
						
					} else {
						JOptionPane.showMessageDialog(null, "No se eliminó ningún producto", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					} 
				}				
			}
		});
		
		tablaCesta.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				eliminarProducto.setBackground(new Color(180, 56, 61));
				
			}	        
	    });
		
		botoneraComprar.add(eliminarProducto);
		botoneraComprar.add(realizarCompra);
		
		panelTablaCesta.add(botoneraBuscar, BorderLayout.NORTH);
		panelTablaCesta.add(botoneraComprar, BorderLayout.SOUTH);
		
		JScrollPane panelScroll = new JScrollPane(tablaCesta);
		panelTablaCesta.add(panelScroll);
		
		return panelTablaCesta;
		
	}

}
