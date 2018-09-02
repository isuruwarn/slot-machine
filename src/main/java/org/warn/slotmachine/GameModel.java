package org.warn.slotmachine;

import java.util.ArrayList;
import java.util.Random;

public class GameModel {
	
	public static final int DELAY_DISPLAY = 200; //ms
	public static final int REPEAT_RESULT = 5;
	
	
	public int spin( int columns, int rows, int squaresPerColumn ) {
		
		Random r = new Random();
		ArrayList<ArrayList<Integer>> gridValues = new ArrayList<ArrayList<Integer>>();
		
		for( int i = 0; i<columns; i++ ) {
			
			//int spinValue = (int) ( Math.random()*100000 % squaresPerColumn );
			int spinValue = r.nextInt(16);
			ArrayList<Integer> column = new ArrayList<Integer>();
			column.add(spinValue);
			
			for( int j=1; j<rows; j++ ) {
				int nextValue = ( spinValue + j ) % squaresPerColumn;
				column.add(nextValue);
			}
			gridValues.add(column);
		}
		
		// TODO: calculate result
		int winnings = 0;
		
		display( columns, rows, gridValues );
		
		return winnings;
	}
	
	
	
	public void display( int columns, int rows, ArrayList<ArrayList<Integer>> gridValues ) {
		
		int totalIterations = columns * REPEAT_RESULT;
		int columnsDisplayed = 0;
		int lowerBound = REPEAT_RESULT;
		int upperBound = lowerBound + REPEAT_RESULT;
		
		for( int i=0; i<=totalIterations; i++ ) {
			
			if(i<lowerBound) {
				displayColumns( gridValues, columnsDisplayed );
				
			} else if( i>=lowerBound && i<upperBound ) {
				columnsDisplayed++;
				displayColumns( gridValues, columnsDisplayed );
				lowerBound = upperBound;
				upperBound = upperBound + REPEAT_RESULT;
			}
			
			// delay for visual effect
			try {
				Thread.sleep(DELAY_DISPLAY);	
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	
	private void displayColumns( ArrayList<ArrayList<Integer>> columns, int columnsDisplayed ) {
		
		for(int rowIndex=0; rowIndex<columns.get(0).size(); rowIndex++) {
			
			String row = "";
			
			for(int i=0; i<columns.size(); i++) {
				
				if(i<columnsDisplayed) {
					ArrayList<Integer> column = columns.get(i);
					row += " | " + String.format( "%02d", column.get(rowIndex) );
				
				} else {
					row += " | .. ";
				}
			}
			System.out.println(row);
		}
		
		System.out.println("\n\n\n\n\n");
	}
	
}
