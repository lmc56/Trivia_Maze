package com.company;

public class Main {

    public static void main(String[] args) {
       // Maze pract = new Maze(4, 4);
        //System.out.println("hello");
	// write your code here
        System.out.println("test");
        Menu myMenu = new Menu();
        // starts rooms
        //Room basic = new Room();
        //maze starter from Tony
        /*
	    Maze maze = new Maze(4,4);
	    maze.display();

	    int[][] testArray = maze.array();
	    for (int i = 0; i < maze.getHeight(); i++) {
	        for (int j = 0; j < maze.getWidth(); j++) {
	            System.out.print(testArray[i][j] + " ");
            }
	        System.out.println();
        }
	    */
    }
    //0 = not visited,   1 = wall,     2 = visited,  3 = no wall,  4 = exitNode, 6 = final path
    //7 = barrier,       8 = entrance, 9 = exit.
    //Treat 1, 7, 8, and 9 as places you cannot go.
    //And idea of what we can do is, the number "3" can be a locked gate, and once the user
    //completes the question, the number "3" can turn into a number "-3" or some other number
    //which symbolizes the gate is unlocked.
}
