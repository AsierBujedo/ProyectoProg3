package ajedrez;

import java.awt.Color;
import java.util.ArrayList;
import java.awt.*;


import javax.swing.*;

public class Tablero extends JPanel {
	protected ArrayList<Pieza> piezasBlancas; // ArrayList en el que se almacenarán las piezas blancas
	protected ArrayList<Pieza> piezasNegras; // ArrayList en el que se almacenarán las piezas negras
	protected Cuadrado[][] tablero; // [Fila], [Columna]
	
	//Aun faltan más atributos de la clase Tablero
	
	// Método inicializarTablero()
	/**
	 * Inicializa el tablero colocando todas las piezas
	 */
	public void inicializarTablero() {
		
		// Inicialización de las reinas
		tablero[0][3].ponerPieza(new Reina(0, tablero[0][3], "negroReina.png"));
		tablero[7][3].ponerPieza(new Reina(1, tablero[7][3], "blancoReina.png"));
		
		// Inicialización de los reyes
        tablero[0][4].ponerPieza(new Rey(0, tablero[0][4], "negroRey.png"));
        tablero[7][4].ponerPieza(new Rey(1, tablero[7][4], "blancoRey.png"));
        
        // Inicialización de las torres
        tablero[0][0].ponerPieza(new Torre(0, tablero[0][0], "negroTorre.png"));
        tablero[0][7].ponerPieza(new Torre(0, tablero[0][7], "negroTorre.png"));
        tablero[7][0].ponerPieza(new Torre(1, tablero[7][0], "blancoTorre.png"));
        tablero[7][7].ponerPieza(new Torre(1, tablero[7][7], "blancoTorre.png"));
        
        // Inicialización de los caballos
        tablero[0][1].ponerPieza(new Caballo(0, tablero[0][1], "negroCaballo.png"));
        tablero[0][6].ponerPieza(new Caballo(0, tablero[0][6], "negroCaballo.png"));
        tablero[7][1].ponerPieza(new Caballo(1, tablero[7][1], "blancoCaballo.png"));
        tablero[7][6].ponerPieza(new Caballo(1, tablero[7][6], "blancoCaballo.png"));
        
        // Inicialización de los alfiles
        tablero[0][2].ponerPieza(new Alfil(0, tablero[0][2], "negroAlfil.png"));
        tablero[0][5].ponerPieza(new Alfil(0, tablero[0][5], "negroAlfil.png"));
        tablero[7][2].ponerPieza(new Alfil(1, tablero[7][2], "blancoAlfil.png"));
        tablero[7][5].ponerPieza(new Alfil(1, tablero[7][5], "blancoAlfil.png"));
        
        // Inicialización de los peones
        for (int x = 0; x < 8; x++) { // La variable x es la columna en la que ponemos el peon
            tablero[1][x].ponerPieza(new Peon(0, tablero[1][x], "negroPeon.png"));
            tablero[6][x].ponerPieza(new Peon(1, tablero[6][x], "blancoPeon.png"));
        }
        
        // Ahora hay que almacenar las piezas en sus ArrayList correspondientes
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

//public static JPanel TableroAjedrez() {
//    JPanel PanelTableroAjedrez = new JPanel();
//    String[][] Casillas = new String[8][8]; // Como todos sabemos el tablero tiene 8x8 casillas
//   
//    // Recorremos el eje Y del tablero
//    for(int y=0; y < Casillas.length; y++) {
//    	// Recorremos el eje X del tablero
//    	for(int x=0; x < Casillas[y].length; x++) {
//    	    
//    		// Creamos un JButton que va ha ir tomando los valores de X e Y
//    		final JButton jButton = new JButton(Casillas[y][x]);
//    	    PanelTableroAjedrez.add(jButton);
//    	    if((y+x+1)%2==0){
//    	        // si cumple la condiciÃ³n, el botÃ³n serÃ¡ de color negro
//    	        jButton.setBackground(Color.BLACK);
//    	    }else{
//    	        // en caso contrario, el botÃ³n serÃ¡ de color blanco
//    	        jButton.setBackground(Color.WHITE);
//    	    }
//    	}
//    }
//
//    PanelTableroAjedrez.setLayout(new GridLayout(8, 8));
//    
//    
//    return PanelTableroAjedrez;
//}       
//
//
//
//
//
//
//public static void main(String[] args) {
//    JFrame Ventana = new JFrame("Tablero de Ajedrez");
//    Ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//
//    Ventana.setLayout(new GridLayout(1, 1));
//    Ventana.add(TableroAjedrez());
//
//    Ventana.setLocation(500, 200);
//    Ventana.setPreferredSize(new Dimension(500, 500));
//    Ventana.pack();
//    Ventana.setVisible(true);
//	}
}
