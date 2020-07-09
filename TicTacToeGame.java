package tic_tac_toe;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author Ankit Patel 
 * contact: apate158@stevens.edu
 * last updated: 7/7/20
 * 
 * This is a player vs cpu tic-tac-toe game. Enjoy!
 *
 */

public class TicTacToeGame {

	// The 3-by-3 tic-tac-toe board is represented by a 2d array

	static char[][] diagram = { { '1', '|', '2', '|', '3' }, 
			{ '-', '+', '-', '+', '-' }, 
			{ '4', '|', '5', '|', '6' },
			{ '-', '+', '-', '+', '-' }, 
			{ '7', '|', '8', '|', '9' } };

	static char[][] gameboard = { { ' ', '|', ' ', '|', ' ' }, 
			{ '-', '+', '-', '+', '-' }, 
			{ ' ', '|', ' ', '|', ' ' },
			{ '-', '+', '-', '+', '-' },
			{ ' ', '|', ' ', '|', ' ' } };

	// These ArrayLists are used to store the player's and cpu's moves (tile numbers)
	static ArrayList<Integer> playerMoves = new ArrayList<Integer>();
	static ArrayList<Integer> cpuMoves = new ArrayList<Integer>();

	/**
	 * This method runs all of the functions used in the game.
	 * 
	 */
	private static void game() {
		printBoard(gameboard);

		while (true) {
			turn("Player", 'X');
			printBoard(gameboard);

			if (gameOver("Player")) {
				System.out.println("player wins!");
				break;
			}

			// checks for ties. If all 9 tiles are occupied and there is no winner,
			// then there is a tie.
			if (playerMoves.size() + cpuMoves.size() == 9) {
				System.out.println("It's a tie!");
				break;
			}

			turn("CPU", 'O');

			printBoard(gameboard);

			if (gameOver("CPU")) {
				System.out.println("cpu wins!");
				break;
			}
		}
	}

	/**
	 * @param user Is either "Player" or "CPU".
	 * @param symbol Is 'X' if its the player's turn or is 'O' if its the CPU's turn.
	 * 				The turn method: 1)obtains user's move/tile. 
	 * 				and 2) places the user's symbol on the desired tile.
	 */
	private static void turn(String user, char symbol) {
		int tile;

		if (user.equals("Player")) {
			System.out.println("Which tile do you want to place your X? Enter an integer from [1,9].");
			Scanner sc = new Scanner(System.in);
			tile = sc.nextInt();
			while (playerMoves.contains(tile) || cpuMoves.contains(tile) || tile < 1 || tile > 9) {
				System.out.println("That tile is taken, pick an available tile.");
				tile = sc.nextInt();
			}
			// stores the player's (valid) move for future reference
			playerMoves.add(tile);
		} else {
			// the CPU's move is randomly generated
			tile = (int) (9 * Math.random()) + 1;

			while (playerMoves.contains(tile) || cpuMoves.contains(tile)) {
				tile = (int) (9 * Math.random()) + 1;
			}

			// stores the cpu's (valid) move for future reference
			cpuMoves.add(tile);
		}

		// switch-cases help place the symbol on the correct tile
		switch (tile) {
		case 1:
			gameboard[0][0] = symbol;
			break;
		case 2:
			gameboard[0][2] = symbol;
			break;
		case 3:
			gameboard[0][4] = symbol;
			break;
		case 4:
			gameboard[2][0] = symbol;
			break;
		case 5:
			gameboard[2][2] = symbol;
			break;
		case 6:
			gameboard[2][4] = symbol;
			break;
		case 7:
			gameboard[4][0] = symbol;
			break;
		case 8:
			gameboard[4][2] = symbol;
			break;
		case 9:
			gameboard[4][4] = symbol;
			break;
		default:
			break;
		}
	}

	/**
	 * @param user is either "Player" or "CPU".
	 * @return True if the game is over. The game is over if the gameboard has 3 X's
	 *         or 3 O's in a row.
	 */
	private static boolean gameOver(String user) {

		// all 8 possible ways to win. Each is stored in its own list.
		List toprow = Arrays.asList(1, 2, 3);
		List midrow = Arrays.asList(4, 5, 6);
		List botrow = Arrays.asList(7, 8, 9);

		List leftcol = Arrays.asList(1, 4, 7);
		List midcol = Arrays.asList(2, 5, 8);
		List rightcol = Arrays.asList(3, 6, 9);

		List diagonal1 = Arrays.asList(3, 5, 7);
		List diagonal2 = Arrays.asList(1, 5, 9);

		// all 8 combinations are stored as a superlist (a list of lists)
		List<List> all_combs = new ArrayList<List>();
		all_combs.add(toprow);
		all_combs.add(midrow);
		all_combs.add(botrow);
		all_combs.add(leftcol);
		all_combs.add(midcol);
		all_combs.add(rightcol);
		all_combs.add(diagonal1);
		all_combs.add(diagonal2);

		/*
		 * Checks if the player or cpu won the game.
		 * 
		 * More specifically, the enhanced for-loop checks if either the player or cpu's
		 * move list contain all of the tiles from at least a single possible way to
		 * win. (ie: it checks if *any* of the eight ways to win is a subset of either
		 * the player's or cpu's move list.
		 */
		if (user.equals("Player")) {
			for (List lis : all_combs) {
				if (playerMoves.containsAll(lis)) {
					return true;
				}
			}
		} else {
			for (List lis : all_combs) {
				if (cpuMoves.containsAll(lis)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @param board is a 2-D array of characters. In this program, the board will
	 *              either be an instructional 'diagram' or the 'gameboard'
	 *              displaying the tic-tac-toe game.
	 */
	private static void printBoard(char[][] board) {
		for (char[] lis : board) {
			for (char c : lis) {
				System.out.print(c);
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * Prints out the instructions for how to use this program.
	 */
	private static void printInstructions() {
		System.out.println("Welcome to Tic-Tac-Toe! The following diagram denotes "
				+ "\nthe integers that correspond to each tile:");
		printBoard(diagram);

	}

	/**
	 * Format of the tic-tac-toe program
	 */
	public static void main(String[] args) {
		printInstructions();
		game();
	}

}