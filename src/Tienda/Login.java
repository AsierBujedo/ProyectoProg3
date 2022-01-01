package Tienda;

import static javax.swing.WindowConstants.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import BD.*;
import Utils.OtherUtils;

public class Login {
	public static JTextField usertf;
	public static JPasswordField passtf;
	public static JTextField usernametf;
	public static JTextField telftf;
	public static JComboBox<Genero> genjcb ;
	public static JTextField dirtf;
	public static JDatePickerImpl datePicker;
	
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
		log = OtherUtils.modifyButton(log, new Color(67, 67, 67), new Color(194, 194, 194));
		
		// Botón regbutton
		JButton regbutton = new JButton("Registro");
		regbutton = OtherUtils.modifyButton(regbutton, new Color(67, 67, 67), new Color(194, 194, 194));
				
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
					JOptionPane.showMessageDialog(null, "Correo o clave incorrectos","Login error", JOptionPane.ERROR_MESSAGE);
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
				JLabel telf = new JLabel("Número de teléfono");
				telf.setFont(new Font("Segoe UI", Font.PLAIN, 15));
				JLabel gen = new JLabel("Género: ");
				gen.setFont(new Font("Segoe UI", Font.PLAIN, 15));
				JLabel fechanac = new JLabel("Fecha de nacimiento");
				fechanac.setFont(new Font("Segoe UI", Font.PLAIN, 15));
				JLabel dir = new JLabel("Dirección: ");
				dir.setFont(new Font("Segoe UI", Font.PLAIN, 15));
				
				usertf = new JTextField(20);
				usertf = OtherUtils.modifyTextField(usertf);
				
				JPasswordField passtf = new JPasswordField(20);
				Border lineP = BorderFactory.createLineBorder(new Color(194,194,194), 2);
				Border emptyP = new EmptyBorder(0, 5, 0, 0);
				CompoundBorder borderP = new CompoundBorder(lineP, emptyP);
				passtf.setBorder(borderP);
				passtf.setFont(new Font("Segoe UI", Font.PLAIN, 15)); // Mirar
				
				usernametf = new JTextField(20);
				usernametf = OtherUtils.modifyTextField(usernametf);
				
				telftf = new JTextField(20);
				telftf = OtherUtils.modifyTextField(telftf);
				
				genjcb = new JComboBox<>(Genero.values());
				genjcb.setFont(new Font("Segoe UI", Font.PLAIN, 15));
				genjcb.setBackground(Color.WHITE);
				
				dirtf = new JTextField(20);
				dirtf = OtherUtils.modifyTextField(dirtf);
				
				//Implemantación del calendario
				UtilDateModel model = new UtilDateModel();
				Properties p = new Properties();
				p.put("text.today", "Hoy");
				p.put("text.month", "Mes");
				p.put("text.year", "Año");
				JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
				AbstractFormatter af = new AbstractFormatter() {

					private static final long serialVersionUID = 1L;
					private String datePattern = "yyyy/MM/dd";
				    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
					@Override
					public String valueToString(Object value) throws ParseException {
						if (value != null) {
				            Calendar cal = (Calendar) value;
				            return dateFormatter.format(cal.getTime());
				        }
				        return "";
					}
					
					@Override
					public Object stringToValue(String text) throws ParseException {
						return dateFormatter.parseObject(text);
					}
				};
				datePicker = new JDatePickerImpl(datePanel, af);
				datePicker.setFont(new Font("Segoe UI", Font.PLAIN, 15));
				datePicker.setBackground(Color.WHITE);
				datePicker.setForeground(Color.GRAY);
				// Botón reg
				JButton reg = new JButton("Registrarse");
				reg = OtherUtils.modifyButton(reg, new Color(67, 67, 67), new Color(194, 194, 194));

				// ActionListener reg
				reg.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (usertf.getText().isBlank() || usernametf.getText().isBlank() || String.valueOf(passtf.getPassword()).isBlank()) {
							JOptionPane.showMessageDialog(null, "Debes rellenar todos los campos", "Advertencia", JOptionPane.WARNING_MESSAGE);		
						} else {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
							Date date;
							try {
								date = sdf.parse(datePicker.getJFormattedTextField().getText());
								long fecha = date.getTime();
								BaseDeDatos.addUser(usertf.getText(), usernametf.getText(), String.valueOf(passtf.getPassword()), Integer.valueOf(telftf.getText()), (Genero) genjcb.getSelectedItem(), new Date(fecha), dirtf.getText());
								frameReg.dispose();
							} catch (ParseException e1) {
								VentanaTienda.logger.log(Level.SEVERE, "No se ha podido añadir el usuario");
							}

						}
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
		        
		        gc.gridx = 0;
		        gc.gridy = 4;
		        labelText.add(telf, gc);
		        
		        gc.gridx = 1;
		        gc.gridy = 4;
		        labelText.add(telftf, gc);
		        
		        gc.gridx = 0;
		        gc.gridy = 5;
		        labelText.add(gen, gc);
		        
		        gc.gridx = 1;
		        gc.gridy = 5;
		        labelText.add(genjcb, gc);
		        
		        gc.gridx = 0;
		        gc.gridy = 6;
		        labelText.add(fechanac, gc);
		        
		        gc.gridx = 1;
		        gc.gridy = 6;
		        labelText.add(datePicker, gc);
		        
		        gc.gridx = 0;
		        gc.gridy = 7;
		        labelText.add(dir, gc);
		        
		        gc.gridx = 1;
		        gc.gridy = 7;
		        labelText.add(dirtf, gc);
				
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
