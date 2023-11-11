import java.util.Random;
import java.util.Scanner;

//Klasse machen und Figur Speichern überprüdeun ob beiden gewonnen hat Spielerklasse 
public class TicTacToe {
	private char[] brett; // Das Spielfeld

	public TicTacToe() {
		// Initialisiere das Spielfeld mit Leerzeichen
		brett = new char[9];
		for (int i = 0; i < 9; i++) {
			brett[i] = ' ';
		}
		System.out.println("Welcome to Tic Tac Toe ૮ ˶ᵔ ᵕ ᵔ˶ ა");
		System.out.println();
	}

	public void printBoard() {
		// Ausgabe des Spielfelds
		for (int i = 0; i < 9; i++) {
			System.out.print("[" + brett[i] + "]");
			if ((i + 1) % 3 == 0) {
				System.out.println(); // Neue Zeile nach jedem dritten Element
			}
		}
	}

	public void placeO(int position) { // Place O bzw das bin ich
		if (position >= 0 && position < 9 && brett[position] == ' ') {
			brett[position] = 'O';
		} else {
			System.out.println("Invalid Input. Please select a free field.");
		}
	}

	public void placeRandomX() { // Random X
		Random random = new Random();
		int emptySpots = 0;

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
		// Überprüfung auf Gewinner in Zeilen, Spalten und Diagonalen
		for (int i = 0; i < 3; i++) {
			// Überprüfung auf Zeilen
			if (brett[i * 3] == player && brett[i * 3 + 1] == player && brett[i * 3 + 2] == player) {
				return true;
			}

			// Überprüfung auf Spalten
			if (brett[i] == player && brett[i + 3] == player && brett[i + 6] == player) {
				return true;
			}
		}

		// Überprüfung auf Diagonalen
		if (brett[0] == player && brett[4] == player && brett[8] == player) {
			return true; // Diagonale (von links oben nach rechts unten)
		}
		if (brett[2] == player && brett[4] == player && brett[6] == player) {
			return true; // Diagonale (von rechts oben nach links unten)
		}

		return false;
	}

	public boolean isBoardFull() {
		// Überprüfung, ob das Spielfeld voll ist
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
			System.out.println("Ihr Zug (Position 1-9): ");
			int position = scanner.nextInt();
			game.placeO(position - 1);

			if (game.checkWinner('O')) {
				game.printBoard();
				System.out.println("Congratulation, you won ᕙ(  •̀ ᗜ •́  )ᕗ!");
				break;
			}

			if (game.isBoardFull()) {
				game.printBoard();
				System.out.println("Unentschiedenฅ^._.^ฅ!");
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
