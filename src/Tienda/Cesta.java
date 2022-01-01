package Tienda;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import Utils.OtherUtils;
import java.awt.*;
import java.awt.event.*;

/**Clase Cesta: 
 *  Gestiona la cesta del cliente, permite a�adir productos, retirarlos o buscar en el carro.
 * @author GR08
 */
public class Cesta {
	public static ArrayList<Producto> cesta = new ArrayList<Producto>();
	public static HashMap<String, ArrayList<Producto>> lastCompra = new HashMap<String, ArrayList<Producto>>();

	/**M�todo getCesta: 
	 *  Obtiene la cesta actual.
	 * @return ArrayList<Producto>
	 */
	public ArrayList<Producto> getCesta() {
		return cesta;
	}

	/**M�todo setCesta:
	 *  Sustituye la actual cesta por una nueva.
	 * @param cesta
	 */
	public void setCesta(ArrayList<Producto> cesta) {
		Cesta.cesta = cesta;
	}
	/**M�todo addToCesta: 
	 *  A�ade un nuevo producto a la cesta
	 * @param p
	 */
	public void addToCesta(Producto p) {
		Cesta.cesta.add(p);
	}
	/**M�todo panelCesta: 
	 * Devuelve un Panel con todas las caracter�sticas necesarias para su gesti�n.
	 * @return JPanel
	 */
	public JPanel panelCesta() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		Border lowered_bevelborder = BorderFactory.createLoweredBevelBorder();
		panel.setBorder(lowered_bevelborder);
		
		JTextField buscarProd = new JTextField("Introduce un n�mero (ID) de producto", 30);
		Border line = BorderFactory.createLineBorder(new Color(194,194,194), 2);
		Border empty = new EmptyBorder(0, 5, 0, 0);
		CompoundBorder border = new CompoundBorder(line, empty);
		buscarProd.setBorder(border);
		buscarProd.setFont(new Font("Segoe UI", Font.PLAIN, 19));
		buscarProd.setForeground(Color.GRAY);
		
		JButton buscar = new JButton(new ImageIcon("search.png"));
		buscar.setBorderPainted(false);
		buscar.setForeground(Color.WHITE);
		buscar.setBackground(new Color(194,194,194));
		
		// FocusListener buscarProd
		buscarProd.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (buscarProd.getText().equals("Introduce un n�mero (ID) de producto")) {
					Border line = BorderFactory.createLineBorder(new Color(245, 182, 66), 2);
					Border empty = new EmptyBorder(0, 5, 0, 0);
					CompoundBorder border = new CompoundBorder(line, empty);
					buscarProd.setBorder(border);
					buscar.setBackground(new Color(245, 182, 66));
					buscarProd.setForeground(Color.BLACK);
					buscarProd.setText("");
				}
				super.focusGained(e);
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (buscarProd.getText().equals("")) {
					Border line = BorderFactory.createLineBorder(new Color(194,194,194), 2);
					Border empty = new EmptyBorder(0, 5, 0, 0);
					CompoundBorder border = new CompoundBorder(line, empty);
					buscarProd.setBorder(border);
					buscar.setBackground(new Color(194,194,194));
					buscarProd.setForeground(Color.GRAY);
					buscarProd.setText("Introduce un n�mero (ID) de producto");
				}
				super.focusLost(e);
			}
		});
				
		// KeyListener buscarProd
		buscarProd.addKeyListener(new KeyAdapter() {
					
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					if (!buscarProd.getText().equals("Introduce un n�mero (ID) de producto")) {
						Producto p = OtherUtils.buscarEnLista(cesta, Integer.valueOf(buscarProd.getText()), 0);
						if (p != null) {
							JOptionPane.showMessageDialog(null, "Nombre: " + p.getNombre() + ", Marca: " + p.getMarca()
									+ ", Precio: " + p.getPrecio() + "�, C�digo: " + p.getCodigoProducto(), p.getNombre(), 1);
						} else {
							JOptionPane.showMessageDialog(null, "No existe ning�n producto con ese ID", "Error de b�squeda", 0);
						}
					}
				}
			}
		});
		
		// MouseListener buscar
		buscar.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				if (buscarProd.hasFocus()) {
				buscar.setBackground(new Color(245, 182, 66).darker());
				}
			}

			public void mouseExited(MouseEvent evt) {
				if (buscarProd.hasFocus()) {
				buscar.setBackground(new Color(245, 182, 66));
				}
			}
		});

		panel.add(buscarProd);
		panel.add(buscar);

		/**ActionListener para el bot�n buscar.
		 * Llama a un m�todo recursivo y obtiene en producto con el id previamente dado. 
		 */
		buscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!buscarProd.getText().equals("Introduce un n�mero (ID) de producto")) {
					Producto p = OtherUtils.buscarEnLista(cesta, Integer.valueOf(buscarProd.getText()), 0);
					if (p != null) {
						JOptionPane.showMessageDialog(null, "Nombre: " + p.getNombre() + ", Marca: " + p.getMarca()
								+ ", Precio: " + p.getPrecio() + "�, C�digo: " + p.getCodigoProducto(), p.getNombre(), 1);
					} else {
						JOptionPane.showMessageDialog(null, "No existe ning�n producto con ese ID", "Error de b�squeda", 0);
					}
				}
				
			}
		});
		return panel;
	}
}
