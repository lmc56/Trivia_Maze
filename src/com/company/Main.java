package com.company;

public class Main {

    public static void main(String[] args) {
		// Maze pract = new Maze(4, 4);
		//System.out.println("hello");
		// write your code here
		Maze maze = new Maze(4, 4);
		maze.display();

		int[][] testArray = maze.array();
		for (int i = 0; i < testArray.length; i++) {
			for (int j = 0; j < testArray[0].length; j++) {
				System.out.print(testArray[i][j] + " ");
			}
			System.out.println();
		}
		try {
			Movement testMove = new Movement(0, 1, testArray);
			boolean test = testMove.isLocked();
			//provide question if true
			System.out.println();
			int[][] testArray2 = testMove.array();
			for (int i = 0; i < testArray2.length; i++) {
				for (int j = 0; j < testArray2[0].length; j++) {
					System.out.print(testArray2[i][j] + " ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
        System.out.println("test");
        Menu myMenu = new Menu();
        Room basic = new Room();
        //maze


		Movement move;
		try {
			move = new Movement(0,1, testArray);

		 */
			/*
			After initiating movement class, it will see if move can be done, if the user
			has chose a locked or unlocked door. it will NOT make changes to the maze yet.

			After initiating the class and no exceptions are thrown, please use the
			move.isLocked() method to see if the movement is towards a locked or unlocked door.

			If move.isLocked() == true;, give the user a question.
			If the question is answered correctly,
			you can now perform the movement through the move.array() method.

			The move.array() method:
			moves the "user (#-1)",
			replace last spot as "visited (#2)",
			changes "locked door (#3)" into an "unlocked door (#-3)",
			returns the new maze array.

			Here is an example of what the code might look like:
			int[][] newArray;

			try {
				move = new Movement(0,1,testArray);
				if (move.isLocked) {
					*Provide question here*
					if(correctAnswer == true) {
						newArray[][] = move.array();
					} else *if incorrect answer, the maze does not change. Use old maze array.*
				} else { *If door is unlocked, then you can just move the user.*
					newArray[][] = move.array();
				}
			} catch (Exception e) {
				*Tell user that there was an error and to pick another movement.*
			}


		} catch (Exception e) {
			//an error will be thrown if user cannot be found, or movement
			//could not be done. handle exceptions here as you please.
		}
	}
	*/
		//0 = not visited,   1 = wall,     2 = visited,  3 = locked door,  4 = exitNode, 6 = final path
		//7 = barrier,       8 = entrance, 9 = exit, -1 = user, -3 unlocked door
		//Treat 1, 7, 8, and 9 as places you cannot go.
		//And idea of what we can do is, the number "3" can be a locked gate, and once the user
		//completes the question, the number "3" can turn into a number "-3" or some other number
		//which symbolizes the gate is unlocked.
	}
}
