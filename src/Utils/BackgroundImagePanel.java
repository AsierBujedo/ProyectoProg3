package Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackgroundImagePanel extends JPanel {

	private static final long serialVersionUID = -678038763364969095L;
	private Image img;
	
	/**
	 * 
	 */
	public BackgroundImagePanel() {
		JButton boton = new JButton("stats");
		boton = OtherUtils.modifyButton(boton, new Color(67, 67, 67), new Color(194, 194, 194), new Color(225, 225, 225));
		//--- Añadir más modificaciones la botón ---
		JTextArea tA = new JTextArea();
		tA.setEditable(false);
		tA.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tA.setBackground(Color.white);
		tA.setText("Emai - Tu tienda desde casa\n\n"
				+ "\nCreada por:\n\n"
				+ "Iker López Arnaiz\n"
				+ "Asier Bujedo Álvarez\n"
				+ "Eduardo Larrinaga Saiz\n"
				+ "Mikel Lambarri Cano");
		//------
		this.add(boton, BorderLayout.SOUTH);
		this.add(tA, BorderLayout.CENTER);
		this.setBackground(Color.white);
		this.setOpaque(true);
		
		boton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread stats = new Thread(new Runnable() {
					
					@Override
					public void run() {
						OtherFrames.VentanaStats();	
					}
				});
				stats.start();
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		img = Toolkit.getDefaultToolkit().getImage("logo.png");
		g.drawImage(img, 0, 0, this);
		this.setOpaque(false);
		super.paint(g);
		
		
	}

}
