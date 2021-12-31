package Utils;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

import java.awt.*;
import java.awt.event.*;
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

	// Acceso al area personal
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
		
		String username = BaseDeDatos.getUser(Login.usertf.getText(), String.valueOf(Login.passtf.getPassword()));
		if (!username.equals("Error")) {
			nombreUsuario.setText(username);
		} else {
			nombreUsuario.setText("");
			VentanaTienda.logger.log(Level.SEVERE, "Error al obtener nombre de usuario");
		}
		
//		String mail = BaseDeDatos.getUserMail(Login.usertf.getText());
//		System.out.println(mail);
//		if (!mail.equals("Error")) {
//			mailUsuario.setText(mail);
//		} else {
//			mailUsuario.setText("");
//			VentanaTienda.logger.log(Level.SEVERE, "Error al obtener mail");
//		}
		
		JButton contra = new JButton("Cambiar contraseña");
		contra.setAlignmentX(Component.CENTER_ALIGNMENT);
		contra.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		contra.setForeground(new Color(67,67,67));
		contra.setBorderPainted(false);
		contra.setFocusPainted(false);
		contra.setOpaque(false);
		contra.setContentAreaFilled(false);
		
		JButton historial = new JButton("Última compra");
		historial.setAlignmentX(Component.CENTER_ALIGNMENT);
		historial.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		historial.setForeground(new Color(67,67,67));
		historial.setBorderPainted(false);
		historial.setFocusPainted(false);
		historial.setOpaque(false);
		historial.setContentAreaFilled(false);
		
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
		frame.add(mailUsuario);
		frame.add(botonera);
		
//		frame.add(contra);
//		frame.add(historial);
//		frame.add(contra);
//		frame.add(historial);

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

		frame.setTitle("Area cliente");
		frame.setSize(300, 400);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setIconImage(VentanaTienda.icon);
	}

}
