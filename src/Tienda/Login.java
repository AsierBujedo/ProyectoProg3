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
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import BD.*;
import Utils.OtherUtils;

/**
 * 
 * @author GR08
 *
 */
public class Login {
	public static JTextField usertf;
	public static JPasswordField passtf;
	public static JPasswordField passtflog;
	public static JTextField usernametf;
	public static JTextField telftf;
	public static JComboBox<Genero> genjcb ;
	public static JTextField dirtf;
	public static JDatePickerImpl datePicker;
	
	/**
	 * 
	 */
	public static void doLogin() {
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
		JLabel mail = new JLabel("Email ");
		mail.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		JLabel pass = new JLabel("Contraseña ");
		pass.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		
		usertf = new JTextField(20);
		usertf = OtherUtils.modifyTextField(usertf, "Email");
		
		passtflog = new JPasswordField(20);
		passtflog = OtherUtils.modifyPasswordField(passtflog, "Contraseña");
		
		// Botón log
		JButton log = new JButton("Iniciar sesion");
		log = OtherUtils.modifyButton(log, new Color(67, 67, 67), new Color(194, 194, 194), new Color(225, 225, 225));
		
		// Botón regbutton
		JButton regbutton = new JButton("Registro");
		regbutton = OtherUtils.modifyButton(regbutton, new Color(67, 67, 67), new Color(194, 194, 194), new Color(170,170,170));
				
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
        labelText.add(passtflog, gc);
        
        botonera.add(log);
		frame.add(labelText);
		frame.add(botonera);
		
		// ActionListener log
		log.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String username = BaseDeDatos.getUser(usertf.getText(), String.valueOf(passtflog.getPassword()));
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
						loginlabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
					}

					public void mouseExited(MouseEvent evt) {
						loginlabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
						loginlabel.setForeground(Color.BLACK);
					}
					
					public void mouseClicked(MouseEvent e) {
						frameReg.dispose();
						doLogin();
						usertf.requestFocus();
					}
				});
				
				JLabel mail = new JLabel("Email * ");
				mail = OtherUtils.JLabelWithPopup(mail);		
				JLabel pass = new JLabel("Contraseña * ");
				pass = OtherUtils.JLabelWithPopup(pass);
				JLabel username = new JLabel("Nombre de usuario * ");
				username = OtherUtils.JLabelWithPopup(username);
				JLabel telf = new JLabel("Número de teléfono *");
				telf = OtherUtils.JLabelWithPopup(telf);
				JLabel gen = new JLabel("Género * ");
				gen = OtherUtils.JLabelWithPopup(gen);
				JLabel fechanac = new JLabel("Fecha de nacimiento *");
				fechanac = OtherUtils.JLabelWithPopup(fechanac);
				JLabel dir = new JLabel("Dirección * ");
				dir = OtherUtils.JLabelWithPopup(dir);
				
				usertf = new JTextField(20);
				usertf = OtherUtils.modifyTextField(usertf, "Email");
				
				passtf = new JPasswordField(20);
				passtf = OtherUtils.modifyPasswordField(passtf, "Contraseña");
				
				usernametf = new JTextField(20);
				usernametf = OtherUtils.modifyTextField(usernametf, "Nombre de usuario");
				
				telftf = new JTextField(20);
				telftf = OtherUtils.modifyTextField(telftf, "Número de teléfono");
				
				telftf.addKeyListener(new KeyAdapter() {
			         public void keyPressed(KeyEvent ke) {
			            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode()==8) { //permite escribir solo números o "backspace"
			               telftf.setEditable(true);
			            } else {
			               telftf.setEditable(false);
			            }
			         }
			      });
				
				genjcb = new JComboBox<>(Genero.values());
				genjcb.setFont(new Font("Segoe UI", Font.PLAIN, 15));
				genjcb.setBackground(Color.WHITE);
				
				dirtf = new JTextField(20);
				dirtf = OtherUtils.modifyTextField(dirtf, "Dirección");
				
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
				reg = OtherUtils.modifyButton(reg, new Color(67, 67, 67), new Color(194, 194, 194), new Color(225, 225, 225));

				// ActionListener reg
				reg.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) { 
						if (usertf.getText().equals("Email") || String.valueOf(passtf.getPassword()).equals("Contraseña") || usernametf.getText().equals("Nombre de usuario") || telftf.getText().equals("Número de teléfono") || genjcb.getSelectedIndex() == -1 || dirtf.getText().equals("Dirección") || datePicker.getJFormattedTextField().getText().isBlank()) {
							JOptionPane.showMessageDialog(null, "Debes rellenar todos los campos", "Advertencia", JOptionPane.WARNING_MESSAGE);		
						} else {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
							Date date;
							try {
								date = sdf.parse(datePicker.getJFormattedTextField().getText());
								BaseDeDatos.addUser(usernametf.getText(), usertf.getText(), String.valueOf(passtf.getPassword()), Integer.valueOf(telftf.getText()), (Genero) genjcb.getSelectedItem(), new java.sql.Date(date.getTime()), dirtf.getText());
								frameReg.dispose();
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, "No se ha podido registrar, asegurate de que todos los valores son validos","Error al registrar", JOptionPane.ERROR_MESSAGE);
								VentanaTienda.logger.log(Level.SEVERE, "No se ha podido añadir el usuario || "+ e1.toString());
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
