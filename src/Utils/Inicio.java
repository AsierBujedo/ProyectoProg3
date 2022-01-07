package Utils;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Inicio extends JPanel {
	private static final long serialVersionUID = -678038763364969095L;
	private static JTextField txtConsola;
	private static JTextField txtRopa;
	private static JTextField txthastaTusLibros;
	private static JTextField txtCompraYa;

	public static JPanel InitWindow() {
		JPanel frame = new JPanel();
		JPanel principal = new JPanel();
		frame.add(principal);
		principal.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
		panelNorte.setBackground(new Color(0, 206, 209));
		principal.add(panelNorte, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("TU TIENDA DESDE CASA");
		lblNewLabel.setFont(new Font("Niagara Engraved", Font.PLAIN, 80));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panelNorte.add(lblNewLabel);
		
		JPanel panelSur = new JPanel();
		panelSur.setBackground(new Color(0, 206, 209));
		principal.add(panelSur, BorderLayout.SOUTH);
		
		JLabel lblNewLabel_1 = new JLabel("Desarrollado por: Iker L\u00F3pez, Asier Bujedo, Eduardo Larrinaga y Mikel Lambarri");
		lblNewLabel_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelSur.add(lblNewLabel_1);
		
		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(new Color(224, 255, 255));
		panelCentral.setPreferredSize(new Dimension(985, 900));
		panelCentral.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(panelCentral);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		principal.add(scrollPane, BorderLayout.CENTER);
		
		JLabel lblPS = new JLabel(new ImageIcon("ps5.jpg"));
		lblPS.setBounds(86, 111, 381, 246);
		ImageIcon im = new ImageIcon("ps5.jpg");
		ImageIcon imDimensiones = new ImageIcon(im.getImage().getScaledInstance(lblPS.getWidth(), lblPS.getHeight(), Image.SCALE_DEFAULT));
		lblPS.setIcon(imDimensiones);
		panelCentral.add(lblPS);
		
		JLabel lblNike = new JLabel(new ImageIcon("airforce1.jfif"));
		lblNike.setBounds(567, 201, 381, 294);
		ImageIcon im2 = new ImageIcon("airforce1.jfif");
		ImageIcon imDimensiones2 = new ImageIcon(im2.getImage().getScaledInstance(lblNike.getWidth(), lblNike.getHeight(), Image.SCALE_DEFAULT));
		lblNike.setIcon(imDimensiones2);
		panelCentral.add(lblNike);
		
		JLabel lblHarry = new JLabel(new ImageIcon("harry.jpg"));
		lblHarry.setBounds(74, 520, 381, 360);
		ImageIcon im3 = new ImageIcon("harry.jpg");
		ImageIcon imDimensiones3 = new ImageIcon(im3.getImage().getScaledInstance(lblHarry.getWidth(), lblHarry.getHeight(), Image.SCALE_DEFAULT));
		lblHarry.setIcon(imDimensiones3);
		panelCentral.add(lblHarry);
		
		
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
		txtRopa.setBounds(541, 125, 300, 86);
		panelCentral.add(txtRopa);
		
		txthastaTusLibros = new JTextField();
		txthastaTusLibros.setText("\u00A1Hasta tus libros preferidos!");
		txthastaTusLibros.setBorder(null);
		txthastaTusLibros.setHorizontalAlignment(SwingConstants.CENTER);
		txthastaTusLibros.setForeground(Color.BLACK);
		txthastaTusLibros.setFont(new Font("Niagara Solid", Font.PLAIN, 55));
		txthastaTusLibros.setEditable(false);
		txthastaTusLibros.setColumns(10);
		txthastaTusLibros.setBackground(new Color(0, 206, 209));
		txthastaTusLibros.setBounds(64, 447, 392, 86);
		panelCentral.add(txthastaTusLibros);
		
		txtCompraYa = new JTextField();
		txtCompraYa.setEditable(false);
		txtCompraYa.setFont(new Font("Niagara Solid", Font.PLAIN, 45));
		txtCompraYa.setHorizontalAlignment(SwingConstants.CENTER);
		txtCompraYa.setText("\u00A1Compra ya!");
		txtCompraYa.setForeground(new Color(255, 255, 255));
		txtCompraYa.setBackground(new Color(176, 224, 230));
		txtCompraYa.setBounds(580, 640, 280, 75);
		panelCentral.add(txtCompraYa);
		txtCompraYa.setColumns(10);
		
		JLabel lblNosotros = new JLabel("Acerca de nosotros");
		lblNosotros.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNosotros.setBounds(843, 870, 114, 35);
		panelCentral.add(lblNosotros);
		
		lblNosotros.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				lblNosotros.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent evt) {
				lblNosotros.setBackground(Color.WHITE);
			}
			
			public void mousePressed(MouseEvent evt) {
				lblNosotros.setText("Alumnos de Programación III de la Universidad de Deusto");
				lblNosotros.setBounds(615, 870, 400, 35);
			}
			
			public void mouseReleased(MouseEvent evt) {
				lblNosotros.setText("Acerca de nosotros");
				lblNosotros.setBounds(843, 870, 114, 35);
			}
		});
		
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
