import java.util.Scanner;

public class TicTacToe 
{
	static Scanner key = new Scanner( System.in );
	
	public static void main( String[] args ) throws InterruptedException 
	{
		System.out.println( "You (X) are playing Tic-Tac-Toe against a computer (O): Good luck!");
		playGame();
	}
	
	public static void playGame() throws InterruptedException 
	{
		char[][] board = {{' ', '|', ' ', '|', ' '},
						  {'-', '+', '-', '+', '-'},
						  {' ', '|', ' ', '|', ' '},
						  {'-', '+', '-', '+', '-'},
						  {' ', '|', ' ', '|', ' '}};
		int count = 0;
		printBoard( board );
		while( count < 9 )
		{
			playTurnOne( board );
			count++;
			printBoard( board );
			if( isGameOver( board, 'X' ) ) {
				if( !endGame( true ) )
					break;
			} if( count < 9 ){
				playTurnComp( board );
				count++;
				printBoard( board );
				if( isGameOver( board, 'O' ) )
					if( !endGame( false ) )
						break;
			}
		}
		if( count > 8 )
			stalemate();
	}
	
	public static void stalemate() throws InterruptedException 
	{
		System.out.println( "Game ended in a draw" );
		System.out.println("If you want to play again, enter 'y', or enter 'n' to quit");
		char letter = key.next().charAt(0);
		while( letter != 'y' && letter != 'n')
		{
			System.out.println("Invalid input: If you want to play again, enter 'y', or enter 'n' to quit");
			letter = key.next().charAt(0);
		}
		if( letter == 'y' )
			playGame();
	}
	
	public static boolean endGame( boolean oneWon ) throws InterruptedException 
	{
		if( oneWon )
			System.out.println( "You won the game, congrats!");
		else
			System.out.println( "The computer won the game");
		System.out.println("If you want to play again, enter 'y', or enter 'n' to quit");
		char letter = key.next().charAt(0);
		while( letter != 'y' && letter != 'n')
		{
			System.out.println("Invalid input: If you want to play again, enter 'y', or enter 'n' to quit");
			letter = key.next().charAt(0);
		}
		if( letter == 'y' )
			playGame();
		return false;
	}
	
	public static void playTurnComp( char[][] board ) throws InterruptedException
	{
		boolean moveComplete = false;
		System.out.println("Computer's turn:");
		Thread.sleep(2000);
		while( !moveComplete )
		{
			int play = (int) (Math.random() * 9) + 1;
			int[] spot = position( play );
			if( board[spot[0]][spot[1]] != 'X' && board[spot[0]][spot[1]] != 'O' )
			{
				board[spot[0]][spot[1]] = 'O';
				moveComplete = true;
			}
		}
	}
	
	public static void printBoard( char[][] board )
	{
		for( char[] r : board )
		{
			for( char c : r )
				System.out.print( c );
			System.out.println();
		}
	}
	
	public static void playTurnOne( char[][] board )
	{
		boolean moveComplete = false;
		while( !moveComplete )
		{
			System.out.println("Your Turn: Enter a spot to mark (1-9)");
			int play = key.nextInt();
			if( validSpot( play ) )
			{
				int[] spot = position( play );
				if( board[spot[0]][spot[1]] != 'X' && board[spot[0]][spot[1]] != 'O' )
				{
					board[spot[0]][spot[1]] = 'X';
					moveComplete = true;
				}
			}
		}
	}
	
	public static boolean validSpot( int play )
	{
		return (play >= 1 && play <= 9 );
	}
	
	public static int[] position( int play )
	{
		switch( play ) {
			case 1:
				return new int[] {0,0};
			case 2:
				return new int[] {0,2};
			case 3:
				return new int[] {0,4};
			case 4:
				return new int[] {2,0};
			case 5:
				return new int[] {2,2};
			case 6:
				return new int[] {2,4};
			case 7:
				return new int[] {4,0};
			case 8:
				return new int[] {4,2};
			case 9: 
				return new int[] {4,4};
			default:
				return new int[] {0,0};
		}
	}
	
	public static boolean isGameOver( char[][] board, int player )
	{
		if( board[0][0] == player ) { // top left 
			if( board[0][2] == player && board[0][4] == player ) // top row
				return true;
			if( board[2][2] == player && board[4][4] == player ) // primary diagonal
				return true;
			if( board[2][0] == player && board[4][0] == player ) // left column
				return true;
		}
		if( board[2][0] == player ) { // middle left
			if( board[2][2] == player && board[2][4] == player ) // middle row
				return true;
		}
		if( board[4][0] == player ) { // bottom left
			if( board[4][2] == player && board[4][4] == player ) // bottom row
				return true;
			if( board[2][2] == player && board[0][4] == player ) // secondary diagonal
				return true;
		}
		if( board[0][2] == player ) { // top middle
			if( board[2][2] == player && board[4][2] == player ) // middle column
				return true;
		}
		if( board[0][4] == player ) { // top right
			if( board[2][4] == player && board[4][4] == player ) // right column
				return true;
		}
		return false;
	}
}