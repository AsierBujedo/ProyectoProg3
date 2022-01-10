package Utils;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JTextField;
import javax.swing.border.Border;
/**Clase RoundedBorder.
 * {@link Border} redondeado impementable en {@link JButton}, {@link JTextField}, {@link JMenu}
 * @author GR08
 */
public class RoundedBorder implements Border {
    private int radius;

    public RoundedBorder(int radius) {
        this.radius = radius;
    }

    public boolean isBorderOpaque() {
        return true;
    }

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		g.drawRoundRect(x, y, width-1, height-1, radius, radius);
	}

	@Override
	public Insets getBorderInsets(Component c) {
		return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
	}
}