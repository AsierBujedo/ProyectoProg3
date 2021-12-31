package Utils;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import BD.BaseDeDatos;
import BD.COLS;
import Tienda.VentanaTienda;
import Tienda.Cesta;
import Tienda.PanelTabla;
import Tienda.Producto;
import Tienda.Cesta.*;
import Tienda.DatoParaTabla;

public class OtherFrames {
	private static double precio = 0.0;

	// Acceso al area personal
	public static void areaCliente() {
		JFrame frame = new JFrame();
		JButton contra = new JButton("Cambiar contraseña");
		contra.setBorder(new RoundedBorder(7));
		JButton historial = new JButton("Ultima compra");
		historial.setBorder(new RoundedBorder(7));
		frame.setLayout(new GridLayout(2, 1));
		historial.setFont(new Font("Uni Sans Heavy", Font.ROMAN_BASELINE, 15));
		contra.setFont(new Font("Uni Sans Heavy", Font.ROMAN_BASELINE, 15));
		frame.add(contra);
		frame.add(historial);

		contra.addActionListener(new ActionListener() {// Ventana para cambio de contraseña

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				JLabel contraV = new JLabel("Contraseña antigua: ");
				JPasswordField contraVtf = new JPasswordField(20);
				contraVtf.setBorder(new RoundedBorder(7));
				JLabel contraN = new JLabel("Contraseña Nueva: ");
				JPasswordField contraNtf = new JPasswordField(20);
				contraNtf.setBorder(new RoundedBorder(7));
				JLabel contraNC = new JLabel("Confirma la contraseña nueva: ");
				JPasswordField contraNCtf = new JPasswordField(20);
				contraNCtf.setBorder(new RoundedBorder(7));
				JButton cambiar = new JButton("Cambiar contraseña");
				JPanel panel = new JPanel();
				panel.setLayout(new GridLayout(3, 1));
				panel.add(contraV, BorderLayout.NORTH);
				panel.add(contraVtf, BorderLayout.CENTER);
				panel.add(contraN, BorderLayout.NORTH);
				panel.add(contraNtf, BorderLayout.CENTER);
				panel.add(contraNC, BorderLayout.NORTH);
				panel.add(contraNCtf, BorderLayout.CENTER);
				frame.add(panel, BorderLayout.CENTER);
				frame.add(cambiar, BorderLayout.SOUTH);

				cambiar.addActionListener(new ActionListener() {// Boton para cambio de contraseñas

					@Override
					public void actionPerformed(ActionEvent e) {
						String nombre = VentanaTienda.loginItem.getText();
						String mail = BaseDeDatos.getUserMail(nombre);
						String existe = BaseDeDatos.getUser(mail, String.valueOf(contraVtf.getPassword()));
						if (String.valueOf(contraNtf.getPassword()).equals(String.valueOf(contraNCtf.getPassword()))
								&& !existe.equals("Error")) {
							BaseDeDatos.editUser(COLS.PASS, mail, String.valueOf(contraVtf.getPassword()),
									String.valueOf(contraNtf.getPassword()));
						} else {
							JOptionPane.showMessageDialog(null, "Alguna de las contraseñas no coincide", "Error", 0);
							frame.dispose();
						}
						frame.dispose();
					}
				});

				frame.setTitle("Cambio de contraseña");
				frame.setSize(400, 225);
				frame.setResizable(false);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				frame.setIconImage(VentanaTienda.icon);
				frame.setLocationRelativeTo(null);
			}
		});
		historial.addActionListener(new ActionListener() {// ventana para ultima compra

			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = VentanaTienda.loginItem.getText();
				String mail = BaseDeDatos.getUserMail(nombre);
				if (Cesta.lastCompra.containsKey(mail)) {
					JFrame frame = new JFrame();
					Vector<String> nomColumnas = new Vector<String>(
							Arrays.asList("Código de producto", "Nombre", "Precio", "Marca"));
					String[] nomColumnasInArray = { "Código de producto", "Nombre", "Precio", "Marca" };
					JTable tabla = new JTable();
					DefaultTableModel model = new DefaultTableModel(new Vector<Vector<Object>>(), nomColumnas);
					tabla.getTableHeader().setReorderingAllowed(false);
					tabla.setFont(new Font("Uni Sans Heavy", Font.PLAIN, 15));
					tabla.getTableHeader().setBackground(Color.GREEN.darker());
					precio = 0.0;
					for (Producto p : Cesta.lastCompra.get(mail)) {
						model.addRow(
								new Object[] { p.getCodigoProducto(), p.getNombre(), p.getPrecio(), p.getMarca() });
						precio += p.getPrecio();
					}
					JLabel info = new JLabel();
					info.setFont(new Font("Uni Sans Heavy", Font.BOLD, 14));
					info.setBackground(Color.YELLOW.darker());
					info.setOpaque(true);
					tabla.setModel(model);
					frame.add(new JScrollPane(tabla), BorderLayout.CENTER);
					frame.add(info, BorderLayout.SOUTH);
					frame.setTitle("Última compra");
					frame.setSize(800, 500);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					frame.setIconImage(VentanaTienda.icon);
					Thread infoTh = new Thread(new Runnable() {

						@Override
						public void run() {
							while (!Thread.interrupted()) {
								info.setText("Precio total: " + OtherUtils.round(precio, 2) + "€");
								try {
									Thread.sleep(3000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								info.setText("Cantidad de productos: " + Cesta.lastCompra.get(mail).size() + " uds.");
								try {
									Thread.sleep(3000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					});
					infoTh.start();

				} else {
					JOptionPane.showMessageDialog(null, "Tu usuario no tiene ninguna compra registrada",
							"Compra no encontrada", 1);
				}
			}
		});

		frame.setTitle("Registro de usuarios");
		frame.setSize(400, 225);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setIconImage(VentanaTienda.icon);
	}
	
	
	// VENTANA ESTADISTICAS
	
//		COMPROBACION
	
//		public static void main(String[] args) {
//		VentanaTienda.iniciaLog();
//		BaseDeDatos.InitDB();
//		new OtherFrames().VentanaStats();
//		BaseDeDatos.closeDB();
//	
//	}
	
	public static void VentanaStats() {
		JFrame frame = new JFrame();
		JPanel principal = new JPanel();
		principal.setBackground(Color.WHITE);
		principal.setForeground(Color.WHITE);
		principal.setLayout(null);
		
		JLabel lblDinero = new JLabel("Dinero recaudado: ");
		lblDinero.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		lblDinero.setBounds(44, 85, 125, 22);
		principal.add(lblDinero);
		
		JTextField txtDinero = new JTextField();
		txtDinero.setEditable(false);
		txtDinero.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtDinero.setBounds(185, 80, 230, 30);
		principal.add(txtDinero);
		txtDinero.setColumns(10);
		txtDinero.setBorder(new RoundedBorder(7));
	
		txtDinero.setText(BaseDeDatos.getPrecioCompras() + " €");
		
		JLabel lblClientes = new JLabel("N\u00FAmero de clientes:");
		lblClientes.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		lblClientes.setBounds(42, 130, 125, 22);
		principal.add(lblClientes);
		
		JTextField txtClientes = new JTextField();
		txtClientes.setEditable(false);
		txtClientes.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtClientes.setColumns(10);
		txtClientes.setBorder(new RoundedBorder(7));
		txtClientes.setBounds(185, 125, 230, 30);
		principal.add(txtClientes);
		
		txtClientes.setText(BaseDeDatos.getTotalClientes() + "");
		
		JLabel lblPedidos = new JLabel("Número de pedidos:");
		lblPedidos.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		lblPedidos.setBounds(42, 175, 145, 22);
		principal.add(lblPedidos);
		
		JTextField txtPedidos = new JTextField();
		txtPedidos.setEditable(false);
		txtPedidos.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtPedidos.setColumns(10);
		txtPedidos.setBorder(new RoundedBorder(7));
		txtPedidos.setBounds(185, 167, 230, 30);
		principal.add(txtPedidos);
		
		txtPedidos.setText(BaseDeDatos.getNumeroCompras() + "");
		
		JLabel lblCompras = new JLabel("Media de compras:");
		lblCompras.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		lblCompras.setBounds(42, 213, 145, 22);
		principal.add(lblCompras);
		
		JTextField txtCompras = new JTextField();
		txtCompras.setEditable(false);
		txtCompras.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtCompras.setColumns(10);
		txtCompras.setBorder(new RoundedBorder(7));
		txtCompras.setBounds(185, 210, 230, 30);
		principal.add(txtCompras);
		
	//	FALTA POR REALIZAR	
	//	txtCompras.setText(BaseDeDatos.getMediaCompras() + "");
		
		JSeparator lineDinero = new JSeparator();
		lineDinero.setBounds(44, 106, 110, 2);
		principal.add(lineDinero);
		
		JSeparator lineClientes = new JSeparator();
		lineClientes.setBounds(44, 150, 120, 2);
		principal.add(lineClientes);
		
		JSeparator linePedidos = new JSeparator();
		linePedidos.setBounds(44, 195, 120, 2);
		principal.add(linePedidos);
		
		JSeparator lineCompras = new JSeparator();
		lineCompras.setBounds(44, 233, 115, 2);
		principal.add(lineCompras);
		
		JButton btnGuardar = new JButton();
		btnGuardar.setBackground(Color.WHITE);
		btnGuardar.setForeground(Color.BLACK);
		btnGuardar.setIcon(new ImageIcon("save.png"));
		btnGuardar.setBounds(165, 255, 40, 40);
		btnGuardar.setBorder(new RoundedBorder(7));
		btnGuardar.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		principal.add(btnGuardar);
		
		JLabel lblGuardar = new JLabel("Guardar en fichero");
		lblGuardar.setBounds(215, 260, 125, 30);
		principal.add(lblGuardar);
		
		JLabel titulo = new JLabel(new ImageIcon("logo.png"));
		titulo.setText("ESTADÍSTICAS");
		titulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		titulo.setBounds(120, 5, 185, 55);
		principal.add(titulo);
		frame.getContentPane().add(principal);
		
		btnGuardar.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnGuardar.setBackground(new Color(204, 204, 204));
			}
	
			public void mouseExited(MouseEvent evt) {
				btnGuardar.setBackground(Color.WHITE);
			}
		});
		
		btnGuardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				JPanel principal = new JPanel();
				principal.setLayout(new BorderLayout(0, 0));
				frame.getContentPane().add(principal);
				
				JTextField txtVistaPrevia = new JTextField();
				txtVistaPrevia.setEditable(false);
				txtVistaPrevia.setFont(new Font("Tahoma", Font.BOLD, 15));
				txtVistaPrevia.setHorizontalAlignment(SwingConstants.CENTER);
				txtVistaPrevia.setText("VISTA PREVIA");
				principal.add(txtVistaPrevia, BorderLayout.NORTH);
				txtVistaPrevia.setColumns(10);
				
				JTextArea info = new JTextArea();
				info.setFont(new Font("Monospaced", Font.PLAIN, 15));
				Date f = new Date(System.currentTimeMillis());
				String fecha = sdf.format(f);
				String todo = fecha + "\n\n" + "Dinero recaudado: " + txtDinero.getText() + "\n" + "Número de clientes: " 
				+ txtClientes.getText() + "\n" + "Número de pedidos: " + txtPedidos.getText() + "\n" + "Media de compras: " + txtCompras.getText();
				info.setText(todo);
				info.setEditable(false);
				principal.add(info, BorderLayout.CENTER);
				
				JButton btnConfirmar = new JButton("Confirmar");
				btnConfirmar.setBorder(new RoundedBorder(7));
				principal.add(btnConfirmar, BorderLayout.SOUTH);
				
				btnConfirmar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							PrintWriter pw = new PrintWriter("estadisticas.txt");
				
							System.out.println(info.getText()); // comprobacion
							pw.println(info);
							
							pw.close();
							frame.dispose();
							
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "Error en la escritura de los datos",null, 0);
							VentanaTienda.logger.log(Level.SEVERE, "Error al escribir datos");
						}
						
					}
				});
				
				frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				frame.setTitle("Guardar en fichero");
				frame.setSize(500, 325);
				frame.setVisible(true);
				frame.setIconImage(VentanaTienda.icon);
				frame.setLocationRelativeTo(null);;
				
			}
		});
		
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setTitle("Stats");
		frame.setSize(459, 350);
		frame.setIconImage(VentanaTienda.icon);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		}
}


