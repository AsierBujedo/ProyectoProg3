package Utils;

import javax.swing.*;
import java.awt.*;

public class BackgroungImagePanel extends JPanel {

	private static final long serialVersionUID = -678038763364969095L;
	private Image img;

	public BackgroungImagePanel() {
		JPanel panel = new JPanel();
		JTextArea tA = new JTextArea();
		this.add(panel);
	}

	@Override
	public void paint(Graphics g) {
		img = Toolkit.getDefaultToolkit().getImage("logo.png");
		g.drawImage(img, 0, 0, this);
		
		
		this.setOpaque(false);
		super.paint(g);
	}
}
