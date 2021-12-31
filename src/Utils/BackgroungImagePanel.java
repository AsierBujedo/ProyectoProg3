package Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackgroungImagePanel extends JPanel {

	private static final long serialVersionUID = -678038763364969095L;
	private Image img;

	public BackgroungImagePanel() {
		JButton boton = new JButton("stats");
		JPanel panel = new JPanel();
		panel.add(boton, BorderLayout.SOUTH);
//		JTextArea tA = new JTextArea();
		this.add(panel);
		
		boton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				OtherFrames.VentanaStats();
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
