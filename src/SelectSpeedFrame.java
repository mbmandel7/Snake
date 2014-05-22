import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SelectSpeedFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3340302795140038002L;
	private String speed;
	private JComboBox combo;

	public SelectSpeedFrame() {

		setTitle("Select Speed");
		setSize(220, 100);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();

		panel.add(new JLabel("Select Speed"));

		String[] speeds = { "SLOW", "MEDIUM", "FAST" };
		combo = new JComboBox(speeds);
		panel.add(combo);

		JButton play = new JButton("Play");
		play.addActionListener(new PlayListener());
		panel.add(play);

		add(panel);
	}

	private class PlayListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			speed = (String) combo.getSelectedItem();
			GameBoard game = new GameBoard();
			game.setVisible(true);
			game.beginThread(speed);
			setVisible(false);
		}
	}
}