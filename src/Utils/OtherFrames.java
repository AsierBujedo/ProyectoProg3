package Utils;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import BD.BaseDeDatos;
import BD.COLS;
import Tienda.VentanaTienda;
import Tienda.Cesta;
import Tienda.PanelTabla;
import Tienda.Producto;
import Tienda.Cesta.*;
import Tienda.DatoParaTabla;


public class OtherFrames {
	
	//Acceso al area personal
	public static void areaCliente() {
		JFrame frame = new JFrame();
		JButton contra = new JButton("Cambiar contraseña");
		contra.setBorder(new RoundedBorder(7));
		JButton historial = new JButton("Ultima compra");
		historial.setBorder(new RoundedBorder(7));
		frame.setLayout(new GridLayout(2, 1));
		frame.add(contra);
		frame.add(historial);
		
		contra.addActionListener(new ActionListener() {//Ventana para cambio de contraseña
			
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
				JButton cambiar=new JButton("Cambiar contraseña");
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
				
				cambiar.addActionListener(new ActionListener() {//Boton para cambio de contraseñas
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String nombre=VentanaTienda.loginItem.getText();
						String mail=BaseDeDatos.getUserMail(nombre);
						String existe=BaseDeDatos.getUser(mail, String.valueOf(contraVtf.getPassword()));
						if(String.valueOf(contraNtf.getPassword()).equals(String.valueOf(contraNCtf.getPassword()))&&!existe.equals("Error")) {
							BaseDeDatos.editUser(COLS.PASS, mail, String.valueOf(contraVtf.getPassword()), String.valueOf(contraNtf.getPassword()));
						}else {
							JOptionPane.showMessageDialog(null, "Alguna de las contraseñas no coincide","Error", 0);
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
			}
		});
		historial.addActionListener(new ActionListener() {//ventana para ultima compra
			
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String nombre=VentanaTienda.loginItem.getText();
			String mail=BaseDeDatos.getUserMail(nombre);
			if(Cesta.lastCompra.containsKey(mail)){
				JFrame frame = new JFrame();
				Vector<String> nomColumnas = new Vector<String>( Arrays.asList( "Código de producto", "Nombre", "Precio", "Marca" ) );
				JTable tabla=new JTable();
				DefaultTableModel model = new DefaultTableModel(new Vector<Vector<Object>>(), nomColumnas);
				for (Producto p : Cesta.lastCompra.get(mail)) {
					model.addRow( new Object[] { p.getCodigoProducto(), p.getNombre(), p.getPrecio(), p.getMarca()} );
				}
				tabla.setModel(model);
				frame.add(new JScrollPane(tabla));
				frame.setTitle("Última compra");
				frame.setSize(800, 500);
				frame.setResizable(false);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				frame.setIconImage(VentanaTienda.icon);
			}else {
				JOptionPane.showMessageDialog(null, "Tu usuario no tiene ninguna compra registrada","Compra no encontrada", 1);
			}
		}
	});

	frame.setTitle("Registro de usuarios");
	frame.setSize(400, 225);
	frame.setResizable(false);
	frame.setVisible(true);
	frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	frame.setIconImage(VentanaTienda.icon);
	}

}