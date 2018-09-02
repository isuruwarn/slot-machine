package org.warn.slotmachine;

import java.util.Scanner;

import org.warn.slotmachine.GameModel;


public class GameController {

	public static final int COLUMNS = 5;
	public static final int ROWS = 6;
	public static final int SQUARES_PER_COLUMN = 15;
	public static final int MINIMUM_BET = 30;
	
	
	public static void main(String[] args) {
		
		int availableCredit = 100;
		Scanner reader = new Scanner(System.in);
		GameModel gm = new GameModel();
		
		while( availableCredit >= MINIMUM_BET ) {
			
			System.out.println("CREDIT AVAILABLE: " + availableCredit);
			System.out.println("MINIMUM BET: " + MINIMUM_BET);
			System.out.println("PLEASE ENTER YOUR CURRENT BET:");
			int betValue = reader.nextInt();
			
			if ( betValue < MINIMUM_BET ) {
				System.out.println("PLEASE ENTER A VALUE LARGER THAN THE MINIMUM BET");
				continue;
			}
			
			if ( betValue > availableCredit ) {
				System.out.println("PLEASE ENTER A VALUE WITHIN YOUR AVAILABLE CREDIT");
				continue;
			}
			
			System.out.println("CURRENT BET: " + betValue );
			availableCredit -= betValue;
			int creditWon = gm.spin( COLUMNS, ROWS, SQUARES_PER_COLUMN );
			System.out.println("CREDIT WON: " + creditWon );
			availableCredit += creditWon;
		}
		reader.close();
		
		System.out.println("CREDIT AVAILABLE: " + availableCredit);
		System.out.println("GAME OVER...");
	}

}
