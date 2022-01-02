package Utils;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Tienda.VentanaTienda;

import javax.swing.JRadioButton;

public class VentanarEleccion {

	public static void main(String[] args) {
		VentanarEleccion frame = new VentanarEleccion();

	}

	public VentanarEleccion() {
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
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(24, 82, 121, 21);
		btnConfirmar.setBorder(new RoundedBorder(7));
		principal.add(btnConfirmar);
		btnConfirmar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(btnHombre.isSelected() || btnMujer.isSelected())
					System.out.println("Seleccion");
				
			}
		});
		
		frame.setResizable(false);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setSize(181, 162);
		frame.setVisible(true);
		frame.setIconImage(VentanaTienda.icon);
		frame.setLocationRelativeTo(null);
	}
}
