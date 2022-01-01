package Utils;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Vector;
import java.util.logging.Level;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import BD.BaseDeDatos;
import BD.COLS;
import Tienda.VentanaTienda;
import Tienda.Cesta;
import Tienda.Login;
import Tienda.Producto;

public class OtherFrames {
	private static double precio = 0.0;

	// Acceso al �rea personal
	public static void areaCliente() {
		JFrame frame = new JFrame();
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.getContentPane().setBackground(Color.WHITE);
		
		JPanel botonera = new JPanel();
		botonera.setLayout(new GridLayout(2, 1));
		botonera.setBackground(new Color(246,246,246));
		
		JLabel iconoUsuario = new JLabel();
		iconoUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
		ImageIcon icono = new ImageIcon("user.png");
		iconoUsuario.setIcon(icono);
		
		JLabel nombreUsuario = new JLabel();
		nombreUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
		nombreUsuario.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JLabel mailUsuario = new JLabel();
		mailUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
		mailUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mailUsuario.setForeground(new Color(225,225,225));
		
		String username = BaseDeDatos.getUser(Login.usertf.getText(), String.valueOf(Login.passtf.getPassword())).toUpperCase();
		if (!username.equals("Error")) {
			nombreUsuario.setText(username);
		} else {
			nombreUsuario.setText("");
			VentanaTienda.logger.log(Level.SEVERE, "Error al obtener nombre de usuario");
		}
		
		// Bot�n contra
		JButton contra = new JButton("Cambiar contrase�a");
		contra.setAlignmentX(Component.CENTER_ALIGNMENT);
		contra.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		contra.setForeground(new Color(67,67,67));
		contra.setBorderPainted(false);
		contra.setFocusPainted(false);
		contra.setOpaque(false);
		contra.setContentAreaFilled(false);
		
		// Bot�n historial
		JButton historial = new JButton("�ltima compra");
		historial.setAlignmentX(Component.CENTER_ALIGNMENT);
		historial.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		historial.setForeground(new Color(67,67,67));
		historial.setBorderPainted(false);
		historial.setFocusPainted(false);
		historial.setOpaque(false);
		historial.setContentAreaFilled(false);
		
		// MouseListener contra
		contra.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				contra.setOpaque(true);
				contra.setContentAreaFilled(true);
				contra.setBackground(new Color(225,225,225));
			}

			public void mouseExited(MouseEvent evt) {
				contra.setOpaque(false);
				contra.setContentAreaFilled(false);
			}
		});
		
		// MouseListener historial
		historial.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				historial.setOpaque(true);
				historial.setContentAreaFilled(true);
				historial.setBackground(new Color(225,225,225));			}

			public void mouseExited(MouseEvent evt) {
				historial.setOpaque(false);
				historial.setContentAreaFilled(false);			}
		});
		
		botonera.add(contra);
		botonera.add(historial);
		frame.add(iconoUsuario);
		frame.add(nombreUsuario);
		frame.add(botonera);
		
		// Ventana para cambio de contrase�a --------------------------------------------------
		contra.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
				frame.getContentPane().setBackground(Color.WHITE);
				
				JPanel labelText = new JPanel();
				labelText.setLayout(new GridBagLayout());
				labelText.setBackground(Color.WHITE);
				
				JPanel botonera = new JPanel();
				botonera.setLayout(new BorderLayout());
				botonera.setBackground(new Color(246,246,246));
				
				JLabel contraActual = new JLabel("Contrase�a actual: ");
				contraActual.setFont(new Font("Segoe UI", Font.PLAIN, 15));
				JLabel contraNueva = new JLabel("Nueva contrase�a: ");
				contraNueva.setFont(new Font("Segoe UI", Font.PLAIN, 15));
				JLabel contraConfirmar = new JLabel("Confirmar contrase�a: ");
				contraConfirmar.setFont(new Font("Segoe UI", Font.PLAIN, 15));
				
				JPasswordField contraActualtf = new JPasswordField(20);
				contraActualtf = OtherUtils.modifyPasswordField(contraActualtf);
				
				JPasswordField contraNuevatf = new JPasswordField(20);
				contraNuevatf = OtherUtils.modifyPasswordField(contraNuevatf);
				
				JPasswordField contraConfirmartf = new JPasswordField(20);
				contraConfirmartf = OtherUtils.modifyPasswordField(contraConfirmartf);
				
				// Bot�n cambiar
				JButton cambiar = new JButton("Cambiar contrase�a");
				cambiar.setFont(new Font("Segoe UI", Font.PLAIN, 15));
				cambiar.setForeground(new Color(67,67,67));
				cambiar.setBorderPainted(false);
				cambiar.setFocusPainted(false);
				cambiar.setOpaque(false);
				cambiar.setContentAreaFilled(false);
				
				// MouseListener cambiar
				cambiar.addMouseListener(new MouseAdapter() {
					public void mouseEntered(MouseEvent evt) {
						cambiar.setOpaque(true);
						cambiar.setContentAreaFilled(true);
						cambiar.setBackground(new Color(225,225,225));
					}

					public void mouseExited(MouseEvent evt) {
						cambiar.setOpaque(false);
						cambiar.setContentAreaFilled(false);
					}
				});
				
				// ActionListener cambiar
				cambiar.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String nombre = VentanaTienda.loginItem.getText();
						String mail = BaseDeDatos.getUserMail(nombre);
						String existe = BaseDeDatos.getUser(mail, String.valueOf(contraActualtf.getPassword()));
						if (String.valueOf(contraNuevatf.getPassword()).equals(String.valueOf(contraConfirmartf.getPassword()))
								&& !existe.equals("Error")) {
							BaseDeDatos.editUser(COLS.PASS, mail, String.valueOf(contraActualtf.getPassword()),
									String.valueOf(contraNuevatf.getPassword()));
						} else {
							JOptionPane.showMessageDialog(null, "Las contrase�as no coinciden", "Error", 0);
							frame.dispose();
						}
						frame.dispose();
					}
				});
				
				GridBagConstraints gc = new GridBagConstraints();
		        gc.fill = GridBagConstraints.HORIZONTAL;
		        gc.insets = new Insets(10, 10, 10, 10);

		        gc.gridx = 0;
		        gc.gridy = 0;
		        labelText.add(contraActual, gc);

		        gc.gridx = 1;
		        gc.gridy = 0;
		        labelText.add(contraActualtf, gc);

		        gc.gridx = 0;
		        gc.gridy = 1;
		        labelText.add(contraNueva, gc);

		        gc.gridx = 1;
		        gc.gridy = 1;
		        labelText.add(contraNuevatf, gc);
		        
		        gc.gridx = 0;
		        gc.gridy = 2;
		        labelText.add(contraConfirmar, gc);

		        gc.gridx = 1;
		        gc.gridy = 2;
		        labelText.add(contraConfirmartf, gc);
				
				botonera.add(cambiar);
				frame.add(labelText);
				frame.add(botonera);

				frame.setTitle("Cambio de contrase�a");
				frame.setResizable(false);
				frame.pack();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				frame.setIconImage(VentanaTienda.icon);
				frame.setLocationRelativeTo(null);
			}
		});
		
		// Ventana para �ltima compra --------------------------------------------------
		historial.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = VentanaTienda.loginItem.getText();
				String mail = BaseDeDatos.getUserMail(nombre);
				if (Cesta.lastCompra.containsKey(mail)) {
					JFrame frame = new JFrame();
					Vector<String> nomColumnas = new Vector<String>(
							Arrays.asList("C�digo de producto", "Nombre", "Precio", "Marca"));
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
					frame.setTitle("�ltima compra");
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
								info.setText("Precio total: " + OtherUtils.round(precio, 2) + "�");
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

		frame.setTitle("Area cliente");
		frame.setSize(300, 250);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setIconImage(VentanaTienda.icon);
	}
	
	// VENTANA ESTADISTICAS
	
//	COMPROBACION

//	public static void main(String[] args) {
//	VentanaTienda.iniciaLog();
//	BaseDeDatos.InitDB();
//	new OtherFrames().VentanaStats();
//	BaseDeDatos.closeDB();
//
//}

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

	txtDinero.setText(OtherUtils.round(BaseDeDatos.getPrecioCompras(), 2)+"�");
	
	JLabel lblClientes = new JLabel("N�mero de clientes:");
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
	
	JLabel lblPedidos = new JLabel("N�mero de pedidos:");
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
	titulo.setText("ESTAD�STICAS");
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
			String todo = fecha + "\n\n" + "Dinero recaudado: " + txtDinero.getText() + "\n" + "N�mero de clientes: " 
			+ txtClientes.getText() + "\n" + "N�mero de pedidos: " + txtPedidos.getText() + "\n" + "Media de compras: " + txtCompras.getText();
			info.setText(todo);
			info.setEditable(false);
			principal.add(info, BorderLayout.CENTER);
				
			JButton btnConfirmar = new JButton("Confirmar");	
			principal.add(OtherUtils.modifyButton(btnConfirmar, new Color(67, 67, 67), new Color(194, 194, 194), new Color(225, 225, 225)), BorderLayout.SOUTH);
			
			btnConfirmar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						PrintWriter pw = new PrintWriter("estadisticas.txt");
			
						System.out.println(info.getText()); // comprobacion
						pw.println(info.getText());
						
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
