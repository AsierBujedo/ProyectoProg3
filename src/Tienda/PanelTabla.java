package Tienda;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;

import BD.BaseDeDatos;

@SuppressWarnings("serial")
public class PanelTabla extends JPanel {
	public static ArrayList<DatoParaTabla> datosCesta = new ArrayList<DatoParaTabla>();
	public static String[] nomColumnasCesta = {};
	public static JButton realizarCompra = new JButton("Realizar compra");

	/**
	 * Crea un panel que contiene un JScrollPane con una tabla y un panel botonera con un botÃ³n para aÃ±adir productos a la cesta.
	 * @param nomColumnas Array con los nombres de las columnas de la tabla.
	 * @param datos ArrayList<DatoParaTabla> con los datos para la tabla.
	 * @param color Color que se le aplicarÃ¡ a la cabecera de la tabla.
	 * @return Panel que contiene la tabla y el panel botonera.
	 */
	public static JPanel getPanelTabla(String[] nomColumnas, ArrayList<DatoParaTabla> datos, Color color) {			
		JPanel panelTabla = new PanelTabla();
		panelTabla.setLayout(new BorderLayout());		
		CustomTableModel ctm = new CustomTableModel(nomColumnas, datos);
		JTable tabla = new JTable(ctm);
		tabla.setOpaque(true);
		TableRowSorter<CustomTableModel> sorter = new TableRowSorter<CustomTableModel>(ctm);
		tabla.setRowSorter(sorter);
		tabla.setOpaque(false);
		tabla.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		tabla.setForeground(Color.BLACK);
		tabla.getTableHeader().setBackground(color);
		tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
		tabla.getTableHeader().setForeground(Color.WHITE);
		tabla.getTableHeader().setReorderingAllowed(false);

		for (int i = 0; i < nomColumnas.length; i++) {
			tabla.getColumnModel().getColumn(i).setResizable(false);
		}
		
		// Renderer rendererPrecio
		DefaultTableCellRenderer rendererPrecio = new DefaultTableCellRenderer() {
		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		        c.setFont(new Font("Segoe UI", Font.BOLD, 15));
		        c.setForeground(Color.RED.darker());
		        return c;
		    }
		    
		};
		
		// Renderer rendererCodigo
		DefaultTableCellRenderer rendererCodigo = new DefaultTableCellRenderer() {
		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		        c.setFont(new Font("Segoe UI", Font.BOLD, 15));
		        c.setForeground(color.darker());
		        return c;
		    }
		    
		};
		
		rendererPrecio.setHorizontalAlignment(JLabel.CENTER);
		rendererCodigo.setHorizontalAlignment(JLabel.CENTER);
		tabla.getColumnModel().getColumn(2).setCellRenderer(rendererPrecio);
		tabla.getColumnModel().getColumn(0).setCellRenderer(rendererCodigo);		

		// Panel botonera
		JPanel botonera = new JPanel();
		botonera.setBackground(Color.WHITE);

		// Botón anyadir
		JButton anyadir = new JButton("Añadir a la cesta", new ImageIcon("add.png"));
		anyadir.setBorderPainted(false);
		anyadir.setFont(new Font("Segoe UI", Font.BOLD, 15));
		anyadir.setForeground(Color.WHITE);
		anyadir.setBackground(Color.GRAY.brighter());
		
		// Botón info
		JButton info = new JButton(new ImageIcon("info.png"));
		info.setBorderPainted(false);
		info.setBackground(new Color(88, 101, 242));
		
		// MouseListener anyadir
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
		
		// MouseListener info
		info.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				info.setBackground(new Color(88, 101, 242).darker());
			}

			public void mouseExited(MouseEvent evt) {
					info.setBackground(new Color(88, 101, 242));
			}
		});
		
		// ActionListener anyadir
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
						imp.setID(imp.getID() - 30 + 1100);
						Cesta.cesta.add(imp);
						datosCesta.add(imp);
					} else if (codigoProducto.contains("L")) {
						Libro lib = new Libro(codigoProducto, nombre, Double.valueOf(precio), marca);
						lib.setID(lib.getID() - 30 + 1100);
						Cesta.cesta.add(lib);
						datosCesta.add(lib);
					} else if (codigoProducto.contains("O")) {
						Ordenador o = new Ordenador(codigoProducto, nombre, Double.valueOf(precio), marca);
						o.setID(o.getID() - 30 + 1100);
						Cesta.cesta.add(o);
						datosCesta.add(o);
					} else if (codigoProducto.contains("P")) {
						Pantalon pant = new Pantalon(codigoProducto, nombre, Double.valueOf(precio), marca);
						pant.setID(pant.getID() - 30 + 1100);
						Cesta.cesta.add(pant);
						datosCesta.add(pant);
					} else if (codigoProducto.contains("S")) {
						Sudadera s = new Sudadera(codigoProducto, nombre, Double.valueOf(precio), marca);
						s.setID(s.getID() - 30 + 1100);
						Cesta.cesta.add(s);
						datosCesta.add(s);
					} else if (codigoProducto.contains("T")) {
						Telefono t = new Telefono(codigoProducto, nombre, Double.valueOf(precio), marca);
						t.setID(t.getID() - 30 + 1100);
						Cesta.cesta.add(t);
						datosCesta.add(t);
					} else if (codigoProducto.contains("VC")) {
						Videoconsola vc = new Videoconsola(codigoProducto, nombre, Double.valueOf(precio), marca);
						vc.setID(vc.getID() - 30 + 1100);
						Cesta.cesta.add(vc);
						datosCesta.add(vc);
					} else if (codigoProducto.contains("VJ")) {
						Videojuego vj = new Videojuego(codigoProducto, nombre, Double.valueOf(precio), marca);
						vj.setID(vj.getID() - 30 + 1100);
						Cesta.cesta.add(vj);
						datosCesta.add(vj);
					} else if (codigoProducto.contains("Z")) {
						Zapatilla z = new Zapatilla(codigoProducto, nombre, Double.valueOf(precio), marca);
						z.setID(z.getID() - 30 + 1100);
						Cesta.cesta.add(z);
						datosCesta.add(z);
					}

					CustomTableModel ctm = new CustomTableModel(nomColumnasCesta, datosCesta);
					tablaCesta.setModel(ctm);
					realizarCompra.setBackground(new Color(92, 156, 180));
					
					VentanaTienda.logger.log(Level.INFO, "Producto con código: " + codigoProducto + " añadido a la cesta");
				}
				
			}
		});
		
		// ActionListener info
		info.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Runnable r = () -> {
		            String html = "<html><body width='%1s'><h1>Código de producto</h1>"
		            		+ "<p style=\"text-align: justify;\"><strong>&Aacute;rea de Electr&oacute;nica&nbsp; &nbsp;</strong><strong>- &nbsp;&nbsp;</strong><strong>I:</strong> Impresora &nbsp; <strong>O:</strong> Ordenador &nbsp; <strong>T:</strong> Tel&eacute;fono</p>"
		            		+ "<p style=\"text-align: justify;\"><strong>&Aacute;rea de Ropa</strong>&nbsp; &nbsp;<strong>-</strong>&nbsp; &nbsp;<strong>P:</strong> Pantalones &nbsp; <strong>S:</strong> Sudadera &nbsp; <strong>Z:</strong> Zapatillas</p>"
		            		+ "<p style=\"text-align: justify;\"><strong>&Aacute;rea de Hobby</strong>&nbsp; &nbsp;<strong>-</strong>&nbsp; &nbsp;<strong>L:</strong> Libro &nbsp; <strong>VC:</strong> Videoconsola &nbsp; <strong>VJ:</strong> Videojuego</p>"
		            		;
		            
		            int w = 400;

		            JOptionPane.showMessageDialog(null, String.format(html, w, w), "Información", JOptionPane.INFORMATION_MESSAGE);
		        };
		        SwingUtilities.invokeLater(r);
		        VentanaTienda.logger.log(Level.INFO, "Información desplegada");
			}
		});
		
		tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				anyadir.setBackground(color);

			}
		});

		botonera.add(anyadir, BorderLayout.CENTER);
		botonera.add(info);
		panelTabla.add(botonera, BorderLayout.SOUTH);

		JScrollPane panelScroll = new JScrollPane(tabla);
		panelTabla.add(panelScroll, BorderLayout.CENTER);

		return panelTabla;
	}

	public static JTable tablaCesta;

	/** 
	 * Crea un panel que contiene un JScrollPane con una tabla y dos paneles botonera con los elementos necesarios para realizar bÃºsquedas y compras de productos.
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
		
		CustomTableModel ctm = new CustomTableModel(nomColumnas, datosTabla);
		tablaCesta = new JTable(ctm);
		tablaCesta.setOpaque(true);
		tablaCesta.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		tablaCesta.setForeground(Color.BLACK);
		tablaCesta.getTableHeader().setBackground(new Color(162, 195, 234));
		tablaCesta.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
		tablaCesta.getTableHeader().setForeground(Color.WHITE);
		tablaCesta.getTableHeader().setReorderingAllowed(false);
		
		for (int i = 0; i < nomColumnas.length; i++) {
			tablaCesta.getColumnModel().getColumn(i).setResizable(false);
		}		
		
		// Renderer rendererCesta		
		DefaultTableCellRenderer rendererCesta = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			setHorizontalAlignment(JLabel.CENTER);
			c.setFont(column == 2 ? new Font("Segoe UI", Font.BOLD, 15) : null);
			c.setForeground(column == 2 ? Color.RED.darker() : null);
			c.setBackground(row % 2 == 0 ? new Color(241, 238, 230) : Color.WHITE);
			return c;
			}
		};
		
		tablaCesta.setDefaultRenderer(Object.class, rendererCesta);
		tablaCesta.setDefaultRenderer(Double.class, rendererCesta);
		tablaCesta.setDefaultRenderer(Integer.class, rendererCesta);
								
		// Panel botoneraBuscar
		JPanel botoneraBuscar = new Cesta().panelCesta();
		botoneraBuscar.setBackground(Color.WHITE);
		
		// Panel botoneraComprar
		JPanel botoneraComprar = new JPanel();
		botoneraComprar.setBackground(Color.WHITE);
		
		// Botón realizarCompra
		realizarCompra = new JButton("Realizar compra");
		realizarCompra.setBorderPainted(false);
		realizarCompra.setFont(new Font("Segoe UI", Font.BOLD, 15));
		realizarCompra.setForeground(Color.WHITE);
		realizarCompra.setBackground(Color.GRAY.brighter());
		
		// Botón eliminarProducto
		JButton eliminarProducto = new JButton("Eliminar producto");
		eliminarProducto.setBorderPainted(false);
		eliminarProducto.setFont(new Font("Segoe UI", Font.BOLD, 15));
		eliminarProducto.setForeground(Color.WHITE);
		eliminarProducto.setBackground(Color.GRAY.brighter());
		
		// MouseListener realizarCompra
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
		
		// MouseListener eliminarProducto
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
		
		// ActionListener realizarCompra
		realizarCompra.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {				
				if (tablaCesta.getRowCount() != 0) {
					int reply = JOptionPane.showConfirmDialog(null, "¿Quieres realizar la compra?", "Mensaje", JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						
						if (!VentanaTienda.loginItem.getText().equals("Login")) {
							Cesta.lastCompra.put(BaseDeDatos.getUserMail(VentanaTienda.loginItem.getText()), Cesta.cesta);
							CustomTableModel model = new CustomTableModel(nomColumnas, new ArrayList<DatoParaTabla>());
							tablaCesta.setModel(model);
							Cesta.cesta = new ArrayList<Producto>();
							datosCesta = new ArrayList<DatoParaTabla>();
							BaseDeDatos.addCompra(BaseDeDatos.getUserMail(VentanaTienda.loginItem.getText()));
							JOptionPane.showMessageDialog(null, "Compra realizada", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
							VentanaTienda.logger.log(Level.INFO, "Compra realizada");
						} else {
							JOptionPane.showMessageDialog(null, "Antes de comprar, debes iniciar sesión", "Advertencia",
									JOptionPane.WARNING_MESSAGE);
						}
					} else {
					    JOptionPane.showMessageDialog(null, "La compra ha sido cancelada", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
				
		});
		
		// ActionListener eliminarProducto
		eliminarProducto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tablaCesta.getRowCount() != 0) {
					int reply = JOptionPane.showConfirmDialog(null, "¿Quieres eliminar el producto de la cesta?", "Mensaje", JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						
						int ID = (int) tablaCesta.getValueAt(tablaCesta.getSelectedRow(), 4);
						
						for (int i = 0; i < Cesta.cesta.size(); i++) {
							if (Cesta.cesta.get(i).getID() == ID) {
								Cesta.cesta.remove(i);
							}
						}
						
						CustomTableModel model = (CustomTableModel) tablaCesta.getModel();
						model.removeRow(tablaCesta.getSelectedRow());
						model.fireTableDataChanged();						
						tablaCesta.repaint();
						eliminarProducto.setBackground(Color.GRAY.brighter());
						
						if (tablaCesta.getRowCount() == 0) {
							realizarCompra.setBackground(Color.GRAY.brighter());
						}
						
						VentanaTienda.logger.log(Level.INFO, "Producto con ID: " + ID + " eliminado");
						JOptionPane.showMessageDialog(null, "Producto eliminado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "No se eliminó ningún producto", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					} 
				}
				
				
			}
		});
		
		// ListSelectionListener tablaCesta
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
