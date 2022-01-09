package Utils;

import javax.swing.*;

import Tienda.VentanaTienda;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Clase Inicio.
 * Creación de la ventana de presentación del proyecto.
 * @author GR08
 * 
 */
public class Inicio extends JPanel {
	private static final long serialVersionUID = -678038763364969095L;
	private static JTextField txtConsola;
	private static JTextField txtRopa;
	private static JTextField txthastaTusLibros;
	private static JTextField txtCompraYa;

	public static JPanel InitWindow() {
		//Inicializamos la ventana
		JPanel frame = new JPanel();
		JPanel principal = new JPanel();
		
		frame.add(principal);
		principal.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
		panelNorte.setBackground(new Color(0, 206, 209));
		principal.add(panelNorte, BorderLayout.NORTH);
		
		JLabel imagen = new JLabel(new ImageIcon(VentanaTienda.icon));
		imagen.setSize(50, 50);
		panelNorte.add(imagen);
		
		JLabel lblEslogan = new JLabel("TU TIENDA DESDE CASA");
		lblEslogan.setFont(new Font("Niagara Engraved", Font.PLAIN, 80));
		lblEslogan.setHorizontalAlignment(SwingConstants.CENTER);
		panelNorte.add(lblEslogan);
		
		JPanel panelSur = new JPanel();
		panelSur.setBackground(new Color(0, 206, 209));
		principal.add(panelSur, BorderLayout.SOUTH);
		
		JLabel lblFrasePanelSur = new JLabel("Desarrollado por: Asier Bujedo, Eduardo Larrinaga, Iker López y Mikel Lambarri");
		lblFrasePanelSur.setForeground(Color.DARK_GRAY);
		lblFrasePanelSur.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelSur.add(lblFrasePanelSur);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(new Color(224, 255, 255));
		panelCentral.setPreferredSize(new Dimension(957, 900));
		panelCentral.setLayout(null);
		panelCentral.add(scrollPane);

		principal.add(panelCentral, BorderLayout.CENTER);
		
		JLabel lblPS = new JLabel(new ImageIcon("ps5.jpg"));
		lblPS.setBounds(86, 111, 381, 246);
		ImageIcon im = new ImageIcon("ps5.jpg");
		ImageIcon imDimensiones = new ImageIcon(im.getImage().getScaledInstance(lblPS.getWidth(), lblPS.getHeight(), Image.SCALE_DEFAULT));
		lblPS.setIcon(imDimensiones);
		panelCentral.add(lblPS);
		
		JLabel lblNike = new JLabel(new ImageIcon("airforce1.jfif"));
		lblNike.setBounds(547, 221, 381, 294);
		ImageIcon im2 = new ImageIcon("airforce1.jfif");
		ImageIcon imDimensiones2 = new ImageIcon(im2.getImage().getScaledInstance(lblNike.getWidth(), lblNike.getHeight(), Image.SCALE_DEFAULT));
		lblNike.setIcon(imDimensiones2);
		panelCentral.add(lblNike);
		
		JLabel lblHarry = new JLabel(new ImageIcon("harry.jpg"));
		lblHarry.setBounds(74, 510, 381, 360);
		ImageIcon im3 = new ImageIcon("harry.jpg");
		ImageIcon imDimensiones3 = new ImageIcon(im3.getImage().getScaledInstance(lblHarry.getWidth(), lblHarry.getHeight(), Image.SCALE_DEFAULT));
		lblHarry.setIcon(imDimensiones3);
		panelCentral.add(lblHarry);
		
		JLabel lblNosotros = new JLabel("Acerca de nosotros");
		lblNosotros.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNosotros.setBounds(835, 870, 114, 35);
		panelCentral.add(lblNosotros);
		
		txtConsola = new JTextField();
		txtConsola.setForeground(new Color(0, 0, 0));
		txtConsola.setBorder(null);
		txtConsola.setText("Desde videoconsolas...");
		txtConsola.setHorizontalAlignment(SwingConstants.CENTER);
		txtConsola.setFont(new Font("Niagara Solid", Font.PLAIN, 55));
		txtConsola.setEditable(false);
		txtConsola.setColumns(10);
		txtConsola.setBackground(new Color(0, 206, 209));
		txtConsola.setBounds(33, 33, 324, 86);
		panelCentral.add(txtConsola);
		
		txtRopa = new JTextField();
		txtRopa.setText("Tu ropa favorita...");
		txtRopa.setBorder(null);
		txtRopa.setHorizontalAlignment(SwingConstants.CENTER);
		txtRopa.setForeground(Color.BLACK);
		txtRopa.setFont(new Font("Niagara Solid", Font.PLAIN, 55));
		txtRopa.setEditable(false);
		txtRopa.setColumns(10);
		txtRopa.setBackground(new Color(0, 206, 209));
		txtRopa.setBounds(520, 145, 300, 86);
		panelCentral.add(txtRopa);
		
		txthastaTusLibros = new JTextField();
		txthastaTusLibros.setText("¡Hasta tus libros preferidos!");
		txthastaTusLibros.setBorder(null);
		txthastaTusLibros.setHorizontalAlignment(SwingConstants.CENTER);
		txthastaTusLibros.setForeground(Color.BLACK);
		txthastaTusLibros.setFont(new Font("Niagara Solid", Font.PLAIN, 55));
		txthastaTusLibros.setEditable(false);
		txthastaTusLibros.setColumns(10);
		txthastaTusLibros.setBackground(new Color(0, 206, 209));
		txthastaTusLibros.setBounds(64, 437, 392, 86);
		panelCentral.add(txthastaTusLibros);
		
		txtCompraYa = new JTextField();
		txtCompraYa.setEditable(false);
		txtCompraYa.setFont(new Font("Niagara Solid", Font.PLAIN, 45));
		txtCompraYa.setHorizontalAlignment(SwingConstants.CENTER);
		txtCompraYa.setText("¡Compra ya!");
		txtCompraYa.setForeground(new Color(255, 255, 255));
		txtCompraYa.setBackground(new Color(176, 224, 230));
		txtCompraYa.setBounds(580, 640, 280, 75);
		panelCentral.add(txtCompraYa);
		txtCompraYa.setColumns(10);
		
		//MouseListener para el JLablel lblNosotros
		lblNosotros.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				lblNosotros.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent evt) {
				lblNosotros.setBackground(Color.WHITE);
			}
			
			public void mousePressed(MouseEvent evt) {
				lblNosotros.setText("Alumnos de Programación III de la Universidad de Deusto");
				lblNosotros.setBounds(605, 870, 400, 35);
			}
			
			public void mouseReleased(MouseEvent evt) {
				lblNosotros.setText("Acerca de nosotros");
				lblNosotros.setBounds(835, 870, 114, 35);
			}
		});
		
		// Hilo para el JLablel lblNosotros
		Runnable r1 = new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					txtCompraYa.setForeground(Color.BLACK);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					txtCompraYa.setForeground(Color.YELLOW);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}

			}
		};
		Thread t1 = new Thread(r1);
		t1.start();
		
		return frame;
	}
}
