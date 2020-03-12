package ticTacToe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;


/**	
 * 
 * @author pratapnea
 * Dated 11th March 2020
 * 
 */


public class TicTacToe {

	public static String user1;
	public static String user2;
	
	public static Scanner scan = new Scanner(System.in);
	
	public static ArrayList<Integer> user1Position = new ArrayList<Integer>();
	public static ArrayList<Integer> user2Position = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		
		// 2-D array to format the gameBoard
		char[][] gameBoard = {
				{' ', '|', ' ', '|', ' '},
				{'-', '+', '-', '+', '-'},
				{' ', '|', ' ', '|', ' '},
				{'-', '+', '-', '+', '-'},
				{' ', '|', ' ', '|', ' '}
		};
		
		System.out.print("Enter first user's name: ");
		user1 = scan.nextLine();
		System.out.print("Enter second user's name: ");
		user2 = scan.nextLine();
		
		displayBoard(gameBoard);
		
		System.out.println("\nUser first will get first chance to move: " + user1);
		
		boolean flag = true;
		int position;
		
		while(true) {
			if(flag) {
				System.out.print("\n" + user1 + " enter your wished position (1-9): ");				
				
				position = scan.nextInt();
				System.out.println();
				
				if(position <= 0 || position > 9) {
					System.out.println("Invalid Move");
					continue;
				}
				
				boolean isVal = insertCheck(gameBoard, position, user1);
				if(!isVal) {
					System.out.println("Position has already been used by " + user2 + "\n please enter next position");
					continue;
				}
				
				flag = false;
			}
			else {
				System.out.print("\n" + user2 + " enter your wished position (1-9): ");
				
				position = scan.nextInt();
				System.out.println();
				
				boolean isVal = insertCheck(gameBoard, position, user2);
				if(!isVal) {
					System.out.println("Position has already been used by " + user1 + "\n please enter next position");
					continue;
				}
				flag = true;
			}
			
			displayBoard(gameBoard);
			
			String winner = checkWinner();
			if(!winner.isEmpty()) {
				System.out.println(winner);
				break;
			}
		}
		
		scan.close();
		
	}
	
	// method to display gameBoard
	public static void displayBoard(char[][] gameBoard) {
		for(char[] row : gameBoard) {
			for(char col : row) {
				System.out.print(col);
			}
			System.out.println();
		}		
	}
	
	//method to insert the cross in the desired position
	public static boolean insertCheck(char[][] gameBoard, int pos, String user) {
		
		char symbol = ' ';	
		
		if(user.equals(user1)) symbol = 'X';
		else symbol = 'O';
		
		switch(pos) {
			case 1:
				if(gameBoard[0][0] != ' ') return false;
				gameBoard[0][0] = symbol;
				break;
			case 2:
				if(gameBoard[0][2] != ' ') return false;
				gameBoard[0][2] = symbol;
				break;
			case 3:
				if(gameBoard[0][4] != ' ') return false;
				gameBoard[0][4] = symbol;
				break;
			case 4:
				if(gameBoard[2][0] != ' ') return false;
				gameBoard[2][0] = symbol;
				break;
			case 5:
				if(gameBoard[2][2] != ' ') return false;
				gameBoard[2][2] = symbol;
				break;
			case 6:
				if(gameBoard[2][4] != ' ') return false;
				gameBoard[2][4] = symbol;
				break;
			case 7:
				if(gameBoard[4][0] != ' ') return false;
				gameBoard[4][0] = symbol;
				break;
			case 8:
				if(gameBoard[4][2] != ' ') return false;
				gameBoard[4][2] = symbol;
				break;
			case 9:
				if(gameBoard[4][4] != ' ') return false;
				gameBoard[4][4] = symbol;
				break;
			default:
				break;
		}
		
		if(user.equals(user1)) user1Position.add(pos);
		else user2Position.add(pos);
		
		return true;
		
	}
	
	
	// method too check winner of the game
	public static String checkWinner() {
		
		List topRow 	= Arrays.asList(1, 2, 3);
		List middleRow	= Arrays.asList(4, 5, 6);
		List buttomRow	= Arrays.asList(7, 8, 9);
		List leftCol	= Arrays.asList(1, 4, 7);
		List middleCol	= Arrays.asList(2, 5, 8);
		List rigtCol	= Arrays.asList(3, 6, 9);
		List digonal1	= Arrays.asList(1, 5, 9);
		List digonal2	= Arrays.asList(3, 5, 7);
		
		List<List> winningCondition =  new ArrayList<List>();
		winningCondition.add(topRow);
		winningCondition.add(middleRow);
		winningCondition.add(buttomRow);
		winningCondition.add(leftCol);
		winningCondition.add(middleCol);
		winningCondition.add(rigtCol);
		winningCondition.add(digonal1);
		winningCondition.add(digonal2);
		
		for(List l : winningCondition) {
			if(user1Position.containsAll(l)) {
				return "\nCongratulations " + user1 + ", you won!";
			}else if(user2Position.containsAll(l)) {
				return "\nCongratulations " + user2 + ", you won!";
			}else if(user1Position.size() + user2Position.size() == 9) {
				return "\nMatch Draw!";
			}
		}
		return "";
	}
	

}















