package Tienda;

import static javax.swing.WindowConstants.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import BD.*;

public class Login {

	public void doLogin() {
		BaseDeDatos bd = new BaseDeDatos();
		bd.InitDB();
		JFrame frame = new JFrame();
		JLabel user = new JLabel("Correo: ");
		JTextField usertf = new JTextField(20);
		JLabel pass = new JLabel("Contraseña: ");
		JTextField passtf = new JTextField(20);
		JButton log = new JButton("Iniciar sesion");
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
		reg.setLayout(new GridLayout(1, 2));
		reg.add(loginlabel);
		reg.add(regbutton);
		frame.add(reg, BorderLayout.NORTH);
		
		log.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = bd.getUser(usertf.getText(), passtf.getText());
				VentanaTienda.loginItem.setText(username);
				VentanaTienda.loginItem.setEnabled(false);
				bd.closeDB();
				frame.dispose();
				
			}
		});
		
		regbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				JLabel user = new JLabel("Correo: ");
				JTextField usertf = new JTextField(20);
				JLabel pass = new JLabel("Contraseña: ");
				JTextField passtf = new JTextField(20);
				JLabel username = new JLabel("Nombre de usuario: ");
				JTextField usernametf = new JTextField(20);
				JButton log = new JButton("Enviar");
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
				regbutton.setEnabled(false);
				reg.setLayout(new GridLayout(1, 2));
				reg.add(loginlabel);
				reg.add(regbutton);
				frame.add(reg, BorderLayout.NORTH);
				
				log.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						bd.addUser(usernametf.getText(), usertf.getText(), passtf.getText());
						frame.dispose();
						
					}
				});
				
				
				frame.setTitle("Registro de usuarios");
				frame.setSize(400, 300);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				
			}
		});
		
		
		
		frame.setTitle("Login");
		frame.setSize(350, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
}
