import java.util.Scanner;

public class Game {

	// - - - - - - - - - - - - - - - - - - - - - - - Set Game - - - - - - - - - - - - - - - - - - - - - - - - - - 


	public static void gameSet(char[][] gameBoard, char gamePlayer, char compPlayer) 
	{
		printBoard(gameBoard);
		int steps = 0;
		while(steps < 9 || checkBoardForWin(gameBoard) == 0) 
		{
			
			boolean result = true;
			do
			{
				result = userInput(gameBoard, gamePlayer);
			}
			while(result == false);
			steps++;
			printBoard(gameBoard);
			if(checkBoardForWin(gameBoard) == 1 || checkBoardForWin(gameBoard) == 2) 
			{
				break;
			}else if(steps == 9 && checkBoardForWin(gameBoard) == 0) 
			{
				break;
			}
			compPlayerTurn(steps, gameBoard, gamePlayer, compPlayer);
			steps++;
			printBoard(gameBoard);
			if(checkBoardForWin(gameBoard) == 1 || checkBoardForWin(gameBoard) == 2) 
			{
				break;
			}else if(steps == 9 && checkBoardForWin(gameBoard) == 0) 
			{
				break;
			}
		}

		if(checkBoardForWin(gameBoard) == 1) {
			System.out.println("\nCongrats! X Won The Game!");
		}else if(checkBoardForWin(gameBoard) == 2) {
			System.out.println("\nToo Bad! O Won The Game! ");
		}else {
			System.out.println("\nIt's A Tie! Let's Play Again!");
		}
		System.exit(0);
	}
	
	
	

	// - - - - - - - - - - - - - - - - - initialize game, board and players - - - - - - - - - - - - - - - -

	public static void main(String[] args) 
	{
		System.out.println("\n - - - - - - - - - - Welcome To Tic-Tac-Toe Game! - - - - - - - - - - ");
		char gamePlayer = 'X'; 
		char compPlayer = 'O';
		char gameBoard [][] = new char[4][4];
		initializeBoard(gameBoard);
		gameSet(gameBoard, gamePlayer, compPlayer);

	}


	public static void initializeBoard(char[][] gameBoard) 
	{
		gameBoard[0][0] = ' ';
		for (int i = 1; i < gameBoard.length; i++)
		{
			for (int j = 1; j < gameBoard.length; j++) 
			{
				gameBoard[i][0] = (char)(i + '0');
				gameBoard[0][j] = (char)(j + '0');
				gameBoard[i][j] = '-';
			}
		}
	}
		


	// - - - - - - - - - - - - - - - - - - - - - - - User Enter Input - - - - - - - - - - - - - - - - - - - - - - - - - - 

	public static boolean userInput(char[][] gameBoard, char gamePlayer) 
	{
		Scanner scn = new Scanner(System.in);
		System.out.println("Your Move! \nChoose a Row (1-3):");
		String row = scn.next();
		if(isAcc(row) == false) {
			System.out.println("\nWrong Input! Please Try Again!\n");
			return false;
		}
		
		System.out.println("Choose a Column (1-3): ");
		String col = scn.next();
		if(isAcc(col) == false) 
		{
			System.out.println("\nWrong Input! Please Try Again!");
			return false;
		}
		
		int x = Integer.parseInt(row);
		int y = Integer.parseInt(col);
		
		if(gameBoard[x][y] != '-')
		{
			System.out.println("Spot Accuupied! Please Try Again!");
			return false;
		}
		
		gameBoard[x][y] = gamePlayer;
		return true;
	}


	public static boolean isAcc(String input) {
		switch (input)
		{
		case "1":
			return true;
			
		case "2":
			return true;
			
		case "3":
			return true;
		}
		return false;
	
	}


	// - - - - - - - - - - - - - - - - - - - - - - - Check Board - - - - - - - - - - - - - - - - - - - - - - - - - - 


	public static void compPlayerTurn(int steps, char[][] gameBoard, char gamePlayer, char compPlayer)
	{
		if (steps == 1) {
			if(gameBoard[2][2] == '-') 
			{
				gameBoard[2][2] = compPlayer;
			}else 
			{
				gameBoard[1][1] = compPlayer;
			}
		}else {
			if(checkBoard(gameBoard, gamePlayer, compPlayer) == false)
			{
				if(findEmptySpotVer(gameBoard) == false) 
				{
					findEmptySpotHor(gameBoard);
				}

			}
		}
	}



	public static boolean checkBoard(char[][] gameBoard, char gamePlayer, char compPlayer) {

		//vertical
		if(isVertical(gameBoard)) 
		{
			return true;
		}


		//horizontal
		else if (isHorizontal(gameBoard)) 
		{
			return true;
		}


		//slant				
		else if(isSlant(gameBoard, gamePlayer) == false) 
		{ 
			if(isSlant(gameBoard, compPlayer)) 
			{
				return true;
			}
		}

		return false;
	}


	// - - - - - - - - - - - - - - - - - - - - - - - SLANT - - - - - - - - - - - - - - - - - - - - - - - - - - 

	 // check for two x in slant
	public static boolean isSlant(char [][] gameBoard, char player) 

	{
		if(gameBoard[1][1]== player && gameBoard[2][2] == player) 
		{
			if(gameBoard[3][3] == '-') 
			{
				gameBoard[3][3] = 'O';
				return true;
			}
		}
		else if(gameBoard[2][2] == player && gameBoard[3][3] == player) 
		{
			if(gameBoard[1][1] == '-') 
			{
				gameBoard[1][1] = 'O';
				return true;
			}
		}
		else if(gameBoard[1][1]== player && gameBoard[3][3] == player) 
		{
			if(gameBoard[2][2] == '-') 
			{
				gameBoard[2][2] = 'O';
				return true;
			}
		}
		else if(gameBoard[1][3]== player && gameBoard[2][2] == player) 
		{
			if(gameBoard[3][1] == '-') 
			{
				gameBoard[3][1] = 'O';
				return true;
			}
		}
		else if(gameBoard[3][1]== player && gameBoard[2][2] == player) 
		{
			if(gameBoard[1][3] == '-') 
			{
				gameBoard[1][3] = 'O';
				return true;
			}
		}
		else if (gameBoard[1][3]== player && gameBoard[3][1]== player)
		{
			if(gameBoard[2][2] == '-') 
			{
				gameBoard[2][2] = 'O';
				return true;
			}
		}
		return false;
	}

	// - - - - - - - - - - - - - - - - - - - - - - - VERTICAL - - - - - - - - - - - - - - - - - - - - - - - - - - 


	// check for two x or o in a row and put O in the empty spot 
	public static boolean isVertical( char[][] gameBoard) 
	{
		int countX = 0, countO = 0;

		int row = 0;
		int col = 0;
		for (row = 1; row < gameBoard.length; row++) 
		{
			for (col = 1; col < gameBoard.length; col++) 
			{
				if(gameBoard[row][col] == 'X') 
				{
					countX++;

				}else if(gameBoard[row][col] == 'O') 
				{
					countO++;
				}
			}

			if (countO == 2 || countX == 2) { //find the empty spot in the row
				for (int k = 1; k < gameBoard.length; k++) 
				{
					if(gameBoard[row][k] == '-') 
					{
						gameBoard[row][k] = 'O';
						return true;

					}
				}
			}
			countO = 0;
			countX = 0;
		}
		return false;
	}

	// - - - - - - - - - - - - - - - - - - - - - - - HORIZONTAL - - - - - - - - - - - - - - - - - - - - - - - - - - 

	// check for two x or o in a col and put O in the empty spot 
	public static boolean isHorizontal(char[][] gameBoard) 
	{
		int countX = 0, countO = 0;
		int row = 0 , col = 0;
		for (col = 1; col < gameBoard.length; col++) 
		{
			for (row = 1; row < gameBoard.length; row++)
			{
				if(gameBoard[row][col] == 'X') 
				{
					countX++;
				}else if(gameBoard[row][col] == 'O') 
				{
					countO++;
				}
			}

			if (countO == 2 || countX == 2) 
			{
				for (int k = 0; k < gameBoard.length; k++) 
				{
					if(gameBoard[k][col] == '-') 
					{
						gameBoard[k][col] = 'O';
						return true;

					}
				}
			}
			countO = 0;
			countX = 0;
		}
		return false;
	}

	// - - - - - - - - - - - - - - - - - - - - - - - find one 'O' - - - - - - - - - - - - - - - - - - - - - - - - - - 

	//find empty spot on the board
	public static boolean findEmptySpotHor(char[][] gameBoard) 
	{
		int countO = 0;

		int row = 0, col = 0;
		for (col = 1; col < gameBoard.length; col++) 
		{
			for (row = 1; row < gameBoard.length; row++) 
			{
				if(gameBoard[row][col] == 'O') 
				{
					countO++;
				}
			}

			if (countO == 1) 
			{
				for (int k = 0; k < gameBoard.length; k++)
				{
					if(gameBoard[k][col] == '-') 
					{
						gameBoard[k][col] = 'O';
						return true;

					}
				}
			}
			countO = 0;
		}
		return false;
	}

	public static boolean findEmptySpotVer(char[][] gameBoard) 
	{
		int countO = 0;
		int row = 0, col = 0;
		for (row = 1; row < gameBoard.length; row++) 
		{
			for (col = 1; col < gameBoard.length; col++) 
			{
				if(gameBoard[row][col] == 'O') 
				{
					countO++;
				}
			}

			if (countO == 1) 
			{
				for (int k = 0; k < gameBoard.length; k++) 
				{
					if(gameBoard[row][k] == '-')
					{
						gameBoard[row][k] = 'O';
						return true;

					}
				}
			}
			countO = 0;
		}
		return false;
	}



	// - - - - - - - - - - - - - - - - - - - - - - - Board Winner - - - - - - - - - - - - - - - - - - - - - - - - - - 


	/*
	 * 0 - no one won yet OR tie
	 * 1 - X won 
	 * 2 - O won	
	 */

	public static int checkBoardForWin(char[][] gameBoard) 
	{
		if(checkBoardForWinVertical(gameBoard) == 2 || checkBoardForWinHorizantal(gameBoard) == 2 || checkBoardForWinSlant(gameBoard) == 2) 
		{
			return 2;
		}else if(checkBoardForWinHorizantal(gameBoard) == 1 || checkBoardForWinVertical(gameBoard) == 1 || checkBoardForWinSlant(gameBoard) == 1) 
		{
			return 1;
		}else 
		{
			return 0;
		}
	}


	public static int checkBoardForWinVertical(char[][] gameBoard) 
	{
		int countX = 0;
		int countO = 0;
		for (int row = 1; row < gameBoard.length; row++) 
		{
			for (int col = 1; col < gameBoard.length; col++) 
			{
				if(gameBoard[row][col] == 'X') 
				{
					countX++;;
				}
				if(gameBoard[row][col] == 'O') 
				{
					countO++;
				}	
			}
			if(countX == 3) 
			{
				return 1;
			}else if(countO == 3) 
			{
				return 2;
			}
			countO = 0;
			countX = 0;
		}
		return 0;
	}


	public static int checkBoardForWinHorizantal(char[][] gameBoard) 
	{
		int countX = 0;
		int countO = 0;
		for (int col = 1; col < gameBoard.length; col++) 
		{
			for (int row = 1; row < gameBoard.length; row++) 
			{
				if(gameBoard[row][col] == 'X') 
				{
					countX++;;
				}
				if(gameBoard[row][col] == 'O') 
				{
					countO++;
				}	
			}
			if(countX == 3) 
			{
				return 1;
			}else if(countO == 3) 
			{
				return 2;
			}
			countO = 0;
			countX = 0;
		}
		return 0;
	}


	public static int checkBoardForWinSlant(char[][] gameBoard) 
	{
		if(gameBoard[1][1] == 'X' && gameBoard[2][2] == 'X' && gameBoard[3][3] == 'X' ||
				gameBoard[1][3] == 'X' && gameBoard[3][1] == 'X' && gameBoard[2][2] == 'X') 
		{
			return 1;
		}else if(gameBoard[1][1] == 'O' && gameBoard[2][2] == 'O' && gameBoard[3][3] == 'O' ||
				gameBoard[1][3] == 'O' && gameBoard[3][1] == 'O' && gameBoard[2][2] == 'O') 
		{
			return 2;
		}	
		return 0;
	}


	// - - - - - - - - - - - - - - - - - - - - - - - Print Board - - - - - - - - - - - - - - - - - - - - - - - - - - 


	public static void printBoard(char[][] gameBoard) 
	{
		System.out.print("\nBoard: \n\n");
		for (int i = 0; i < gameBoard.length; i++) 
		{
			for (int j = 0; j < gameBoard.length; j++) 
			{
				System.out.print("  " + gameBoard[i][j] + "  ");
			}
			System.out.println("\n");
		}
	}
}
