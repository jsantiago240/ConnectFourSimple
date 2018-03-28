/*Javius Santiago
/Gifted Independent Study/AP Java Project
/2017
*/

//Allows programmer to use Scanners  to recieve user input.
import java.util.Scanner;

//Main Class of the program
public class Main
{
	//Declares the variable "scan" as a new Scanner object
	static Scanner scan = new Scanner(System.in);
	final static int WIDTH = 7; 											//width of board
	final static int HEIGHT = 6;											//height of board
	static char[][] board = new char[HEIGHT][WIDTH];	//The game board is a two dimmensional array object
	static boolean gameIsOn = false;									//flag variable to signify if the game is currently running
	static String name;																//Name of the player
	
	//main method
  public static void main(String[] args)
  {

  	fillBoard();																		//Runs fillBoard method, which fills the array with periods which act as placeholders 
  	System.out.println("Hello, what is your name?");//Asks player for their name
  	name = scan.nextLine();													//Sets "name"  as the players input after asking their name
  	System.out.println("Hello " + name + ", it's nice to meet you.");//Greets the player
  	giveInstructions();															//Gives the instructions to the player
  	printBoard();																		//Prints every element in the 2D array "board" which symbolizes a visual Connect Four board.
		gameIsOn = true;																//Sets flag variable to true to show that the game has started and is currently running.
		//This is the game loop, it runs as long as the game is running
		while(gameIsOn)																	//While the game is on/running...
		{
			plyrGo();																			//Gets players input, and places their piece
			printBoard();																	//prints the game board
      botGo();																			//The "bot" (computer) goes in a random spot
      printBoard();																	//Prints the board once again to show where the bot has placed its piece.
		}
  }//end of main method
  //This method fills the board with periods which act as placeholders until the player or computer places a game piece ('X' or 'O') in its place.
  public static void fillBoard()
  {
  	//This double nested for loop goes through each row and each column of the 2D array in order to access each element within it, it then places a '.' character in every value of the array (board).
    for(int r = 0; r < HEIGHT;r++)
    	for(int c = 0; c < WIDTH;c++)
    		board[r][c] = '.';
  }//end of fillBoard method
  //This method prints every value in the board array in a way which allows the user to visualize the array as a Connect Four game board. 
  public static void printBoard()
  {
  	//Prints numbers which number each column, there are 7
    System.out.println("0123456");
    //This double nested for each loop accesses each row in the board array and then each value in that row and prints the value of each element, then goes to the next line to print the values in the next row.
    for(char[] currentRow : board)
    {
      for(char val : currentRow)
      {
        System.out.print(val);
      }
      System.out.println();												//Moves to the next line before printing the next row, to keep it organized and so it appears as an an actual representation of a Connect Four board.
    }
    System.out.println();													//Moves to the next line
  }//end of printBoard method
  public static void plyrGo()
  {
  	int col = scan.nextInt();												//Player's input (where they wish to place their piece)
  	int counter = 1;																//Keeps track of how full the selected column is, acts as gravity, in a way.
  	while (gameIsOn)																//while the game is still running...
  	{
  		//If the user attempts to place their piece in a column that doesn't exist...
  		if (col > 6 || col < 0)
			{
				System.out.println("That column doesn't exist");//Tells them the column is nonexistant
				break;																			//Exits the while loop at this point
			}
			if (board[5][col] == '.')											//If the lowest point of the selected column is empty...
			{
				board[5][col] = 'X';												//Places the player's piece in that position
				break;																			//Exits the while loop at this point
			}
			else if (board[5][col] != '.')								//If the lowest point of the column is not empty...
			{
				if (board[5-counter][col] == '.')						//If the space above the lowest point is empty (and later, the space above the previously attempted space) is empty...
				{
					board[5-counter][col] = 'X';							//Places the player's game piece in this position
					break;																		//Exits the while loop at this point
				}	
			}
			
			counter++;																		//Increments the counter by one and then...
			
			if (counter > 5)															//If the counter is bigger than 5, that means that the column is full, since the height is 6 and java has a 0th position...
			{
				System.out.println("Column is full");				//Tells the user that this column is full
				break;																			//Exits the while loop at this point
			}
			//Since this is a while loop, as long as the col value didn't cause the program to break out of this loop and the "gameIsOn" variable still = true, it attempts to place the piece again, this causes the program to go back and try the space above, this is what makes the counter variable useful, as mentioned in the comment on line 78 in parentheses.
  	}
  }//end of plyrGo method
  //This is the same as plyrGo(), but it doesn't recieve user input, and places the bot's game piece in a random spot based a a psuedorandom number generated by the computer in the getRandomNum method.
  public static void botGo()
  {
  	/** SEE COMMENTS IN PREVIOUS METHOD (plyrGo()) FOR EXPLANATION OF THIS METHOD.*/
    int col = getRandomNum(1, 6);
    int counter = 1;
    while (gameIsOn)
    {    
      if (board[5][col] == '.')
      {
        board [5][col] = 'O';
        break;
      }
      else if (board[5][col] != '.')
      {
        if (board[5-counter][col] == '.')
        {
        	 board[5-counter][col] = 'O';
        	 break;     
        }     
      }
      counter ++;
      if (counter > 5)
      {
        System.out.println("That Column is full");
        break;
      }
    }
  }//End of the botGo method
  //Returns a pseudorandom number between the low value and high value, inclusive, generated by the computer.
  public static int getRandomNum(int low, int high)
	{
  	return (int) (Math.random() * (high - low + 1)) + low; 
  }
  //Gives game instructions to the player
	public static void giveInstructions()
	{
		System.out.println("To begin, enter the column number where you would like to place your game piece.");
	}
}//End of main class 


