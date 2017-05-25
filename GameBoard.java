
public class GameBoard {
    protected Tile[][] tile;
    protected int row;
    protected int col;
    protected int numMines; // number of mines
    private int countUncoveredTile; // Count number of tiles have been discovered, which do not include mine
    private int numMarks; // Count number of tiles has been mark, does not include repetation of mark in same tile
    protected boolean wantOpenAll; // If want to open all the board then set this to true
    public GameBoard(int row, int col, int numMines)
    {
        tile = new Tile[row][col]; // Instantiate tile object
        this.row = row;
        this.col = col;
        this.numMines = numMines;
        countUncoveredTile = 0;
        numMarks = 0;
        wantOpenAll = false;
        for(int i = 0; i < row; i++)  // Initialize 2-D array tile
            for(int j = 0; j < col; j++)
                tile[i][j] = new Tile();
    }
    
    public void distributeMines()
    {
        int countRow = 0;
        int countCol = 0;
        if(numMines > (row*col)/2)
        {
            System.out.println("Warning! Number of Mines can not exceed 50% number of tiles! #Mines = " + numMines);
            System.out.println("The # of mines will be set to " + (row*col)/2);
            numMines = (row*col)/2;
        }

            String[] arr = new String[numMines];
            String pos = "";
            int index = 0;
           for(int k = 0; k < numMines; k++)
            {
                 do
                 {
                     int randomRow = (int)(row*Math.random());
                     int randomCol = (int)(col*Math.random());
                     tile[randomRow][randomCol].isContainMine = true;  
                     pos = randomRow +""+randomCol; // Save the position of the mine and
                     arr[index] = pos; // save the pos to the array 
                                       // send position and array to check Duplicate location
                 }while(isDuplicate(arr,pos)); // While position duplicate repeat random
                  index++;
            }
                   
    }
    
    protected boolean isDuplicate(String[] arr, String pos)
    {
        int count = 0;
        for(int i = 0; i < arr.length; i++)
        {
            if(pos.equals(arr[i]))
                count++;
        }
        if(count >= 2)
            return true;
        return false;
    }
    
    public void countMines()
    {
        if(tile[0][0].isContainMine == true)
        {
            tile[0][1].numMine++;
            tile[1][0].numMine++;
            tile[1][1].numMine++;
        }
        
        if(tile[0][col - 1].isContainMine == true)
        {
            tile[0][col - 2].numMine++;
            tile[1][col - 1].numMine++;
            tile[1][col - 2].numMine++;
        }
        
        if(tile[row -1][0].isContainMine == true)
        {
            tile[row - 2][0].numMine++;
            tile[row - 2][1].numMine++;
            tile[row - 1][1].numMine++;
        }
        
        if(tile[row -1][col - 1].isContainMine == true)
        {
            tile[row - 2][col - 1].numMine++;
            tile[row - 2][col - 2].numMine++;
            tile[row - 1][col - 2].numMine++;
        }
        
            for(int colN = 1; colN < col - 1; colN++)
            {
                if(tile[0][colN].isContainMine == true)
                {
                    for(int r = 0; r <= 1; r++)
                    {
                        for(int c = colN - 1; c <= colN + 1; c++)
                            //if(c != colN || r != 0)
                                tile[r][c].numMine++;
                    }
                }
                if(tile[row -1][colN].isContainMine == true)
                {
                    for(int r1 = row - 2; r1 <= row - 1; r1++)
                        for(int c1 = colN - 1; c1 <= colN + 1; c1++)
                            //if(c1 != colN || r1 != row -1)
                                tile[r1][c1].numMine++;
                }
            }
            
            for(int rowN = 1; rowN < row - 1; rowN++)
            {
                if(tile[rowN][0].isContainMine == true)
                {
                    for(int c2 = 0; c2 <= 1; c2++)
                        for(int r2 = rowN - 1; r2 <= rowN + 1; r2++)
                            if(r2 != rowN || c2 != 0)
                                tile[r2][c2].numMine++;
                }
                if(tile[rowN][col - 1].isContainMine == true)
                {
                    for(int c3 = col - 2; c3 <= col - 1; c3++)
                        for(int r3 = rowN - 1; r3 <= rowN + 1; r3++)
                           if(r3 != rowN || c3 != col -1)
                                tile[r3][c3].numMine++;
                }
            }
            
            for(int i = 1; i < row - 1; i++)
                for(int j = 1; j < col - 1; j++)
                    if(tile[i][j].isContainMine == true)
                        for(int tempRow = i - 1; tempRow <= (i + 1); tempRow++)
                            for(int tempCol = j - 1; tempCol <= (j + 1); tempCol++)
                                tile[tempRow][tempCol].numMine++;
        
    }
    
    public void uncoveredOrMarked(int rowPos, int colPos, String choice)
    {
        if(choice.equals("m"))
        {
            tile[rowPos][colPos].isMarked(true);
            if(tile[rowPos][colPos].countMarks == 1)
                numMarks++;
            else if(tile[rowPos][colPos].countMarks == 0)
                numMarks--;
        }
        if(choice.equals("u"))
        {
            tile[rowPos][colPos].isCovered(false);
            //if(tile[rowPos][colPos].numUncoveredTile >= 1 && tile[rowPos][colPos].countMarks == 0)
                //countUncoveredTile++;
                if(tile[rowPos][colPos].toString().equals("0"))
                    expand(rowPos,colPos);
            countUncoveredTile = 0;        
            for(int i = 0; i < row; i++)
                for(int k = 0; k < col; k++)
                    if(!tile[i][k].toString().equals("!") && !tile[i][k].toString().equals(" "))
                        countUncoveredTile++;
        }
    }
    
    public int isWin(int rowPos, int colPos)
    {
        String tileContent = tile[rowPos][colPos].adjecentMine;
     
        if(tileContent.equals("*"))
            return 0;
           
        if(numMarks + countUncoveredTile == (row*col) && (numMarks == numMines))
            return 1;
            
        return 2;
    }
    // This method is used to open all the tiles
    public void openATile(int r, int c)
    {
        tile[r][c].isMarked = true;
        tile[r][c].isCovered = false;
        tile[r][c].countMarks = 2;
    }
    
    public void expand(int r, int c)
    {
        if(tile[r][c].toString().equals("0"))
        {
            tile[r][c].markExpand = true;
            if( (c - 1) >= 0 && (r - 1) >= 0 )
            {
                if(tile[r-1][c-1].countMarks != 1)
                {
                    openATile(r-1,c-1);
                    if(tile[r-1][c-1].toString().equals("0") && tile[r-1][c-1].markExpand == false)
                        expand(r-1,c-1);
                }
            }
            if( (r - 1) >= 0 && (c + 1) < col )
            {
                if(tile[r-1][c+1].countMarks != 1)
                {
                    openATile(r-1,c+1);
                    if(tile[r-1][c+1].toString().equals("0") && tile[r-1][c+1].markExpand == false)
                        expand(r-1,c+1);
                }
            }
            if( (r - 1) >= 0 )
            {
                if(tile[r-1][c].countMarks != 1)
                {
                    openATile(r-1,c);
                    if(tile[r-1][c].toString().equals("0") && tile[r-1][c].markExpand == false)
                        expand(r-1,c);
                }
            }
            if( (c - 1) >= 0 )
            {
                if(tile[r][c-1].countMarks != 1)
                {
                    openATile(r,c-1);
                    if(tile[r][c-1].toString().equals("0") && tile[r][c-1].markExpand == false)
                        expand(r,c-1);
                }
            }
            if( (c + 1) < col )
            {
                if(tile[r][c+1].countMarks != 1)
                {
                    openATile(r,c+1);
                    if(tile[r][c+1].toString().equals("0") && tile[r][c+1].markExpand == false)
                        expand(r,c+1);
                }
            }
            if( (r + 1) < row && (c - 1) >= 0 )
            {
                if(tile[r+1][c-1].countMarks != 1)
                {
                    openATile(r+1,c-1);
                    if(tile[r+1][c-1].toString().equals("0") && tile[r+1][c-1].markExpand == false)
                        expand(r+1,c-1);
                }
            }
            if( (r + 1) < row )
            {
                if(tile[r+1][c].countMarks != 1)
                {
                    openATile(r+1,c);
                    if(tile[r+1][c].toString().equals("0") && tile[r+1][c].markExpand == false)
                        expand(r+1,c);
                }
            }
            if( (c + 1) < col && (r + 1) < row)
            {
                if(tile[r+1][c+1].countMarks != 1)
                {
                    openATile(r+1,c+1);
                    if(tile[r+1][c+1].toString().equals("0") && tile[r+1][c+1].markExpand == false)
                        expand(r+1,c+1);
                }
            }
        }
        
    }
    
    public String toString()
    {
        String s = "";
        int r = row;
        int c = col;
        s +=  "   ";
        for(int t = 0; t < c; t++)
                s += " " + t + "  ";
                s += "\n";
        for(int i = 0; i < r; i++)
        {
            s += "  ";
            for(int k = 0; k < c; k++)
                s += "+---";
            
            s += "+\n";
            s += i + " |";
            for(int j = 0; j < c; j++)
            {
                
                //board.tile[i][j].isCovered(false);// When the isCovered(false) method is called
                                                // it automaically assign isMarked to true in its method
                if(wantOpenAll)
                    openATile(i,j);
                s += " ";
                s += tile[i][j].toString() + " |";
            }
            
            s += "\n";
        }
        s += "  ";
        for(int k = 0; k < c; k++)
                s += "+---";
                s += "+\n";
                s += "  Mines: " + numMines + "  Marked: " + numMarks + " Uncovered Tiles: "+ countUncoveredTile+"\n";
        return s;
    }
    
    // Test method, used to test the GameBoard class
    public static void main(String[] args) 
    {
        GameBoard board = new GameBoard(6,6,11);
        
        board.distributeMines();
        board.countMines();
        System.out.println(board);
        board.uncoveredOrMarked(0,0,"m");
        System.out.println(board);
        board.uncoveredOrMarked(0,0,"m");
        System.out.println(board);
        board.uncoveredOrMarked(0,0,"m");
        System.out.println(board);
        board.uncoveredOrMarked(0,0,"u");
        System.out.println(board);
        board.uncoveredOrMarked(0,0,"m");
        System.out.println(board);
        board.uncoveredOrMarked(0,0,"u");
        System.out.println(board);
        board.uncoveredOrMarked(0,0,"m");
        System.out.println(board);
            for(int i = 0; i < board.row; i++)
                for(int j = 0; j < board.col; j++)
                    board.uncoveredOrMarked(i,j,"u");
             System.out.println(board);
        
    }
}
