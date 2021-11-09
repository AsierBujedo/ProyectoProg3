package Ajedrez;

import java.awt.Color;
import java.awt.*;


import javax.swing.*;

public class Tablero {

public static JPanel TableroAjedrez() {
    JPanel PanelTableroAjedrez = new JPanel();
    String[][] Casillas = new String[8][8]; // Como todos sabemos el tablero tiene 8x8 casillas
   
    // Recorremos el eje Y del tablero
    for(int y=0; y < Casillas.length; y++) {
    	// Recorremos el eje X del tablero
    	for(int x=0; x < Casillas[y].length; x++) {
    	    
    		// Creamos un JButton que va ha ir tomando los valores de X e Y
    		final JButton jButton = new JButton(Casillas[y][x]);
    		Cuadrado cuadrado=new Cuadrado(1, null, null, false, x, y, jButton);
    	    PanelTableroAjedrez.add(jButton);
    	    if((y+x+1)%2==0){
    	        // si cumple la condición, el botón será de color negro
    	        jButton.setBackground(Color.BLACK);
    	    }else{
    	        // en caso contrario, el botón será de color blanco
    	        jButton.setBackground(Color.WHITE);
    	    }
    	   // Icon i=new ImageIcon("src\\imagenes\\bpawn.png");
    	   // jButton.setIcon(i);
    	}
    }

    PanelTableroAjedrez.setLayout(new GridLayout(8, 8));
    
    
    return PanelTableroAjedrez;
}       


public static void main(String[] args) {
    JFrame Ventana = new JFrame("Tablero de Ajedrez");
    Ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    Ventana.setLayout(new GridLayout(1, 1));
    Ventana.add(TableroAjedrez());

    Ventana.setLocation(500, 200);
    Ventana.setPreferredSize(new Dimension(500, 500));
    Ventana.pack();
    Ventana.setVisible(true);
	}
}
