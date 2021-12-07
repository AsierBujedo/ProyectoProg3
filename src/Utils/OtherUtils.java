package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeSet;
import java.util.logging.Level;

import Tienda.*;

public class OtherUtils {
	public static Properties prop = new Properties();

	/**
	 * Recibe una lista de productos y el ID único como identificador de un
	 * producto. La función recorre recursivamente la lista hasta que encuentra el
	 * Producto.
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
		} else {
			if (list.get(cont).getID() == codigo) {
				return list.get(cont);
			} else {
				cont++;
				return buscarEnLista(list, codigo, cont);
			}
		}
		return null;
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
}
