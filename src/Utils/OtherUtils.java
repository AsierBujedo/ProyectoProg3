package Utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeSet;
import java.util.logging.Level;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import Tienda.*;

public class OtherUtils {
	public static Properties prop = new Properties();

	/**
	 * Escribe los datos creados mediante las instancias de cada uno de los
	 * productos en el fichero datos.dat.
	 */
	public static void escribeDatos() {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("datos.dat");
			@SuppressWarnings("resource")
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			ArrayList<Producto> list = new ArrayList<Producto>();
			list.add(new Impresora("I001", "Canon PIXMA", 56.99, "Canon", "PIXMA TS3350", true, false));
			list.add(new Telefono("T002", "Apple iPhone 12", 949.00, "Apple", "Pro Max", 128, 6, 60, 4352));
			list.add(new Ordenador("O001", "Lenovo Ideapad 2", 500.00, "Lenovo", "Ideapad 2", 500, 8, 2));
			list.add(new Telefono("T001", "Samsung Galaxy S21 5G", 859.99, "Samsung", "S21", 128, 8, 120, 4000));
			list.add(new Impresora("I002", "Impresora multifunción láser", 188.00, "Brother", "Brother MFC-L2710DW",
					true, true));

			list.add(new Pantalon("P001", "Pantalón Tapered Caroenter", 69.99, "Levis", Talla.L, Color.BLUE,
					Pais.ALEMANIA));
			list.add(new Zapatilla("Z002", "Zapatilla Superstar", 100.00, "Adidas", "Superstar", 41.00, Color.WHITE,
					Pais.CHINA));
			list.add(new Sudadera("S002", "Nike Dri-FIT", 59.99, "Nike", Talla.L, Color.BLUE, true, Pais.CHINA));
			list.add(new Zapatilla("Z001", "Nike Sportswear AIR Force 1", 79.99, "Nike", "AIR Force 1", 42.00,
					Color.BLACK, Pais.CHINA));
			list.add(new Sudadera("S001", "Boundary", 22.99, "Billabong", Talla.M, Color.GREEN, true, Pais.CHINA));

			list.add(new Videoconsola("VC002", "PlayStation 4", 399.99, "Sony", "Modelo Estándar", 500, 8, 3, false,
					false, true));
			list.add(new Libro("L001", "La casa de los espíritus", 19.99, "Debolsillo", "Isabel Allende", "Debolsillo",
					Color.BLUE, true));
			list.add(new Videojuego("VJ001", "Spider-Man", 39.99, "PS4", 2018, "Insomniac Games", false, 46));
			list.add(new Libro("L002", "Harry Potter y la piedra filosofal", 24.99, "Salamandra", "J. K. Rowling",
					"Editorial", Color.RED, true));
			list.add(new Videoconsola("VC001", "PlayStation 5", 499.99, "Sony", "Edición normal", 825, 16, 5, false,
					false, true));

			oos.writeObject(list);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Recibe una lista de productos y el ID único como identificador de un
	 * producto. La función recorre recursivamente la lista hasta que encuentra el
	 * producto.
	 * 
	 * @param list
	 * @param codigo
	 * @param start
	 * @return Producto
	 */
	public static Producto buscarEnLista(ArrayList<Producto> list, int codigo, int start) {
		int cont = start;
		if (list == null || cont >= list.size() || cont < 0) {
			System.err.println("Nada que hacer");
			return null;
		} else {
			if (list.get(cont).getID() == codigo) {
				return list.get(cont);
			} else {
				cont++;
				return buscarEnLista(list, codigo, cont);
			}
		}
	}

	/**
	 * Método que recibe un ArrayList<Producto> y lo devuelve ordenado por precio de
	 * menor a mayor.
	 * 
	 * @param list
	 * @return ArrayList<Producto>
	 */
	public static ArrayList<Producto> ordenaLista(ArrayList<Producto> list) {
		TreeSet<Producto> set = new TreeSet<Producto>();
		ArrayList<Producto> ret = new ArrayList<Producto>();
		for (Producto p : list) {
			set.add(p);
		}
		for (Producto p2 : set) {
			ret.add(p2);
		}
		return ret;
	}

	public static void restartProperties() {
		File f = new File("config.prop");
		if (!f.exists()) {
			prop.setProperty("HOST-PORT", "6666");
			prop.setProperty("CLIENT-PORT", "6666");
			prop.setProperty("CLIENT-IP-DESTINATION", "localhost");
			prop.setProperty("WINDOW-HEIGHT", "700");
			prop.setProperty("WINDOW-WIDTH", "1000");
			try {
				prop.store(new FileOutputStream(f), null);
				VentanaTienda.logger.log(Level.INFO, "PROPERTIES RESTARTED");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				VentanaTienda.logger.log(Level.SEVERE, e.toString());
			}
		} else {
			try {
				prop.load(new FileInputStream(f));
				VentanaTienda.logger.log(Level.INFO, "PROPERTIES SUCCESFULLY LOADED");
			} catch (IOException e) {
				VentanaTienda.logger.log(Level.SEVERE, "ERROR LOADING PROPERTIES");
			}

		}
	}

	/**
	 * Método que recibe un double (preferentemente con muchos decimales), y el
	 * número de decimales a tener en cuenta. La función obtendrá el redondeo a x
	 * decimales del double que le hemos pasado.
	 * 
	 * @param valor
	 * @param dec
	 * @return double
	 */
	public static double round(double valor, int dec) {
		if (dec < 0)
			throw new IllegalArgumentException();
		long factor = (long) Math.pow(10, dec);
		valor = valor * factor;
		long tmp = Math.round(valor);
		return (double) tmp / factor;
	}
	
	
	
	public static JButton modifyButton(JButton boton, Color Foreground, Color Background, Color BackgroundWhenEnetered) {
		boton.setAlignmentX(Component.CENTER_ALIGNMENT);
		boton.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		boton.setForeground(Foreground);
		boton.setBorderPainted(false);
		boton.setFocusPainted(false);
		boton.setContentAreaFilled(false);
		boton.setBackground(Background);
		if (boton.getText() == "Registro") {
			boton.setOpaque(true);
		}

		boton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				if (boton.getText() != "Registro") {
					boton.setOpaque(true);
					boton.setContentAreaFilled(true);
				}
				boton.setBackground(BackgroundWhenEnetered);
			}

			public void mouseExited(MouseEvent evt) {
				if (boton.getText() != "Registro") {
					boton.setOpaque(false);
					boton.setContentAreaFilled(false);
				}
				boton.setBackground(new Color(194,194,194));
			}
		});
		return boton;
	}
	
	public static JTextField modifyTextField(JTextField field) {
		Border line = BorderFactory.createLineBorder(new Color(194,194,194), 2);
		Border empty = new EmptyBorder(0, 5, 0, 0);
		CompoundBorder border = new CompoundBorder(line, empty);
		field.setBorder(border);
		field.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		field.setForeground(Color.GRAY);
		
		field.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Border line = BorderFactory.createLineBorder(new Color(20,115,191), 2);
				Border empty = new EmptyBorder(0, 5, 0, 0);
				CompoundBorder border = new CompoundBorder(line, empty);
				field.setBorder(border);
				field.setForeground(Color.BLACK);
				super.focusGained(e);
			}

			@Override
			public void focusLost(FocusEvent e) {
				Border line = BorderFactory.createLineBorder(new Color(194,194,194), 2);
				Border empty = new EmptyBorder(0, 5, 0, 0);
				CompoundBorder border = new CompoundBorder(line, empty);
				field.setBorder(border);
				field.setForeground(Color.GRAY);
				super.focusLost(e);
			}
		});
		
		field.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				field.setCursor(new Cursor(Cursor.TEXT_CURSOR));
			}
			
		});
		return field;
	}
	
	public static JPasswordField modifyPasswordField(JPasswordField field) {
		Border line = BorderFactory.createLineBorder(new Color(194,194,194), 2);
		Border empty = new EmptyBorder(0, 5, 0, 0);
		CompoundBorder border = new CompoundBorder(line, empty);
		field.setBorder(border);
		field.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		field.setForeground(Color.GRAY);
		
		field.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Border line = BorderFactory.createLineBorder(new Color(20,115,191), 2);
				Border empty = new EmptyBorder(0, 5, 0, 0);
				CompoundBorder border = new CompoundBorder(line, empty);
				field.setBorder(border);
				field.setForeground(Color.BLACK);
				super.focusGained(e);
			}

			@Override
			public void focusLost(FocusEvent e) {
				Border line = BorderFactory.createLineBorder(new Color(194,194,194), 2);
				Border empty = new EmptyBorder(0, 5, 0, 0);
				CompoundBorder border = new CompoundBorder(line, empty);
				field.setBorder(border);
				field.setForeground(Color.GRAY);
				super.focusLost(e);
			}
		});
		return field;
	}

}
