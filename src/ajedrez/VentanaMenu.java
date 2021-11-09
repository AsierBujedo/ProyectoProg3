package ajedrez;


import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

// Hay que decidir como vamos a hacer el menú. ¿Qué opciones va a tener?
public class VentanaMenu extends JFrame {
	
	protected JTextField textNomUsuario;
	
	public VentanaMenu() {		
		Container cp = this.getContentPane();
		JPanel panel1 = new JPanel();
		
		JLabel labelNomJugador= new JLabel("Nombre del jugador: ");
		textNomUsuario = new JTextField(20);
		
		panel1.add(labelNomJugador);
		panel1.add(textNomUsuario);
		
		cp.add(panel1, BorderLayout.NORTH);
		
		//cp.setBackground(Color.BLACK);
		this.setTitle("Ajedrez");
		this.setSize(960, 540);
        this.setResizable(false); // No deja cambiar el tamaño de la ventana
        
        // Aquí asigno una imagen como icono de la ventan
//		try {
//            BufferedImage iconoVentana = ImageIO.read(getClass().getResource("xxxxx.png"));
//            this.setIconImage(iconoVentana); // Asigna iconoVentana como icono de imagen de la ventana
//        }  catch (Exception e) {
//            System.out.println("Archivo bp.png no encontrado");
//        }
        
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// Método main de prueba para poder ejecutar y ver la ventana. En esta clase no habrá un método main.
	public static void main(String[] args) {
		VentanaMenu ventana = new VentanaMenu();
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}
	
	
}
