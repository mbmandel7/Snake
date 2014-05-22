import java.util.ArrayList;

//Controls all the game logic .. most important class in this project.
public class ThreadsController extends Thread {

	private Square[][] squares;
	private Tuple headSnakePos;
	private int sizeSnake;
	private long speed;
	private int directionSnake;

	private ArrayList<Tuple> positions = new ArrayList<Tuple>();
	private Tuple foodPosition;

	// Constructor of ControllorThread
	public ThreadsController(Square[][] grid, String speed) {
		// Get all the threads
		this.squares = grid;

		headSnakePos = new Tuple(20 / 2, 20 / 2);
		sizeSnake = 3;
		if ("SLOW".equals(speed)) {
			this.speed = 200;
		} else {
			if ("MEDIUM".equals(speed)) {
				this.speed = 100;
			} else {
				this.speed = 30;
			}
		}
		directionSnake = 1;

		// !!! Pointer !!!!
		Tuple headPos = new Tuple(headSnakePos.getX(), headSnakePos.getY());
		positions.add(headPos);

		foodPosition = new Tuple(20 - 1, 20 - 1);
		spawnFood(foodPosition);
	}

	public int getDirectionSnake() {
		return directionSnake;
	}

	public void setDirectionSnake(int dir) {
		directionSnake = dir;
	}

	// Put food in a position and displays it
	private void spawnFood(Tuple foodPositionIn) {
		squares[foodPositionIn.getX()][foodPositionIn.getY()].lightMeUp(2);
	}

	// Important part :
	@Override
	public void run() {
		while (true) {
			moveInterne(directionSnake);
			checkCollision();
			moveExterne();
			deleteTail();
			pauser();
		}
	}

	// Moves the head of the snake and refreshes the positions in the arraylist
	// 1:right 2:left 3:top 4:bottom 0:nothing
	public void moveInterne(int dir) {
		switch (dir) {
		case 4:// bottom
			headSnakePos.changeData(headSnakePos.getX(),
					(headSnakePos.getY() + 1) % 20);
			positions.add(new Tuple(headSnakePos.getX(), headSnakePos.getY()));
			break;
		case 3:// top
			if (headSnakePos.getY() - 1 < 0) {
				headSnakePos.changeData(headSnakePos.getX(), 19);
			} else {
				headSnakePos.changeData(headSnakePos.getX(),
						Math.abs(headSnakePos.getY() - 1) % 20);
			}
			positions.add(new Tuple(headSnakePos.getX(), headSnakePos.getY()));
			break;
		case 2:// left
			if (headSnakePos.getX() - 1 < 0) {
				headSnakePos.changeData(19, headSnakePos.getY());
			} else {
				headSnakePos.changeData(Math.abs(headSnakePos.getX() - 1) % 20,
						headSnakePos.getY());
			}
			positions.add(new Tuple(headSnakePos.getX(), headSnakePos.getY()));

			break;
		case 1:// right
			headSnakePos.changeData(Math.abs(headSnakePos.getX() + 1) % 20,
					headSnakePos.getY());
			positions.add(new Tuple(headSnakePos.getX(), headSnakePos.getY()));
			break;
		}
	}

	// Checking if the snake bites itself or is eating
	public void checkCollision() {
		Tuple posCritique = positions.get(positions.size() - 1);
		for (int i = 0; i <= positions.size() - 2; i++) {
			boolean biteItself = posCritique.getX() == positions.get(i).getX()
					&& posCritique.getY() == positions.get(i).getY();
			if (biteItself) {
				stopTheGame();
			}
		}

		boolean eatingFood = posCritique.getX() == foodPosition.getY()
				&& posCritique.getY() == foodPosition.getX();
		if (eatingFood) {
			System.out.println("Yummy!");
			sizeSnake = sizeSnake + 1;
			foodPosition = getValAreaNotInSnake();

			spawnFood(foodPosition);
		}
	}

	// Refresh the squares that needs to be
	public void moveExterne() {
		for (Tuple t : positions) {
			int y = t.getX();
			int x = t.getY();
			squares[x][y].lightMeUp(0);

		}
	}

	// Refreshes the tail of the snake, by removing the superfluous data in
	// positions arraylist
	// and refreshing the display of the things that is removed
	public void deleteTail() {
		int cmpt = sizeSnake;
		for (int i = positions.size() - 1; i >= 0; i--) {
			if (cmpt == 0) {
				Tuple t = positions.get(i);
				squares[t.getY()][t.getX()].lightMeUp(1);
			} else {
				cmpt--;
			}
		}
		cmpt = sizeSnake;
		for (int i = positions.size() - 1; i >= 0; i--) {
			if (cmpt == 0) {
				positions.remove(i);
			} else {
				cmpt--;
			}
		}
	}

	// delay between each move of the snake
	public void pauser() {
		try {
			sleep(speed);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Stops The Game
	public void stopTheGame() {
		System.out.println("COLLISION! \n");
		while (true) {
			pauser();
		}
	}

	// return a position not occupied by the snake
	public Tuple getValAreaNotInSnake() {
		Tuple p;
		int ranX = 0 + (int) (Math.random() * 19);
		int ranY = 0 + (int) (Math.random() * 19);
		p = new Tuple(ranX, ranY);
		for (int i = 0; i <= positions.size() - 1; i++) {
			if (p.getY() == positions.get(i).getX()
					&& p.getX() == positions.get(i).getY()) {
				ranX = 0 + (int) (Math.random() * 19);
				ranY = 0 + (int) (Math.random() * 19);
				p = new Tuple(ranX, ranY);
				i = 0;
			}
		}
		return p;
	}
}