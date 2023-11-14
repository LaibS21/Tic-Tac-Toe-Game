import java.util.Random;
import java.util.Scanner;

/**
 * TicTacToe class represents a simple Tic Tac Toe game. The game is played on a
 * 3x3 grid, where players take turns placing their markers (O and X).
 */
public class TicTacToe {
	private char[] brett; // The game board, represented as an array of chars.

	/**
	 * Constructor initializes the game board with spaces. The board is a 1D array
	 * but represents a 3x3 grid.
	 */

	public TicTacToe() {
		// Initialize the game board with spaces
		brett = new char[9];
		for (int i = 0; i < 9; i++) {
			brett[i] = ' ';
		}
		System.out.println("Welcome to Tic Tac Toe ૮ ˶ᵔ ᵕ ᵔ˶ ა");
		System.out.println();
	}

	public void printBoard() {
		// Display the game board
		for (int i = 0; i < 9; i++) {
			System.out.print("[" + brett[i] + "]");
			if ((i + 1) % 3 == 0) {
				System.out.println(); // New line after every third element
			}
		}
	}

	public void placeO(int position) { // Place O, that's me
		if (position >= 0 && position < 9 && brett[position] == ' ') {
			brett[position] = 'O';
		} else {
			System.out.println("Invalid Input. Please select a free field.");
		}
	}

	public void placeRandomX() { // Place a random X
		Random random = new Random();
		int emptySpots = 0;
		// Count the number of empty spots on the board.

		for (int i = 0; i < 9; i++) {
			if (brett[i] == ' ') {
				emptySpots++;
			}
		}

		if (emptySpots > 0) {
			int randomIndex = random.nextInt(emptySpots) + 1;
			emptySpots = 0;

			for (int i = 0; i < 9; i++) {
				if (brett[i] == ' ') {
					emptySpots++;
					if (emptySpots == randomIndex) {
						brett[i] = 'X';
						return;
					}
				}
			}
		}
	}

	public boolean checkWinner(char player) {
		// Check for a winner in rows, columns, and diagonals
		for (int i = 0; i < 3; i++) {
			// Check rows
			if (brett[i * 3] == player && brett[i * 3 + 1] == player && brett[i * 3 + 2] == player) {
				return true;
			}

			// Check columns
			if (brett[i] == player && brett[i + 3] == player && brett[i + 6] == player) {
				return true;
			}
		}

		// Check diagonals
		if (brett[0] == player && brett[4] == player && brett[8] == player) {
			return true; // Diagonal (from top left to bottom right)
		}
		if (brett[2] == player && brett[4] == player && brett[6] == player) {
			return true; // Diagonal (from top right to bottom left)
		}

		return false;
	}

	public boolean isBoardFull() {
		// Check if the board is full
		for (int i = 0; i < 9; i++) {
			if (brett[i] == ' ') {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		Scanner scanner = new Scanner(System.in);

		while (true) {
			game.printBoard();
			System.out.println();
			System.out.println("Your move (positions 1-9): ");
			int position = scanner.nextInt();
			game.placeO(position - 1);

			if (game.checkWinner('O')) {
				game.printBoard();
				System.out.println("Congratulation, you won ᕙ(  •̀ ᗜ •́  )ᕗ!");
				break;
			}

			if (game.isBoardFull()) {
				game.printBoard();
				System.out.println("drawฅ^._.^ฅ!");
				break;
			}

			game.placeRandomX();

			if (game.checkWinner('X')) {
				game.printBoard();
				System.out.println("The computer won!(¬_¬\")");
				break;
			}

			if (game.isBoardFull()) {
				game.printBoard();
				System.out.println("Draw!");
				break;
			}
		}
	}
}
