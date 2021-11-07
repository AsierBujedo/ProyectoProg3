package Ajedrez;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

abstract public class Pieza {
	protected int color;
	protected Cuadrado cuadradoActual;
	protected BufferedImage imagen;
	
	// Constructor de la clase Pieza
	public Pieza(int color, Cuadrado cuadradoActual, String img) {
		super();
		this.color = color;
		this.cuadradoActual = cuadradoActual;
		
		try {
			this.imagen = ImageIO.read(getClass().getResource(img));

		} catch (IOException e) {
			System.out.println("Archivo no encontrado: " + e.toString());
		}
		
	}
	
	// Getters
	public int getColor() {
		return color;
	}
	
	public Cuadrado getCuadradoActual() {
		return cuadradoActual;
	}
	
	public BufferedImage getImagen() {
		return imagen;
	}
	
	// Setters
	public void setColor(int color) {
		this.color = color;
	}

	public void setCuadradoActual(Cuadrado cuadradoActual) {
		this.cuadradoActual = cuadradoActual;
	}

	public void setImagen(BufferedImage imagen) {
		this.imagen = imagen;
	}
	
	// Métodos propios de la clase Pieza
	
	// Método dibuja() ...
	public void dibuja(Graphics graf) {
        int x = cuadradoActual.getX();
        int y = cuadradoActual.getY();
        
        graf.drawImage(this.imagen, x, y, null);
    }
	
	
	
	
}
