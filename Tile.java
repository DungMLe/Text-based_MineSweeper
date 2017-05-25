
public class Tile {
    protected boolean isMarked;
    protected boolean isCovered;
    protected boolean isContainMine;
    protected int numMine;
    protected String adjecentMine;
    protected int countMarks;
    protected int numUncoveredTile;
    protected boolean markExpand = false;
    public Tile()
    {
        isMarked = false; // Set to unmarked
        isCovered = true; // Set to uncovered(or is covered) true = covered, false = uncovered
        isContainMine = false; // Not contain mine
        numMine = 0; // adjecent number of mine is 0
        adjecentMine = " "; // 
        countMarks = 0; // number times the user click mark, if more than 2 reset it to 0 and unmark the tile
        numUncoveredTile = 0;
    }
    
    public void isMarked(boolean im)
    {
        isMarked = im;
        countMarks++; // Increase by 1 every time the isMarked is called
        if(countMarks > 1) // However, if countMarks exceeds 1 then
            countMarks = 0; // reset countMarks to 0
        /* If the tile is open and displays with a number (not ! and " ") then set countMarks to a
         * number different from 0 or 1, so that when uncoveredOrMarked() method in GameBoard is called
         * instant variable numMarks in GameBoard will not be changed
        */
        if(isCovered == false && !adjecentMine.equals("!") && !adjecentMine.equals(" "))
            countMarks = 2;
    }
    
    public void isCovered(boolean ic)
    {
        isCovered = ic;
        if(isCovered == false) // For case when the uncovered is called at the beginning 
        {
             if(isContainMine == false) // When a tile is uncovered and does not contain mine then
                numUncoveredTile++; // increase number of open tile
            isMarked = true; // isMarked's status will be turned true
        }
    }
    
    public void containMine(boolean ct)
    {
        isContainMine = ct;
    }
    
    public String toString()
    {
        if(isMarked == true && isCovered == true) //when the tile has not been uncovered
        {
             if(countMarks == 0) // if the mark is mark again then return empty string
                    return adjecentMine = " ";
             else // else return the "!" sign - symbol of marked tile
                    return adjecentMine = "!";
        }
        // When the tile has been uncovered includes the case when the tile is marked
        else if(isMarked == true && isCovered == false) 
        {
            if(adjecentMine.equals("!")) // When the tile is marked or countMarks = 1
                if(countMarks == 0) // if the mark is mark again then return empty string
                    return adjecentMine = " ";
                else if(countMarks == 1) // else return the "!" sign - symbol of marked tile
                    return adjecentMine = "!";
            
            /* The if statement bellow is for the situation when mark -> uncovered -> mark -> mark
               the tile is blank (or ""); however, the status of isCovered = false (or uncovered)
               this will make the code access this block of else if statement, skip the first if statement and
               run the last if statement, which gives out the adjencent number of mines instead of a mark.  
               */ 
              
            // countMarks == 1 iff the tile is marked with "!"
            if(countMarks == 1 && adjecentMine.equals(" ")) // If there is a blank space and the user wants to mark 
            {
                //countMarks = 0;
                return adjecentMine = "!"; // Return the mark "!" 
            }
            
            // And if tile does not contain "!" or " " then uncover the tile
            if(isContainMine == false)
            {
                //numUncoveredTile++;
                adjecentMine = "" + numMine;
            }
            else
                adjecentMine = "*";
        }

        return adjecentMine;
    }
    
    
    
    
}
