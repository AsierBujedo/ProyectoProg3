package Ajedrez;


import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

// Hay que decidir como vamos a hacer el menú. ¿Qué opciones va a tener?
public class VentanaMenu extends JFrame {
	
	private Image imagenFondo;
	private JTextField textNomUsuario;
	
	public VentanaMenu() {		
		Container cp = this.getContentPane();
		JPanel panel1 = new JPanel();
		
		JLabel labelNomJugador= new JLabel("Nombre del jugador: ");
		textNomUsuario = new JTextField(20);
		
		panel1.add(labelNomJugador);
		panel1.add(textNomUsuario);
		
		cp.add(panel1, BorderLayout.NORTH);
		
		cp.setBackground(Color.BLACK);
		this.setTitle("Ajedrez - Menú");
		this.setSize(960, 540);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// Método main de prueba para poder ejecutar y ver la ventana. En esta clase no habrá un método main.
	public static void main(String[] args) {
		VentanaMenu ventana = new VentanaMenu();
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}
	
	
}
