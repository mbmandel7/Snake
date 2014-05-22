import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardListener extends KeyAdapter {

	private ThreadsController c;

	public KeyboardListener(ThreadsController c) {
		this.c = c;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			// if it's not the opposite direction
			if (c.getDirectionSnake() != 2)
				c.setDirectionSnake(1);
			break;
		case KeyEvent.VK_UP:
			if (c.getDirectionSnake() != 4)
				c.setDirectionSnake(3);
			break;
		case KeyEvent.VK_LEFT:
			if (c.getDirectionSnake() != 1)
				c.setDirectionSnake(2);
			break;

		case KeyEvent.VK_DOWN:
			if (c.getDirectionSnake() != 3)
				c.setDirectionSnake(4);
			break;
		default:
			break;
		}
	}
}
