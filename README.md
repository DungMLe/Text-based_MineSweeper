# Text-based_MineSweeper
PROJECT TITLE: Text-based_MineSweeper

PURPOSE OF PROJECT: This is the text-based Mine Sweeper game using Java programming language.




VERSION or DATE: 2/26/2017

HOW TO START THIS PROJECT:
1. Tile – this class represents a single tile on the Minesweeper game board. 

	 Instance variables should be whatever you need to maintain that tile’s state information. This includes a number of things: is it covered?  Is it marked?  Does it contain a mine?  How many adjacent 	mines does it have?
	 
	 Methods should include (but are not limited to): 
	 
		o Any necessary constructors, accessors, and mutators. 
		
		o A toString method that specifies how to display the tile, depending on its state. 
		
2. GameBoard – this class maintains the internal state of the game. ? 	Instance variables should include (but are not limited to) a 2-D 	array of Tile objects. 

	Methods should include (but are not limited to): 
	
		o Any necessary constructors, accessors, and mutators. 
		
		o A method that randomly distributes a specified number of mines throughout the 2-D array of tiles. 
		
		o A method that counts the number of adjacent mines for each tile. 
		
		o Methods to uncover or mark/unmark a tile at a specific location on the board. 
		
		o A method that checks whether the game is over.  The game is over if 1) all tiles with mines are marked, and all tiles
		  without mines are uncovered (win), or 2) any tile with a mine is uncovered (loss). 
		  
		o A toString method that specifies how the board should be displayed. 
		
3. GameClient – this is a client program that the user runs to play the game. It should contain an instance of GameBoard and a method that can be called to start the game (similar to the Poker and Nim examples we discussed in class).  Include a main method that’s run to play the game. 

AUTHORS: Dung Le

USER INSTRUCTIONS:



