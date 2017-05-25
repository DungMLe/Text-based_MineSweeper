import java.util.*;
public class Test {
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		Tile[] tile = new Tile[5];
		for(int i = 0; i < tile.length; i++)
		{
			tile[i] = new Tile();
			//tile[i].isCovered(false);
		}
		
		 for(int k = 0; k < 2; k++)
	     {
	          int random = (int)(5 * Math.random());
	          tile[random].isContainMine = true;
	     }
		if(tile[0].isContainMine == true)
		{
			tile[1].numMine++;
		}
		if(tile[tile.length - 1].isContainMine == true)
		{
			tile[tile.length - 1].numMine++;
		}
		for(int j = 1; j < tile.length - 1; j++)
		{
			if(tile[j].isContainMine == true)
			{
				tile[j+1].numMine++;
				tile[j-1].numMine++;
			}
				
		}
		
		
		String choice = "";
		do
		{
			System.out.print("Chose position of tile from 1 to 5: ");
				int position = input.nextInt();
			System.out.print("(M)ark or (U)ncover a tile? ");
			String answer = input.next();
			if(answer.equals("m"))
			{
				tile[position - 1].isMarked(true);
			}
			if(answer.equals("u"))
			{
				tile[position - 1].isCovered(false);
			}
			
			for(int i = 0; i < tile.length; i++)
			{
				System.out.print(" ");
				System.out.print(tile[i] + " |");
			}
			System.out.println();
			System.out.print("Continues...? ");
			choice = input.next();
		}while(choice.equals("y"));
	}
}
