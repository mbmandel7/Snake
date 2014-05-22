import java.awt.GridLayout;

import javax.swing.JFrame;

//adds width * height Squares to the window and starts the thread.
class GameBoard extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2373231214645354432L;
	private Square[][] grid;
	private int width = 20;
	private int height = 20;

	public GameBoard() {

		setTitle("Snake");
		setSize(300, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Creates the arraylist that'll contain the threads
		grid = new Square[width][height];

		// Setting up the layout of the panel
		setLayout(new GridLayout(width, height));

		// Creates Square and its data, and adds it to the arrayList and to the
		// panel
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				grid[i][j] = new Square(1);
				add(grid[i][j]);
			}
		}
	}

	public void beginThread(String speed) {
		// passing this value to the controller
		ThreadsController c = new ThreadsController(grid, speed);
		// // Let's start the game now..
		c.start();

		// Links the window to the keyboardlistenner.
		this.addKeyListener(new KeyboardListener(c));
	}

	public Square[][] getGrid() {
		// TODO Auto-generated method stub
		return grid;
	}
}