import java.awt.Color;

import javax.swing.JPanel;

public class Square extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5907503468092925582L;

	private Color[] colors = { Color.ORANGE, Color.DARK_GRAY, Color.CYAN };

	public Square(int color) {
		setBackground(colors[color]);
		repaint();
	}

	public void lightMeUp(int color) {
		setBackground(colors[color]);
		repaint();
	}
}
