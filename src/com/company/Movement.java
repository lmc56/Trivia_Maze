package com.company;

public class Movement {
    private int myX;
    private int myY;
    private int myUX;
    private int myUY;
    private int myDoorX;
    private int myDoorY;
    private int myPostUX;
    private int myPostUY;
    private int[][] myMaze;
    private int myColumns;
    private int myRows;
    private boolean myUserFound;
    private boolean canMove;
    private boolean myLocked;

    public Movement(int x1, int y1, int[][] maze1) throws Exception {
        myX = x1;
        myY = y1;
        myUX = 0;
        myUY = 0;
        System.out.println("XY: " +myX + " " + myY);
        myMaze = maze1;
        myColumns = myMaze.length;
        myRows = myMaze[0].length;
        myUserFound = findUser();

        if ((myUserFound) && (myX == 0 || myY == 0)) {
            myDoorX = myUX + myX;
            myDoorY = myUY + myY;
            myPostUX = myUX;
            myPostUY = myUY;
            canMove = setCanMove();

            if (!canMove) {
                throw new Exception("Novement has failed.");
            }
        } else {
            throw new Exception("Either movement error, or movement input error.");
        }
    }

    public boolean isLocked() {
        return myLocked;
    }

    public int[][] array() {
        myMaze[myUY][myUX] = 2;
        myMaze[myDoorY][myDoorX] = -3;
        myMaze[myPostUY][myPostUX] = -1;
        return myMaze;
    }

    private boolean setCanMove() {
        if (myMaze[myDoorY][myDoorX] == 3 || myMaze[myDoorY][myDoorX] == -3) { //if the spot intending to move is a door, move person
            if (myX != 0) {
                myPostUX = myDoorX + myX;
            }
            if (myY != 0) {
                myPostUY = myDoorY + myY;
            }
            if (myMaze[myDoorY][myDoorX] == 3) {
                myLocked = true;
            } else if (myMaze[myDoorY][myDoorX] == -3) {
                myLocked = false;
            }
            return true;
        }
        return false;
    }


    private boolean findUser() { //if spot = user, record spot and return true, else false
        for (int i = 0; i < myColumns; i++) {
            for (int j = 0; j < myRows; j++) {
                if (myMaze[i][j] == -1) {
                    myUY = i;
                    myUX = j;
                    myUserFound = true;
                    return true;
                }
            }
        }
        myUserFound = false;
        return false;
    }
}