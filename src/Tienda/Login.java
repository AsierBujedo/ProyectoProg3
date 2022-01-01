package Tienda;

import static javax.swing.WindowConstants.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import BD.*;

public class Login {
	public static JTextField usertf;
	public static JPasswordField passtf;
	
	public void doLogin() {
		// Inicializamos la ventana
		JFrame frame = new JFrame();
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.getContentPane().setBackground(Color.WHITE);
		
		JPanel labelText = new JPanel();
		labelText.setLayout(new GridBagLayout());
		labelText.setBackground(Color.WHITE);
		
		JPanel botonera = new JPanel();
		botonera.setLayout(new BorderLayout());
		botonera.setBackground(new Color(246,246,246));
		
		JLabel register = new JLabel("Crear una cuenta");
		register.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		JLabel question = new JLabel("¿Ya tienes una cuenta?");
		question.setFont(new Font("Segoe UI", Font.BOLD, 13));
		JLabel mail = new JLabel("Email: ");
		mail.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		JLabel pass = new JLabel("Contraseña: ");
		pass.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		
		usertf = new JTextField(20);
		Border lineU = BorderFactory.createLineBorder(new Color(194,194,194), 2);
		Border emptyU = new EmptyBorder(0, 5, 0, 0);
		CompoundBorder borderU = new CompoundBorder(lineU, emptyU);
		usertf.setBorder(borderU);
		usertf.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		
		passtf = new JPasswordField(20);
		Border lineP = BorderFactory.createLineBorder(new Color(194,194,194), 2);
		Border emptyP = new EmptyBorder(0, 5, 0, 0);
		CompoundBorder borderP = new CompoundBorder(lineP, emptyP);
		passtf.setBorder(borderP);
		passtf.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		
		// FocusListener usertf
		usertf.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusGained(FocusEvent e) {
				Border lineU = BorderFactory.createLineBorder(new Color(20,115,191), 2);
				Border emptyU = new EmptyBorder(0, 5, 0, 0);
				CompoundBorder borderU = new CompoundBorder(lineU, emptyU);
				usertf.setBorder(borderU);
				super.focusGained(e);			
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				Border lineU = BorderFactory.createLineBorder(new Color(194,194,194), 2);
				Border emptyU = new EmptyBorder(0, 5, 0, 0);
				CompoundBorder borderU = new CompoundBorder(lineU, emptyU);
				usertf.setBorder(borderU);
				super.focusLost(e);						
			}
		});
		
		// FocusListener passtf
		passtf.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusGained(FocusEvent e) {
				Border lineP = BorderFactory.createLineBorder(new Color(20,115,191), 2);
				Border emptyP = new EmptyBorder(0, 5, 0, 0);
				CompoundBorder borderP = new CompoundBorder(lineP, emptyP);
				passtf.setBorder(borderP);
				super.focusGained(e);
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				Border lineP = BorderFactory.createLineBorder(new Color(194,194,194), 2);
				Border emptyP = new EmptyBorder(0, 5, 0, 0);
				CompoundBorder borderP = new CompoundBorder(lineP, emptyP);
				passtf.setBorder(borderP);
				super.focusLost(e);						
			}
		});
		
		// Botón log
		JButton log = new JButton("Iniciar sesion");
		log.setAlignmentX(Component.CENTER_ALIGNMENT);
		log.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		log.setForeground(new Color(67,67,67));
		log.setBorderPainted(false);
		log.setFocusPainted(false);
		log.setOpaque(false);
		log.setContentAreaFilled(false);
		
		// Botón regbutton
		JButton regbutton = new JButton("Registro");
		regbutton.setAlignmentX(Component.CENTER_ALIGNMENT);
		regbutton.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		regbutton.setForeground(new Color(67,67,67));
		regbutton.setBorderPainted(false);
		regbutton.setFocusPainted(false);
		regbutton.setBackground(new Color(194,194,194));
		
		// MouseListener log
		log.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				log.setOpaque(true);
				log.setContentAreaFilled(true);
				log.setBackground(new Color(225,225,225));
			}

			public void mouseExited(MouseEvent evt) {
				log.setOpaque(false);
				log.setContentAreaFilled(false);
			}
		});
				
		// MouseListener regbutton
		regbutton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				regbutton.setBackground(new Color(170,170,170));			
			}

			public void mouseExited(MouseEvent evt) {
				regbutton.setBackground(new Color(194,194,194));			
			}
		});
		
		GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(10, 10, 10, 10);
        
        gc.gridx = 0;
        gc.gridy = 0;
        labelText.add(register, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        labelText.add(regbutton, gc);
        
        gc.gridx = 0;
        gc.gridy = 1;
        labelText.add(question, gc);
        
        gc.gridx = 0;
        gc.gridy = 2;
        labelText.add(mail, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        labelText.add(usertf, gc);

        gc.gridx = 0;
        gc.gridy = 3;
        labelText.add(pass, gc);

        gc.gridx = 1;
        gc.gridy = 3;
        labelText.add(passtf, gc);
        
        botonera.add(log);
		frame.add(labelText);
		frame.add(botonera);
		
		// ActionListener log
		log.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String username = BaseDeDatos.getUser(usertf.getText(), String.valueOf(passtf.getPassword()));
				if (!username.equals("Error")) {
					VentanaTienda.loginItem.setText(username);
					VentanaTienda.loginItem.setEnabled(false);
					VentanaTienda.logoutItem.setEnabled(true);
					VentanaTienda.personalArea.setEnabled(true);
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Incorrect mail or password","Login error", 0);
					frame.dispose();
					doLogin();
				}

			}
		});
		
		// ActionListener regbutton
		regbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				JFrame frameReg = new JFrame();
				frameReg.setLayout(new BoxLayout(frameReg.getContentPane(), BoxLayout.Y_AXIS));
				frameReg.getContentPane().setBackground(Color.WHITE);
				
				JPanel labelText = new JPanel();
				labelText.setLayout(new GridBagLayout());
				labelText.setBackground(Color.WHITE);
				
				JPanel botonera = new JPanel();
				botonera.setLayout(new BorderLayout());
				botonera.setBackground(new Color(246,246,246));

				JLabel loginlabel = new JLabel("¿Ya tienes una cuenta? Iniciar sesión");
				loginlabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
				
				// MouseListener loginlabel
				loginlabel.addMouseListener(new MouseAdapter() {
					@SuppressWarnings({ "unchecked", "rawtypes" })
					public void mouseEntered(MouseEvent evt) {
						Font font = loginlabel.getFont();
						Map attributes = font.getAttributes();
						attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
						loginlabel.setFont(font.deriveFont(attributes));
						loginlabel.setForeground(new Color(20,115,191));
					}

					public void mouseExited(MouseEvent evt) {
						loginlabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
						loginlabel.setForeground(Color.BLACK);
					}
					
					// Asegurar que se hace correctamente
					public void mouseClicked(MouseEvent e) {
						frameReg.dispose();
						doLogin();
						usertf.requestFocus();
					}
				});
				
				JLabel mail = new JLabel("Email: ");
				mail.setFont(new Font("Segoe UI", Font.PLAIN, 15));
				JLabel pass = new JLabel("Contraseña: ");
				pass.setFont(new Font("Segoe UI", Font.PLAIN, 15));
				JLabel username = new JLabel("Nombre de usuario: ");
				username.setFont(new Font("Segoe UI", Font.PLAIN, 15));
				
				usertf = new JTextField(20);
				Border lineU = BorderFactory.createLineBorder(new Color(194,194,194), 2);
				Border emptyU = new EmptyBorder(0, 5, 0, 0);
				CompoundBorder borderU = new CompoundBorder(lineU, emptyU);
				usertf.setBorder(borderU);
				usertf.setFont(new Font("Segoe UI", Font.PLAIN, 15));
				usertf.setForeground(Color.GRAY);
				
				JPasswordField passtf = new JPasswordField(20);
				Border lineP = BorderFactory.createLineBorder(new Color(194,194,194), 2);
				Border emptyP = new EmptyBorder(0, 5, 0, 0);
				CompoundBorder borderP = new CompoundBorder(lineP, emptyP);
				passtf.setBorder(borderP);
				passtf.setFont(new Font("Segoe UI", Font.PLAIN, 15)); // Mirar
				
				JTextField usernametf = new JTextField(20);
				Border lineUN = BorderFactory.createLineBorder(new Color(194,194,194), 2);
				Border emptyUN = new EmptyBorder(0, 5, 0, 0);
				CompoundBorder borderUN = new CompoundBorder(lineUN, emptyUN);
				usernametf.setBorder(borderUN);
				usernametf.setFont(new Font("Segoe UI", Font.PLAIN, 15));
				usernametf.setForeground(Color.GRAY);
				
				// FocusListener usertf
				usertf.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent e) {
						Border lineU = BorderFactory.createLineBorder(new Color(20,115,191), 2);
						Border emptyU = new EmptyBorder(0, 5, 0, 0);
						CompoundBorder borderU = new CompoundBorder(lineU, emptyU);
						usertf.setBorder(borderU);
						usertf.setForeground(Color.BLACK);
						super.focusGained(e);
					}

					@Override
					public void focusLost(FocusEvent e) {
						Border lineU = BorderFactory.createLineBorder(new Color(194,194,194), 2);
						Border emptyU = new EmptyBorder(0, 5, 0, 0);
						CompoundBorder borderU = new CompoundBorder(lineU, emptyU);
						usertf.setBorder(borderU);
						usertf.setForeground(Color.GRAY);
						super.focusLost(e);
					}
				});
				
				// FocusListener passtf
				passtf.addFocusListener(new FocusAdapter() {
					
					@Override
					public void focusGained(FocusEvent e) {
						Border lineP = BorderFactory.createLineBorder(new Color(20,115,191), 2);
						Border emptyP = new EmptyBorder(0, 5, 0, 0);
						CompoundBorder borderP = new CompoundBorder(lineP, emptyP);
						passtf.setBorder(borderP);
						super.focusGained(e);
						
					}
					@Override
					public void focusLost(FocusEvent e) {
						Border lineP = BorderFactory.createLineBorder(new Color(194,194,194), 2);
						Border emptyP = new EmptyBorder(0, 5, 0, 0);
						CompoundBorder borderP = new CompoundBorder(lineP, emptyP);
						passtf.setBorder(borderP);
						super.focusLost(e);						
					}
				});
				
				// FocusListener usernametf
				usernametf.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent e) {
						Border lineUN = BorderFactory.createLineBorder(new Color(20,115,191), 2);
						Border emptyUN = new EmptyBorder(0, 5, 0, 0);
						CompoundBorder borderUN = new CompoundBorder(lineUN, emptyUN);
						usernametf.setBorder(borderUN);
						usernametf.setForeground(Color.BLACK);
						super.focusGained(e);
					}

					@Override
					public void focusLost(FocusEvent e) {
						Border lineUN = BorderFactory.createLineBorder(new Color(194,194,194), 2);
						Border emptyUN = new EmptyBorder(0, 5, 0, 0);
						CompoundBorder borderUN = new CompoundBorder(lineUN, emptyUN);
						usernametf.setBorder(borderUN);
						usernametf.setForeground(Color.GRAY);
						super.focusLost(e);
					}
				});
				
				// Botón reg
				JButton reg = new JButton("Registrarse");
				reg.setAlignmentX(Component.CENTER_ALIGNMENT);
				reg.setFont(new Font("Segoe UI", Font.PLAIN, 15));
				reg.setForeground(new Color(67,67,67));
				reg.setBorderPainted(false);
				reg.setFocusPainted(false);
				reg.setOpaque(false);
				reg.setContentAreaFilled(false);
				
				// MouseListener log
				reg.addMouseListener(new MouseAdapter() {
					public void mouseEntered(MouseEvent evt) {
						reg.setOpaque(true);
						reg.setContentAreaFilled(true);
						reg.setBackground(new Color(225,225,225));
					}

					public void mouseExited(MouseEvent evt) {
						reg.setOpaque(false);
						reg.setContentAreaFilled(false);
					}
				});
				
				// ActionListener reg
				reg.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						BaseDeDatos.addUser(usernametf.getText(), usertf.getText(), String.valueOf(passtf.getPassword()));
						frameReg.dispose();
					}
				});
				
				GridBagConstraints gc = new GridBagConstraints();
		        gc.fill = GridBagConstraints.HORIZONTAL;
		        gc.insets = new Insets(10, 10, 10, 10);
		        
		        gc.gridx = 0;
		        gc.gridy = 0;
		        labelText.add(loginlabel, gc);
		        
		        gc.gridx = 0;
		        gc.gridy = 1;
		        labelText.add(mail, gc);
		        
		        gc.gridx = 1;
		        gc.gridy = 1;
		        labelText.add(usertf, gc);

		        gc.gridx = 0;
		        gc.gridy = 2;
		        labelText.add(pass, gc);

		        gc.gridx = 1;
		        gc.gridy = 2;
		        labelText.add(passtf, gc);

		        gc.gridx = 0;
		        gc.gridy = 3;
		        labelText.add(username, gc);
		        
		        gc.gridx = 1;
		        gc.gridy = 3;
		        labelText.add(usernametf, gc);
				
		        botonera.add(reg);
		        frameReg.add(labelText);
		        frameReg.add(botonera);

				frameReg.setTitle("Registro de usuarios");
				frameReg.setResizable(false);
				frameReg.pack();
				frameReg.setVisible(true);
				frameReg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				frameReg.setIconImage(VentanaTienda.icon);
				frameReg.setLocationRelativeTo(null);

			}
		});
		
		frame.setTitle("Login");
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setIconImage(VentanaTienda.icon);
		frame.setLocationRelativeTo(null);
	}

}
