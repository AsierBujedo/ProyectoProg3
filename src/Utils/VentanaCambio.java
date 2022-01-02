package Utils;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class VentanaCambio {

	public static void main(String[] args) {
		VentanaCambio frame = new VentanaCambio();
				
	}

	public VentanaCambio() {
		JFrame frame = new JFrame();
		frame.setUndecorated(true);
		
		JPanel principal = new JPanel();
		principal.setBackground(Color.WHITE);
		principal.setLayout(null);
		frame.getContentPane().add(principal);
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(Color.WHITE);
		panelSuperior.setBounds(0, 0, 450, 40);
		panelSuperior.setLayout(null);
		principal.add(panelSuperior);
		
		JPanel panelLeft = new JPanel();
		panelLeft.setBackground(Color.WHITE);
		panelLeft.setLayout(null);
		panelLeft.setBounds(0, 0, 54, 40);
		panelSuperior.add(panelLeft);
		
		JLabel lblNewLabel = new JLabel("X");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 54, 40);
		panelLeft.add(lblNewLabel);
		
		JPanel panelRight = new JPanel();
		panelRight.setBackground(Color.WHITE);
		panelRight.setLayout(null);
		panelRight.setBounds(396, 0, 54, 40);
		panelSuperior.add(panelRight);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Eduardo\\Downloads\\tick_1.png"));
		btnNewButton.setBounds(0, 0, 54, 40);
		panelRight.add(btnNewButton);
		
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}
