package Utils;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Vector;
import java.util.logging.Level;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import BD.BaseDeDatos;
import BD.COLS;
import BD.Genero;
import Tienda.VentanaTienda;
import Tienda.Cesta;
import Tienda.Login;
import Tienda.Producto;

/** 
 * Clase OtherFrames.
 * Contiene ventanas y elementos para el area personal y estadisticas.
 * @author GR08
 */

public class OtherFrames {
	private static double precio = 0.0;
	public static JPasswordField contraActualtf;
	public static JPasswordField contraNuevatf;
	public static JPasswordField contraConfirmartf;
	private static JFrame frameaC;

	public static JFormattedTextField txtDate;
	public static JFormattedTextField txtTelefono;
	public static SimpleDateFormat sdf;
	
	// Acceso al área personal
	public static void areaCliente() {
		frameaC = new JFrame();
		frameaC.setLayout(new BoxLayout(frameaC.getContentPane(), BoxLayout.Y_AXIS));
		frameaC.getContentPane().setBackground(Color.WHITE);

		JPanel botonera = new JPanel();
		botonera.setLayout(new GridLayout(3, 1));
		botonera.setBackground(new Color(246, 246, 246));

		JLabel iconoUsuario = new JLabel();
		iconoUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
		ImageIcon icono = new ImageIcon("user.png");
		iconoUsuario.setIcon(icono);

		JLabel nombreUsuario = new JLabel();
		nombreUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
		nombreUsuario.setFont(new Font("Segoe UI", Font.BOLD, 20));

		String username = BaseDeDatos.getUser(Login.usertf.getText(), String.valueOf(Login.passtflog.getPassword()))
				.toUpperCase();
		if (!username.equals("ERROR")) {
			nombreUsuario.setText(username);
		} else {
			nombreUsuario.setText("");
			VentanaTienda.logger.log(Level.SEVERE, "Error al obtener nombre de usuario");
		}

		// Botón contra
		JButton contra = new JButton("Cambiar contraseña");
		contra = OtherUtils.modifyButton(contra, new Color(67, 67, 67), new Color(194, 194, 194),
				new Color(225, 225, 225));

		// Botón historial
		JButton historial = new JButton("Última compra");
		historial = OtherUtils.modifyButton(historial, new Color(67, 67, 67), new Color(194, 194, 194),
				new Color(225, 225, 225));

		// Botón informacion
		JButton informacion = new JButton("Información de la cuenta");
		informacion = OtherUtils.modifyButton(informacion, new Color(67, 67, 67), new Color(194, 194, 194),
				new Color(225, 225, 225));

		botonera.add(contra);
		botonera.add(historial);
		botonera.add(informacion);
		frameaC.add(iconoUsuario);
		frameaC.add(nombreUsuario);
		frameaC.add(botonera);

		// Ventana para cambio de contraseña
		// --------------------------------------------------
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
				botonera.setBackground(new Color(246, 246, 246));

				JLabel contraActual = new JLabel("Contraseña actual");
				contraActual.setFont(new Font("Segoe UI", Font.PLAIN, 15));
				JLabel contraNueva = new JLabel("Nueva contraseña");
				contraNueva.setFont(new Font("Segoe UI", Font.PLAIN, 15));
				JLabel contraConfirmar = new JLabel("Confirmar contraseña");
				contraConfirmar.setFont(new Font("Segoe UI", Font.PLAIN, 15));

				contraActualtf = new JPasswordField(20);
				contraActualtf = OtherUtils.modifyPasswordField(contraActualtf, "Contraseña actual");

				contraNuevatf = new JPasswordField(20);
				contraNuevatf = OtherUtils.modifyPasswordField(contraNuevatf, "Nueva contraseña");

				contraConfirmartf = new JPasswordField(20);
				contraConfirmartf = OtherUtils.modifyPasswordField(contraConfirmartf, "Confirmar contraseña");

				// Botón cambiar
				JButton cambiar = new JButton("Cambiar contraseña");
				cambiar = OtherUtils.modifyButton(cambiar, new Color(67, 67, 67), new Color(194, 194, 194),
						new Color(225, 225, 225));

				// ActionListener cambiar
				cambiar.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String nombre = VentanaTienda.loginItem.getText();
						String mail = BaseDeDatos.getUserMail(nombre);
						String existe = BaseDeDatos.getUser(mail, String.valueOf(contraActualtf.getPassword()));
						if (String.valueOf(contraNuevatf.getPassword())
								.equals(String.valueOf(contraConfirmartf.getPassword())) && !existe.equals("Error")) {
							BaseDeDatos.editUser(COLS.PASS, mail, String.valueOf(contraActualtf.getPassword()),
									String.valueOf(contraNuevatf.getPassword()));
							frame.dispose();
							VentanaTienda.loginItem.setText("Login");
							VentanaTienda.loginItem.setEnabled(true);
							VentanaTienda.logoutItem.setEnabled(false);
							VentanaTienda.personalArea.setEnabled(false);
							frameaC.dispose();
							JOptionPane.showMessageDialog(null, "Vuelve a iniciar sesión para verificar el cambio",
									"Verificación", JOptionPane.WARNING_MESSAGE);
							Login.doLogin();
						} else {
							JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Error",
									JOptionPane.ERROR_MESSAGE);
							frame.dispose();
						}
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

				frame.setTitle("Cambio de contraseña");
				frame.setResizable(false);
				frame.pack();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				frame.setIconImage(VentanaTienda.icon);
				frame.setLocationRelativeTo(null);
			}
		});

		// Ventana para última compra --------------------------------------------------
		historial.addActionListener(new ActionListener() {

			@SuppressWarnings("serial")
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = VentanaTienda.loginItem.getText();
				String mail = BaseDeDatos.getUserMail(nombre);
				if (Cesta.lastCompra.containsKey(mail)) {
					JFrame frame = new JFrame();
					Vector<String> nomColumnas = new Vector<String>(
							Arrays.asList("Código de producto", "Nombre", "Precio", "Marca"));
					JTable tabla = new JTable() {
						public boolean editCellAt(int row, int column, java.util.EventObject e) {// Evita que la tabla
																									// se pueda editar
							return false;
						}
					};
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

		// Ventana para la informacion de la cuenta
		// --------------------------------------------------
		informacion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Inicializamos la ventana
				JFrame frame = new JFrame();
				JPanel principal = new JPanel();

				String nombre = VentanaTienda.loginItem.getText();
				String mail = BaseDeDatos.getUserMail(nombre);

				principal.setBackground(Color.WHITE);
				principal.setLayout(null);

				frame.add(principal);

				JTextArea txtAreaInfo = new JTextArea();
				txtAreaInfo.setEditable(false);
				txtAreaInfo.setForeground(Color.GRAY);
				txtAreaInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				txtAreaInfo.setText(
						"Proporciona tu informaci\u00F3n personal. Esta informaci\u00F3n se mantendr\u00E1 privada y no se incluir\u00E1 en tu perfil p\u00FAblico.");
				txtAreaInfo.setBounds(20, 10, 356, 46);
				txtAreaInfo.setLineWrap(true);
				txtAreaInfo.setWrapStyleWord(true);

				JLabel lblMail = new JLabel("Dirección de correo electrónico");
				lblMail.setForeground(Color.GRAY);
				lblMail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				lblMail.setBounds(20, 65, 200, 20);

				JTextField txtMail = new JTextField();
				txtMail.setEditable(false);
				txtMail.setForeground(Color.BLACK);
				txtMail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				txtMail.setBounds(20, 90, 199, 19);
				txtMail.setBorder(null);
				txtMail.setColumns(10);
				OtherUtils.modifyTextFieldSimple(txtMail, "Email");

				txtMail.setText(mail);

				JLabel lblNumero = new JLabel("N\u00FAmero de tel\u00E9fono");
				lblNumero.setForeground(Color.GRAY);
				lblNumero.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				lblNumero.setBounds(20, 245, 185, 20);

				try {
					MaskFormatter masc = new MaskFormatter("#########");
					txtTelefono = new JFormattedTextField(masc);
					txtTelefono.setHorizontalAlignment(SwingConstants.LEFT);
					txtTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 14));
					txtTelefono.setBounds(20, 270, 199, 19);
					txtTelefono.setBorder(null);

					txtTelefono.setText(BaseDeDatos.getUserTelf(mail) + "");
				} catch (ParseException e1) {
					e1.printStackTrace();
				}

				JLabel lblSexo = new JLabel("Sexo");
				lblSexo.setForeground(Color.GRAY);
				lblSexo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				lblSexo.setBounds(20, 185, 185, 20);

				JComboBox<Genero> genero = new JComboBox<Genero>(Genero.values());
				genero.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				genero.setSelectedItem(Genero.FEMENINO);
				genero.setBackground(Color.WHITE);
				genero.setBounds(20, 210, 199, 29);
				genero.setSelectedItem(BaseDeDatos.getUserGenero(mail));

				JLabel lblFecha = new JLabel("Fecha de nacimiento");
				lblFecha.setForeground(Color.GRAY);
				lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				lblFecha.setBounds(20, 365, 185, 20);

				try {
					MaskFormatter modelo = new MaskFormatter("####-##-##");
					txtDate = new JFormattedTextField(modelo);
					sdf = new SimpleDateFormat("yyyy/MM/dd");
					String f = BaseDeDatos.getUserDate(BaseDeDatos.getUserMail(VentanaTienda.loginItem.getText()));
					txtDate.setText(f);

					txtDate.setHorizontalAlignment(SwingConstants.LEFT);
					txtDate.setFont(new Font("Segoe UI", Font.PLAIN, 14));
					txtDate.setBounds(20, 390, 199, 19);
					txtDate.setBorder(null);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}

				JSeparator lineMail = new JSeparator();
				lineMail.setBounds(20, 110, 250, 2);
				lineMail.setForeground(Color.GRAY);

				JSeparator lineNumero = new JSeparator();
				lineNumero.setForeground(Color.GRAY);
				lineNumero.setBounds(20, 290, 250, 2);

				JSeparator lineFecha = new JSeparator();
				lineFecha.setForeground(Color.GRAY);
				lineFecha.setBounds(20, 410, 250, 2);
				
				JSeparator lineUsuario = new JSeparator();
				lineUsuario.setForeground(Color.GRAY);
				lineUsuario.setBounds(20, 170, 250, 2);

				JSeparator lineDireccion = new JSeparator();
				lineDireccion.setForeground(Color.GRAY);
				lineDireccion.setBounds(20, 350, 250, 2);

				JLabel lblDir = new JLabel("Dirección");
				lblDir.setForeground(Color.GRAY);
				lblDir.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				lblDir.setBounds(20, 305, 185, 20);

				JTextField txtDireccion = new JTextField();
				txtDireccion.setForeground(Color.BLACK);
				txtDireccion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				txtDireccion.setColumns(10);
				txtDireccion.setBorder(null);
				txtDireccion.setBounds(20, 330, 250, 19);
				OtherUtils.modifyTextFieldSimple(txtDireccion, "Escriba una direccion");

				txtDireccion.setText(BaseDeDatos.getUserDir(mail));

				JLabel lblUsuario = new JLabel("Usuario");
				lblUsuario.setForeground(Color.GRAY);
				lblUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				lblUsuario.setBounds(20, 130, 185, 13);

				JTextField txtUsuario = new JTextField();
				txtUsuario.setHorizontalAlignment(SwingConstants.LEFT);
				txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				txtUsuario.setBorder(null);
				txtUsuario.setBounds(20, 150, 199, 19);
				OtherUtils.modifyTextFieldSimple(txtUsuario, "Usuario");

				txtUsuario.setText(nombre);

				// Boton cambios
				JButton btnCambios = new JButton("Aplicar cambios");
				btnCambios.setForeground(Color.WHITE);
				btnCambios.setCursor(new Cursor(Cursor.HAND_CURSOR));
				btnCambios.setBackground(new Color(0, 206, 209));
				btnCambios.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				btnCambios.setBounds(137, 438, 135, 29);
				btnCambios.setBorderPainted(false);

				// MouseListener cambios
				btnCambios.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseExited(MouseEvent e) {
						btnCambios.setBackground(new Color(0, 206, 209));

					}

					@Override
					public void mouseEntered(MouseEvent e) {
						btnCambios.setBackground(new Color(0, 206, 209).darker());

					}

				});

				// ActionListener del boton para ejecutar los cambios realizados por el usuario
				btnCambios.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						sdf = new SimpleDateFormat("yyyy-MM-dd");
						java.util.Date date;
						try {
							date = sdf.parse(txtDate.getText());
							long dateL = date.getTime();
							BaseDeDatos.editUserSpecs(mail, txtUsuario.getText(),
									Integer.valueOf(txtTelefono.getText()), (Genero) genero.getSelectedItem(),
									new java.sql.Date(dateL), txtDireccion.getText());
							if (VentanaTienda.loginItem.getText() != txtUsuario.getText())
								VentanaTienda.loginItem.setText(txtUsuario.getText()); // Se cambia el nombre del
																						// loginItem con el texto de
																						// usuario,
																						// sino la siguiente vez que se
																						// cargue la ventana se cargaran
																						// datos incorrectos o vacios
							VentanaTienda.logger.log(Level.INFO, "Especificaciones actualizadas");
							JOptionPane.showMessageDialog(null, "Cambios realizados correctamente", null, 1);
						} catch (ParseException e1) {
							e1.printStackTrace();
						}

					}
				});

				// JTextArea
				principal.add(txtAreaInfo);

				// Labels
				principal.add(lblMail);
				principal.add(lblNumero);
				principal.add(lblSexo);
				principal.add(lblFecha);
				principal.add(lblUsuario);
				principal.add(lblDir);

				// TextFields
				principal.add(txtMail);
				principal.add(txtTelefono);
				principal.add(txtDate);
				principal.add(txtDireccion);
				principal.add(txtUsuario);

				// JComboBox
				principal.add(genero);

				// JSeparator
				principal.add(lineMail);
				principal.add(lineNumero);
				principal.add(lineFecha);
				principal.add(lineDireccion);
				principal.add(lineUsuario);

				// Boton cambios
				principal.add(btnCambios);

				frame.setVisible(true);
				frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				frame.setTitle("Informacion personal");
				frame.setSize(400, 520);
				frame.setIconImage(VentanaTienda.icon);
				frame.setLocationRelativeTo(null);
				frame.setResizable(false);

			}
		});

		frameaC.setTitle("Area cliente");
		frameaC.setSize(300, 270);
		frameaC.setResizable(false);
		frameaC.setLocationRelativeTo(null);
		frameaC.setVisible(true);
		frameaC.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frameaC.setIconImage(VentanaTienda.icon);
	}


	/**
	 * 
	 */
	// Ventana para las estadisticas de la tienda
	public static void VentanaStats() {
		// Inicializamos la ventana
		JFrame frame = new JFrame();
		JPanel principal = new JPanel();
		principal.setBackground(Color.WHITE);
		principal.setForeground(Color.WHITE);
		principal.setLayout(null);

		JLabel lblDinero = new JLabel("Dinero recaudado: ");
		lblDinero.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDinero.setBounds(30, 85, 125, 22);
		principal.add(lblDinero);

		JTextField txtDinero = new JTextField();
		txtDinero.setEditable(false);
		txtDinero.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtDinero.setBounds(185, 80, 230, 30);
		principal.add(txtDinero);
		txtDinero.setColumns(10);
		txtDinero.setBorder(new RoundedBorder(7));

		txtDinero.setText(OtherUtils.round(BaseDeDatos.getPrecioCompras(), 2) + "€");

		JLabel lblClientes = new JLabel("Número de clientes:");
		lblClientes.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblClientes.setBounds(30, 130, 127, 22);
		principal.add(lblClientes);

		JTextField txtClientes = new JTextField();
		txtClientes.setEditable(false);
		txtClientes.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtClientes.setColumns(10);
		txtClientes.setBorder(new RoundedBorder(7));
		txtClientes.setBounds(185, 125, 230, 30);
		principal.add(txtClientes);

		txtClientes.setText(BaseDeDatos.getTotalClientes() + "");

		JLabel lblCompras = new JLabel("Compras totales:");
		lblCompras.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCompras.setBounds(30, 170, 145, 22);
		principal.add(lblCompras);

		JTextField txtCompras = new JTextField();
		txtCompras.setEditable(false);
		txtCompras.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtCompras.setColumns(10);
		txtCompras.setBorder(new RoundedBorder(7));
		txtCompras.setBounds(185, 167, 230, 30);
		principal.add(txtCompras);

		txtCompras.setText(BaseDeDatos.getNumeroCompras() + "");

		JLabel lblProductos = new JLabel("Productos vendidos:");
		lblProductos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblProductos.setBounds(30, 213, 145, 22);
		principal.add(lblProductos);

		JTextField txtProductos = new JTextField();
		txtProductos.setEditable(false);
		txtProductos.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtProductos.setColumns(10);
		txtProductos.setBorder(new RoundedBorder(7));
		txtProductos.setBounds(185, 210, 230, 30);
		principal.add(txtProductos);

		txtProductos.setText(BaseDeDatos.getTotalProductos() + "");

		JSeparator lineDinero = new JSeparator();
		lineDinero.setBounds(34, 107, 110, 2);
		principal.add(lineDinero);

		JSeparator lineClientes = new JSeparator();
		lineClientes.setBounds(34, 152, 120, 2);
		principal.add(lineClientes);

		JSeparator lineCompras = new JSeparator();
		lineCompras.setBounds(34, 192, 100, 2);
		principal.add(lineCompras);

		JSeparator lineProductos = new JSeparator();
		lineProductos.setBounds(34, 235, 120, 2);
		principal.add(lineProductos);

		// Boton guardar
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

		// MouseListener boto guardar
		btnGuardar.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
				btnGuardar.setBackground(new Color(204, 204, 204));
			}

			public void mouseExited(MouseEvent evt) {
				btnGuardar.setBackground(Color.WHITE);
			}
		});
		
		// ActionListener boton guardar
		btnGuardar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				JPanel principal = new JPanel();
				principal.setLayout(new BorderLayout(0, 0));
				frame.getContentPane().add(principal);

				JTextArea info = new JTextArea();
				JScrollPane infoSP = new JScrollPane(info);
				info.setFont(new Font("Monospaced", Font.PLAIN, 15));
				Date f = new Date(System.currentTimeMillis());
				String fecha = sdf.format(f);
				info.append(fecha);
				String todo = fecha + "\n\n" + "Dinero recaudado: " + txtDinero.getText() + "\n"
						+ "Número de clientes: " + txtClientes.getText() + "\n" + "Compras totales: "
						+ txtCompras.getText() + "\n" + "Productos totales: " + txtProductos.getText();
				info.append(todo);
				OtherUtils.combinaCompras(200); // NO SUBIR DE 200€
				for (String s : OtherUtils.posiblesCompras) {
					info.append("\n" + s);
				}
				info.setEditable(false);
				principal.add(infoSP, BorderLayout.CENTER);

				JTextField txtVistaPrevia = new JTextField();
				txtVistaPrevia.setEditable(false);
				txtVistaPrevia.setFont(new Font("Tahoma", Font.BOLD, 15));
				txtVistaPrevia.setHorizontalAlignment(SwingConstants.CENTER);
				txtVistaPrevia.setText("VISTA PREVIA");
				principal.add(txtVistaPrevia, BorderLayout.NORTH);
				txtVistaPrevia.setColumns(10);

				// Boton confirmar
				JButton btnConfirmar = new JButton("Confirmar");
				principal.add(OtherUtils.modifyButton(btnConfirmar, new Color(67, 67, 67), new Color(194, 194, 194),
						new Color(225, 225, 225)), BorderLayout.SOUTH);

				// ActionListener boton confirmar
				btnConfirmar.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						Thread escritura = new Thread(new Runnable() {

							@Override
							public void run() {
								try {
									File f = new File("estadisticas.txt");
									PrintWriter pw = new PrintWriter(f);
									pw.println(info.getText());
									pw.println("\n Posibles Compras con los productos actuales: ");
									for (String s : OtherUtils.posiblesCompras) {
										pw.println(s);
									}
									pw.close();
									if (Boolean.valueOf(OtherUtils.prop.getProperty("COMPRESS-STATS"))) {
										byte[] buffer = new byte[1024];
										ZipOutputStream toZip = new ZipOutputStream(new FileOutputStream("stats.zip"));
										FileInputStream in = new FileInputStream(f);
										toZip.putNextEntry(new ZipEntry(f.getName()));
										int dato;
										while ((dato = in.read(buffer)) > 0) {
											toZip.write(buffer, 0, dato);
										}

										toZip.closeEntry();
										in.close();
										toZip.close();
										f.delete();
										JOptionPane.showMessageDialog(null,
												"¡Escritura completada! Se han comprimido las estadísticas...",
												"Escritura", JOptionPane.INFORMATION_MESSAGE);
									} else {
										JOptionPane.showMessageDialog(null, "¡Escritura completada!", "Escritura",
												JOptionPane.INFORMATION_MESSAGE);
									}

								} catch (IOException e1) {
									JOptionPane.showMessageDialog(null, "Error en la escritura de los datos", null, 0);
									VentanaTienda.logger.log(Level.SEVERE, "Error al escribir datos");
								}
							}
						});
						escritura.start();

					}
				});

				frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				frame.setTitle("Guardar en fichero");
				frame.setSize(500, 325);
				frame.setVisible(true);
				frame.setIconImage(VentanaTienda.icon);
				frame.setLocationRelativeTo(null);
				;

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
