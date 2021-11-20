package Utils;

import java.util.ArrayList;
import java.util.TreeSet;

import Tienda.*;

public class OtherUtils {

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
	public Producto buscarEnLista(ArrayList<Producto> list, int codigo, int start) {
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
	 * Método que recibe un ArrayList<Producto> y lo devuelve ordenado por precio de menor a mayor.
	 * @param list
	 * @return ArrayList<Producto>
	 */
	public ArrayList<Producto> ordenaLista(ArrayList<Producto> list) {
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
}
