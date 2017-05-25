
/**
 * Write a description of class GameClient here.
 * Mine Sweeper Game (text based)
 * GameClient Class
 * @Dung Le (your name) 
 * @2/27/2017
 */
import java.util.Scanner;
import java.util.InputMismatchException;
public class GameClient
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("This version is for honor section. If this does not work out.\n Please refer to the first submission!!! Thanks");
        System.out.println("************Wellcome to the Mine Sweeper Game**************\n\n");
        int rows = 0, cols = 0, numMines = 0;
        do
        {
            System.out.print("Enter number of rows (4-10): ");
                rows = input.nextInt();
        }while(rows < 4 || rows > 10);
        do
        {
            System.out.print("Enter number of columns (4-10): ");
                cols = input.nextInt();
        }while(cols < 4 || cols > 10);
        
        int total = (int)((rows * cols)/2);
        do
        {
            System.out.print("Enter number of mines (1-" + total + "): ");
                numMines = input.nextInt();
        }while(numMines < 1 || numMines > total);
        GameBoard board = new GameBoard(rows,cols,numMines);
        
        System.out.print(board);
        System.out.println("------------------Game On!-------------------\n");
            
            board.distributeMines();
            board.countMines();
            System.out.println("Select the first tile to uncover!");
            int r = 0;
            int c = 0;
            boolean exceptionThrown = false;
            do
            {
                System.out.print("Enter the tile's row (0-" + (rows - 1)+ ")"+  "and column (0-"+(cols -1)+"): ");
                try{
                    r = input.nextInt();
                    c = input.nextInt();
                    exceptionThrown = false;
                   }catch(InputMismatchException e){
                    exceptionThrown = true;
                    input.nextLine();
                    System.out.println("Error: Invalid Input! Try again.");
                   }
            }while(exceptionThrown);
        board.uncoveredOrMarked(r,c,"u");

        // Making sure first open tile can not be mine
        while(board.tile[r][c].isContainMine == true) // if the first open tile is a mine then reset the board
        {
            board = new GameBoard(rows,cols,numMines);
            board.distributeMines();
            board.countMines();
            board.uncoveredOrMarked(r,c,"u");
        }
    
        System.out.print(board);
        int checkWin = 0;
        do
        {
            boolean exceptionThrown1 = false;
            do
            {
                System.out.print("Enter the tile's row (0-" + (rows-1)+ ")"+"and column (0-"+(cols-1)+"): ");
                try
                {
                    r = input.nextInt();
                    c = input.nextInt();
                    input.nextLine();
                    exceptionThrown1 = false;
                }catch(InputMismatchException e){
                    exceptionThrown1 = true;
                    input.nextLine();
                    System.out.println("Error: Invalid Input! Try again.");
                }
            }while(exceptionThrown1);
            String answer = "";
            do
            {
                System.out.print("(M)ark or (U)ncover a tile? : ");
                    answer = input.next();
                answer = answer.toLowerCase();
            }while(!answer.equals("m") && !answer.equals("u"));
            board.uncoveredOrMarked(r,c,answer);
            System.out.print(board);
            checkWin = board.isWin(r,c);
            
            if(checkWin == 0) // If hit a mine then
            {
                board.wantOpenAll = true; // Open all the tiles in the board
                System.out.println("----------------------GAME OVER-------------------------");
                System.out.println("--------------------!!YOU LOOSE!!-----------------------");
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                }
                System.out.print(board); // Print out the board
            }
            if(checkWin == 1)
            {
                System.out.println("----------------------GAME OVER-------------------------");
                System.out.println("---------------------!!YOU WIN!!------------------------");
            }
        }while( checkWin != 1 && checkWin != 0);
    }        
}