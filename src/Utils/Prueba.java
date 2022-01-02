package Utils;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import BD.BaseDeDatos;
import Tienda.VentanaTienda;

public class Prueba extends JFrame {
	
	public static void main(String[] args) {
		VentanaTienda.iniciaLog();
		BaseDeDatos.InitDB();
		Prueba frame = new Prueba();
		BaseDeDatos.closeDB();
	
	}
	
	public  Prueba() {
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
		frame.add(principal);
		
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
