import java.util.Scanner; 

public class BattleShip {
	
	// This is a comment
	
	public static final int SHIP_SYMBOL = 'S';
	public static final int WATER_SYMBOL = 'O';
	public static final int SUNK_SHIP_SYMBOL = 'X';
	public static final int EMPTY_SYMBOL = '.';
	
	public static final int MAX_SHOTS = 30;
	public static final int NUM_SHIPS = 10;
	public static final int DIMENSION = 8;	
	static char[][] matrix = new char[DIMENSION][DIMENSION];
	static boolean gameOver;
	
	static int remainingShots;
	static int sunkShipCounter;
		
	public static void main(String[] args) {
		
		char letter;
		int number;
		Scanner input = new Scanner(System.in);
			     	     	
		gameOver = false;
		sunkShipCounter = 0;
		remainingShots = MAX_SHOTS;
	    initMatrix();
	    addShipsToMatrix();
	    	     
	    while(!gameOver) {
	    	printMatrix(true);
	    	System.out.println("Enter row (Letter):");
	    	letter = input.next().toUpperCase().charAt(0);
	    	System.out.println("Enter column (Number): ");
	    	number = input.nextInt();
	    	shoot(letter, number);
	    	checkGameOver();	    	
	    }
	    
	    showResult();
	     
	}

	private static void showResult() {		
		if (sunkShipCounter >= NUM_SHIPS) {
			System.out.println("You WIN !!!!");
		} else {
			System.out.println("No remaining shots :-(");
		}
		
	}

	private static void checkGameOver() {
		if (sunkShipCounter >= NUM_SHIPS || remainingShots <= 0) {
			gameOver = true;
		}
		
	}

	private static void shoot(char letter, int number) {
		
		int row = letter - 'A';
		int col = number - 1;
		
		remainingShots --;
		
		if (matrix[row][col] == SHIP_SYMBOL) {
			matrix[row][col] = SUNK_SHIP_SYMBOL;
			sunkShipCounter ++;
		} else {
			if (matrix[row][col] == EMPTY_SYMBOL) {
				matrix[row][col] = WATER_SYMBOL;
			}
		}
		
	}

	private static void addShipsToMatrix() {
		
		long shipCounter = 0;
		int randomRow, randomCol;
		
		while (shipCounter < NUM_SHIPS) {
			randomRow = (int) (Math.random() * DIMENSION);
			randomCol= (int) (Math.random() * DIMENSION);
	
			if (matrix[randomRow][randomCol] != SHIP_SYMBOL) {
				matrix[randomRow][randomCol] = SHIP_SYMBOL;
				shipCounter ++;
			}
		}
		
	}

	public static void initMatrix() {
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++) {
				matrix[row][col] = EMPTY_SYMBOL;				
			}
		}
	}
	
	public static void printMatrix(boolean debug) {
		printHeader();
		char c = 'A';
		for (int row = 0; row < matrix.length; row++) {
			System.out.print(c + " ");
			c++;
			for (int col = 0; col < matrix[0].length; col++) {
				if (matrix[row][col] == SHIP_SYMBOL) {
					if (debug) {
						System.out.print(matrix[row][col] + " ");
					} else {
						System.out.print(Character.toString(EMPTY_SYMBOL)
								+ " ");
					}
				} else {
					System.out.print(matrix[row][col] + " ");
				}			
			}
			System.out.println();
		}
	}

	private static void printHeader() {
		System.out.print("  ");
		for(int i = 1; i <= matrix[0].length; i++ ) {
			System.out.print(i + " ");
		}
		System.out.println();
		
	}

}
