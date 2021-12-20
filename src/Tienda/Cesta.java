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
	public static ArrayList<Producto> cesta = new ArrayList<Producto>();
	public static HashMap<String, ArrayList<Producto>> lastCompra = new HashMap<String, ArrayList<Producto>>();

	/**Método getCesta: 
	 *  Obtiene la cesta actual.
	 * @return ArrayList<Producto>
	 */
	public ArrayList<Producto> getCesta() {
		return cesta;
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
		JTextField buscarProd = new JTextField("Introduce el número (ID) del producto", 30);
		buscarProd.setFont(new Font("Uni Sans Heavy", Font.PLAIN, 12));
		buscarProd.setBorder(new RoundedBorder(5));
		buscarProd.setForeground(Color.GRAY);
		
		buscarProd.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (buscarProd.getText().equals("Introduce el número (ID) del producto")) {
					buscarProd.setForeground(Color.BLACK);
					buscarProd.setText("");
				}
				super.focusGained(e);
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (buscarProd.getText().equals("")) {
					buscarProd.setForeground(Color.GRAY);
					buscarProd.setText("Introduce el número (ID) del producto");
				}
				super.focusLost(e);
			}
		});

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
				if (!buscarProd.getText().equals("Introduce el número (ID) del producto")) {
					Producto p = OtherUtils.buscarEnLista(cesta, Integer.valueOf(buscarProd.getText()), 0);
					if (p != null) {
						JOptionPane.showMessageDialog(null, "Nombre: " + p.getNombre() + ", Marca: " + p.getMarca()
								+ ", Precio: " + p.getPrecio() + "€, Código: " + p.getCodigoProducto(), p.getNombre(), 1);
					} else {
						JOptionPane.showMessageDialog(null, "No existe ningún producto con ese ID", "Error de búsqueda", 0);
					}
				}
				
			}
		});
		return panel;
	}
}
