package Utils;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Tienda.VentanaTienda;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import BD.BaseDeDatos;

import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JRadioButton;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.Panel;

public class VentanaInfo {
	
	public static void main(String[] args) {
		VentanaTienda.iniciaLog();
		BaseDeDatos.InitDB();
		VentanaInfo frame = new VentanaInfo();
		BaseDeDatos.closeDB();
	}


	public VentanaInfo() {
		JFrame frame = new JFrame();
		frame.setUndecorated(false);
		JPanel principal = new JPanel();
		principal.setBackground(Color.WHITE);
		principal.setLayout(null);
		
		frame.getContentPane().add(principal);
		
		JTextArea txtAreaInfo = new JTextArea();
		txtAreaInfo.setEditable(false);
		txtAreaInfo.setForeground(Color.GRAY);
		txtAreaInfo.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));
		txtAreaInfo.setText("Proporciona tu informaci\u00F3n personal. Esta informaci\u00F3n se mantendr\u00E1 privada y no se incluir\u00E1 en tu perfil p\u00FAblico.");
		txtAreaInfo.setBounds(20, 10, 341, 46);
		principal.add(txtAreaInfo);
		txtAreaInfo.setLineWrap(true);
		txtAreaInfo.setWrapStyleWord(true);
		
		JLabel lblMail = new JLabel("Direcci\u00F3n de correo electr\u00F3nico");
		lblMail.setForeground(Color.GRAY);
		lblMail.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));
		lblMail.setBounds(20, 65, 185, 13);
		principal.add(lblMail);
		
		JTextField txtMail = new JTextField();
		txtMail.setForeground(Color.BLACK);
		txtMail.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));
		txtMail.setBounds(20, 85, 199, 19);
		txtMail.setBorder(null);
		txtMail.setColumns(10);
		
		String nombre = VentanaTienda.loginItem.getText();
		String mail = BaseDeDatos.getUserMail(nombre);
		txtMail.setText(mail);
		
		principal.add(txtMail);
		
		JLabel lblNumero = new JLabel("N\u00FAmero de tel\u00E9fono");
		lblNumero.setForeground(Color.GRAY);
		lblNumero.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));
		lblNumero.setBounds(20, 245, 185, 13);
		principal.add(lblNumero);
		
		try {
			MaskFormatter masc = new MaskFormatter("#########");
			JFormattedTextField txtTelefono = new JFormattedTextField(masc);
			txtTelefono.setHorizontalAlignment(SwingConstants.LEFT);
			txtTelefono.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));
			txtTelefono.setBounds(20, 265, 199, 19);
			txtTelefono.setBorder(null);
			principal.add(txtTelefono);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setForeground(Color.GRAY);
		lblSexo.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));
		lblSexo.setBounds(20, 185, 185, 13);
		principal.add(lblSexo);
		
		JTextField txtSexo = new JTextField();
		txtSexo.setEditable(false);
		txtSexo.setBackground(Color.WHITE);
		txtSexo.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));
		txtSexo.setColumns(10);
		txtSexo.setBorder(null);
		txtSexo.setBounds(20, 205, 199, 19);
		principal.add(txtSexo);
		txtSexo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame frame = new JFrame();
				JPanel principal = new JPanel();
				principal.setBackground(Color.WHITE);
				principal.setLayout(null);
				frame.getContentPane().add(principal);
				
				JRadioButton btnHombre = new JRadioButton("Hombre");
				btnHombre.setBackground(Color.WHITE);
				btnHombre.setBounds(24, 21, 103, 21);
				principal.add(btnHombre);
				
				JRadioButton btnMujer = new JRadioButton("Mujer");
				btnMujer.setBackground(Color.WHITE);
				btnMujer.setBounds(24, 44, 103, 21);
				principal.add(btnMujer);
				
				ButtonGroup elecciones = new ButtonGroup();		
				elecciones.add(btnMujer);
				elecciones.add(btnHombre);
				
				Panel panel = new Panel();
				panel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(btnHombre.isSelected()) {
							txtSexo.setText("Hombre");
							frame.dispose();
						}else if (btnMujer.isSelected()) {
							txtSexo.setText("Mujer");
							frame.dispose();
						}else {
							JOptionPane.showMessageDialog(null, "Ninguna opción seleccionada");
						}
							
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						panel.setBackground(new Color(0, 206, 209).darker());	
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						panel.setBackground(new Color(0, 206, 209));	
					}
				});
				panel.setBackground(new Color(0, 206, 209));
				panel.setBounds(51, 89, 76, 25);
				principal.add(panel);
				panel.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("Confirmar");
				lblNewLabel.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));
				lblNewLabel.setForeground(Color.WHITE);
				lblNewLabel.setBounds(10, 0, 57, 23);
				panel.add(lblNewLabel);
				
				frame.setResizable(false);
				frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				frame.setSize(181, 162);
				frame.setVisible(true);
				frame.setIconImage(VentanaTienda.icon);
				frame.setLocationRelativeTo(null);
			}
		});
		
		JLabel lblFecha = new JLabel("Fecha de nacimiento");
		lblFecha.setForeground(Color.GRAY);
		lblFecha.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));
		lblFecha.setBounds(20, 365, 185, 13);
		principal.add(lblFecha);
		
		try {
			MaskFormatter modelo = new MaskFormatter("####/##/##");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			JFormattedTextField txtDate = new JFormattedTextField(modelo);
			Date date = sdf.parse(txtDate.getText());
			txtDate.setHorizontalAlignment(SwingConstants.LEFT);
			txtDate.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));
			txtDate.setBounds(20, 385, 199, 19);
			txtDate.setBorder(null);
			principal.add(txtDate);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		JSeparator lineMail = new JSeparator();
		lineMail.setBounds(20, 105, 199, 2);
		lineMail.setForeground(Color.GRAY);
		principal.add(lineMail);

		JSeparator lineNumero = new JSeparator();
		lineNumero.setForeground(Color.GRAY);
		lineNumero.setBounds(20, 285, 199, 2);
		principal.add(lineNumero);
		
		JSeparator lineFecha = new JSeparator();
		lineFecha.setForeground(Color.GRAY);
		lineFecha.setBounds(20, 405, 199, 2);
		principal.add(lineFecha);
		
		JSeparator lineSexo = new JSeparator();
		lineSexo.setForeground(Color.GRAY);
		lineSexo.setBounds(20, 225, 199, 2);
		principal.add(lineSexo);
		
		JLabel lblDireccinDelDomicilio = new JLabel("Direcci\u00F3n del domicilio");
		lblDireccinDelDomicilio.setForeground(Color.GRAY);
		lblDireccinDelDomicilio.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));
		lblDireccinDelDomicilio.setBounds(20, 305, 185, 13);
		principal.add(lblDireccinDelDomicilio);
		
		JTextField textField = new JTextField();
		textField.setForeground(Color.BLACK);
		textField.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));
		textField.setColumns(10);
		textField.setBorder(null);
		textField.setBounds(20, 325, 199, 19);
		principal.add(textField);
		
		JSeparator lineMail_1 = new JSeparator();
		lineMail_1.setForeground(Color.GRAY);
		lineMail_1.setBounds(20, 345, 199, 2);
		principal.add(lineMail_1);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setForeground(Color.GRAY);
		lblUsuario.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));
		lblUsuario.setBounds(20, 125, 185, 13);
		principal.add(lblUsuario);
		
		JSeparator lineNumero_1 = new JSeparator();
		lineNumero_1.setForeground(Color.GRAY);
		lineNumero_1.setBounds(20, 165, 199, 2);
		principal.add(lineNumero_1);
		
		JTextField txtTelefono = new JTextField();
		txtTelefono.setHorizontalAlignment(SwingConstants.LEFT);
		txtTelefono.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));
		txtTelefono.setBorder(null);
		txtTelefono.setBounds(20, 145, 199, 19);
		principal.add(txtTelefono);
		
		Panel panelCambios = new Panel();
		panelCambios.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				panelCambios.setBackground(new Color(0, 206, 209).darker());
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				panelCambios.setBackground(new Color(0, 206, 209));
			}
		});
		panelCambios.setBackground(new Color(0, 206, 209));
		panelCambios.setBounds(126, 433, 113, 29);
		principal.add(panelCambios);
		panelCambios.setLayout(null);
		
		JLabel lblCambios = new JLabel("Aplicar cambios");
		lblCambios.setForeground(Color.WHITE);
		lblCambios.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));
		lblCambios.setBounds(10, 0, 96, 29);
		panelCambios.add(lblCambios);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setTitle("Informacion personal");
		frame.setSize(380, 520);
		frame.setIconImage(VentanaTienda.icon);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
	}
}