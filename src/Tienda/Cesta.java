package Tienda;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import Utils.OtherUtils;
import Utils.RoundedBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
/**Clase Cesta: 
 *  Gestiona la cesta del cliente, permite añadir productos, retirarlos o buscar en el carro.
 * @author GR08
 */
public class Cesta {
	public static HashMap<String, Cesta> lastCompra = new HashMap<String, Cesta>();
	public static ArrayList<Producto> cesta = new ArrayList<Producto>();
	/**Método getCesta: 
	 *  Obtiene la cesta actual.
	 * @return ArrayList<Producto>
	 */
	public ArrayList<Producto> getCesta() {
		return cesta;
	}

	public static void datosPrueba() {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("datos.dat");
			@SuppressWarnings("resource")
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			ArrayList<Producto> list = new ArrayList<Producto>();
			list.add(new Libro("L123", "La casa de los espíritus", 19.99, "Editorial", "Isabel Allende",
					"Editorial", Color.BLUE, true));
			list.add(new Libro("L124", "Harry Potter", 24.99, "Editorial", "J. K. Rowling", "Editorial", Color.RED,
					true));
			list.add(new Ordenador("O223", "Lenovo Ideapad", 500.00, "Lenovo", "Ideapad 2", 500, 8, 2));
			list.add(new Zapatilla("Z323", "Nike Air", 99.99, "Nike", null, 42.00, Color.BLACK, Pais.CHINA));
			list.add(new Sudadera("Z423", "Sudadera", 22.99, "Billabong", Talla.M, Color.GREEN, true, Pais.CHINA));
			oos.writeObject(list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**Método setCesta:
	 *  Sustituye la actual cesta por una nueva.
	 * @param cesta
	 */
	public void setCesta(ArrayList<Producto> cesta) {
		Cesta.cesta = cesta;
	}
	/**Método addToCesta: 
	 *  Añade un nuevo producto a la cesta
	 * @param p
	 */
	public void addToCesta(Producto p) {
		Cesta.cesta.add(p);
	}
	/**Método panelCesta: 
	 * Devuelve un Panel con todas las características necesarias para su gestión.
	 * @return JPanel
	 */
	public JPanel panelCesta() {
		JPanel panel = new JPanel();
		JTextField buscarProd = new JTextField(10);
		buscarProd.setBorder(new RoundedBorder(7));

		JButton buscar = new JButton("Buscar en carro");
		buscar.setFont(new Font("Uni Sans Heavy", Font.BOLD, 15));
		buscar.setForeground(Color.WHITE);
		buscar.setBackground(new Color(245, 182, 66));

		buscar.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				buscar.setBorderPainted(false);
				buscar.setBackground(new Color(245, 182, 66).darker());
			}

			public void mouseExited(MouseEvent evt) {
				buscar.setBackground(new Color(245, 182, 66));
			}
		});

		panel.add(buscarProd);
		panel.add(buscar);

		/**ActionListener para el botón buscar.
		 * Llama a un método recursivo y obtiene en producto con el id previamente dado. 
		 */
		buscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Producto p = OtherUtils.buscarEnLista(cesta, Integer.valueOf(buscarProd.getText()), 0);
				if (p != null) {
					JOptionPane.showMessageDialog(null, "Nombre: " + p.getNombre() + ", Marca: " + p.getMarca()
							+ ", Precio: " + p.getPrecio() + "€, Código: " + p.getCodigoProducto(), p.getNombre(), 1);
				} else {
					JOptionPane.showMessageDialog(null, "No existe ningún producto con ese ID", "Error de búsqueda", 0);
				}
			}
		});
		return panel;
	}
}
