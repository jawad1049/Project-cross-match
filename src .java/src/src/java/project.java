package project cross match src.java;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	//Display game board with dashes.2D board console output.

	private char gameBoard[] = {' ',' ',' ',' ',' ',' ',' ',' ',' '};
	private final int BOARD_SIZE = 9;// Board is 9 spaces. 
	
	public static final char Jawad = 'X';
	public static final char Your = 'O';
	
	private Random mRand; // Random
	
	public TicTacToe() {
		
		// import java.util.Random
		mRand = new Random(); 
		
		// Jawad's turn first.		
		char turn = Jawad;    
		int  win = 0;               
		
		// Tic-Tac-Toe game loops until human or computer wins.
		while (win == 0)
		{	
			displayBoard();

			if (turn == Jawad)
			{
				getUserMove();
				turn = Your;
			}
			else
			{
				getComputerMove();
				turn = Jawad;
			}	

			win = checkForWinner();
		}

		displayBoard();

		// Report the Tic-Tac-Toe winner
		System.out.println();
		if (win == 1)
			System.out.println("It's a tie game.\nend program.\nStart over, Play to win!! ");
		
		else if (win == 2)
			System.out.println(Jawad + " wins!..As you should");
		
		else if (win == 3)
			System.out.println(Your + " wins!..Jawad you failed");
		
		else
			System.out.println("Selection doesn't make sense!");
	}
	
	private void displayBoard()	{ // Game Board. Array == 0-8. Which is 9 spots.
		System.out.println();
		System.out.println(gameBoard[0] + " | " + gameBoard[1] + " | " + gameBoard[2]);
		System.out.println("-----------");
		System.out.println(gameBoard[3] + " | " + gameBoard[4] + " | " + gameBoard[5]);
		System.out.println("-----------");
		System.out.println(gameBoard[6] + " | " + gameBoard[7] + " | " + gameBoard[8]);
		System.out.println();
	}
	
	// Check for a winner.  
	// Return 0 to continue game.
	// Return 1 if it's a tie game.
	// Return 2 if X (Jawad) wins.
	// Return 3 if O (Your) wins.
	private int checkForWinner() {
		
		// Check for a horizontal win.
		for (int i = 0; i <= 6; i += 3)	{
			if (gameBoard[i] == Jawad && 
				gameBoard[i+1] == Jawad &&
				gameBoard[i+2]== Jawad)
				return 2;// Jawad (x) wins.
			if (gameBoard[i] == Your && 
				gameBoard[i+1]== Your && 
				gameBoard[i+2] == Your)
				return 3;// Your (y) wins.
		}
	
		// Check for a vertical win.
		for (int i = 0; i <= 2; i++) {
			if (gameBoard[i] == Jawad && 
				gameBoard[i+3] == Jawad && 
				gameBoard[i+6]== Jawad)
				return 2; // Jawad (x) wins.
			if (gameBoard[i] == Your && 
				gameBoard[i+3] == Your && 
				gameBoard[i+6]== Your)
				return 3; // Your (y) wins.
		}
	
			// Check for a diagonal win.
		if ((gameBoard[0] == Jawad &&
			 gameBoard[4] == Jawad && 
			 gameBoard[8] == Jawad) ||
			(gameBoard[2] == Jawad && 
			 gameBoard[4] == Jawad &&
			 gameBoard[6] == Jawad))
			return 2;  // Jawad (x) wins.
		if ((gameBoard[0] == Your &&
			 gameBoard[4] == Your && 
			 gameBoard[8] == Your) ||
			(gameBoard[2] == Your && 
			 gameBoard[4] == Your &&
			 gameBoard[6] == Your))
			return 3; // Your (y) wins.
	
				// Check for a tie game.
		for (int i = 0; i < BOARD_SIZE; i++) {
				// If no win/tie/loss, Continue game. Human VS. Computer.
				// Return == 0 , Continue Game. 
			if (gameBoard[i] != Jawad && gameBoard[i] != Your)
				return 0; //Continue game play.
		}
	
		
		return 1;
		// Return 1 == tie.
	}
	
	void getUserMove() 
	{
		
		Scanner s = new Scanner(System.in);// allows Jawad (Human) to move. 
		
		int move = -1;
		
		while (move == -1) {			
			try {
				System.out.print("Jawad's turn: ");
			    move = s.nextInt();
			
				while (move < 1 || move > BOARD_SIZE || 
					   gameBoard[move-1] == Jawad || gameBoard[move-1] == Your) {
					
					if (move < 1 || move > BOARD_SIZE)
						System.out.println("Please select between 1 and " + BOARD_SIZE + ".");
					else
						System.out.println("That space is occupied.  Please choose another space.");
		
					System.out.print("Jawad it's your move: ");
				    move = s.nextInt();
				}
			} 
			catch (InputMismatchException ex) {
				System.out.println("Please enter a number between 1 and " + BOARD_SIZE + ".");
				s.next();  // Get next line so we can start new game. 
				move = -1;
			}
		}

		gameBoard[move-1] = Jawad;
	}
	
	private void getComputerMove() 
	{
		int move;

		// First see if there's a move O (computer) can make to win
		for (int i = 0; i < BOARD_SIZE; i++) {
			if (gameBoard[i] != Jawad && gameBoard[i] != Your) {
				char letter = gameBoard[i];
				gameBoard[i] = Your;
				if (checkForWinner() == 3) {
					System.out.println("The Computer selected " + (i + 1));
					return;
				}
				else
					gameBoard[i] = letter;
			}
		}
		//Computers turn to check to block Jawad's "X" move..
		for (int i = 0; i < BOARD_SIZE; i++) {
			if (gameBoard[i] != Jawad && gameBoard[i] != Your) {
				char letter = gameBoard[i];   // Store selected letter (o/x)
				gameBoard[i] = Jawad;
				if (checkForWinner() == 2) {
					gameBoard[i] = Your;
					System.out.println(" The Computer selected " + (i + 1));
					return;
				}
				else
					gameBoard[i] = letter;
			}
		}

		// Perform random selection from computer's move.
		// import java.util.Random;
		// import java.util.Scanner;
		do
		{
			move = mRand.nextInt(BOARD_SIZE);
		} while (gameBoard[move] == Jawad || gameBoard[move] == Your);
			
		System.out.println("The Computer selected " + (move + 1));

		gameBoard[move] = Your;
	}	
	
	
	// Main Method.
	public static void main(String[] args) {		
		new TicTacToe();		
	}
}