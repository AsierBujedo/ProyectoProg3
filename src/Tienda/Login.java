package Tienda;

import static javax.swing.WindowConstants.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import BD.*;
import Utils.RoundedBorder;

public class Login {
	public void doLogin() {
		BaseDeDatos bd = new BaseDeDatos();
		bd.InitDB();
		
		// Inicializamos la ventana
		JFrame frame = new JFrame();
		JLabel user = new JLabel("Correo: ");
		JTextField usertf = new JTextField(20);
		usertf.setBorder(new RoundedBorder(7));
		JLabel pass = new JLabel("Contraseña: ");
		JPasswordField passtf = new JPasswordField(20);
		passtf.setBorder(new RoundedBorder(7));
		JButton log = new JButton("Iniciar sesion");
		log.setBorder(new RoundedBorder(7));
		JPanel panel = new JPanel();
		JLabel loginlabel = new JLabel("¿Sin usuario? --->");
		panel.setLayout(new GridLayout(2, 1));
		panel.add(user, BorderLayout.NORTH);
		panel.add(usertf, BorderLayout.CENTER);
		panel.add(pass, BorderLayout.NORTH);
		panel.add(passtf, BorderLayout.CENTER);
		frame.add(panel, BorderLayout.CENTER);
		frame.add(log, BorderLayout.SOUTH);
		JPanel reg = new JPanel();
		JButton regbutton = new JButton("Registro");
		regbutton.setBorder(new RoundedBorder(7));
		reg.setLayout(new GridLayout(1, 2));
		reg.add(loginlabel);
		reg.add(regbutton);
		frame.add(reg, BorderLayout.NORTH);

		log.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String username = bd.getUser(usertf.getText(), String.valueOf(passtf.getPassword()));
				if (!username.equals("Error")) {
					VentanaTienda.loginItem.setText(username);
					VentanaTienda.loginItem.setEnabled(false);
					VentanaTienda.logoutItem.setEnabled(true);
					VentanaTienda.personalArea.setEnabled(true);
					bd.closeDB();
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Incorrect mail or password","Login error", 0);
					frame.dispose();
					doLogin();
				}

			}
		});

		regbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				JLabel user = new JLabel("Correo: ");
				JTextField usertf = new JTextField(20);
				usertf.setBorder(new RoundedBorder(7));
				JLabel pass = new JLabel("Contraseña: ");
				JPasswordField passtf = new JPasswordField(20);
				passtf.setBorder(new RoundedBorder(7));
				JLabel username = new JLabel("Nombre de usuario: ");
				JTextField usernametf = new JTextField(20);
				usernametf.setBorder(new RoundedBorder(7));
				JButton log = new JButton("Enviar");
				log.setBorder(new RoundedBorder(7));
				JPanel panel = new JPanel();
				JLabel loginlabel = new JLabel("¿Todavia sin usuario?");
				panel.setLayout(new GridLayout(3, 1));
				panel.add(user, BorderLayout.NORTH);
				panel.add(usertf, BorderLayout.CENTER);
				panel.add(pass, BorderLayout.NORTH);
				panel.add(passtf, BorderLayout.CENTER);
				panel.add(username, BorderLayout.NORTH);
				panel.add(usernametf, BorderLayout.NORTH);
				frame.add(panel, BorderLayout.CENTER);
				frame.add(log, BorderLayout.SOUTH);
				JPanel reg = new JPanel();
				JButton regbutton = new JButton("Rellena todos los campos");
				regbutton.setBorder(new RoundedBorder(7));
				regbutton.setEnabled(false);
				reg.setLayout(new GridLayout(1, 2));
				reg.add(loginlabel);
				reg.add(regbutton);
				frame.add(reg, BorderLayout.NORTH);

				log.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						bd.addUser(usernametf.getText(), usertf.getText(), String.valueOf(passtf.getPassword()));
						frame.dispose();

					}
				});

				frame.setTitle("Registro de usuarios");
				frame.setSize(400, 225);
				frame.setResizable(false);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				frame.setIconImage(VentanaTienda.icon);

			}
		});
		
		frame.setTitle("Login");
		frame.setSize(400, 185);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setIconImage(VentanaTienda.icon);
	}

}
