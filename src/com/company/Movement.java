package com.company;

public class Movement {
    private int x;
    private int y;
    private int ux;
    private int uy;
    private int doorX;
    private int doorY;
    private int postUX;
    private int postUY;
    private int[][] maze;
    private int columns;
    private int rows;
    private boolean userFound;
    private boolean canMove;
    private boolean locked;

    public Movement(int x, int y, int[][] maze) throws Exception {
        userFound = false;
        this.x = x;
        this.y = y;
        this.ux = 0;
        this.uy = 0;
        this.doorX = ux + x;
        this.doorY = uy + y;
        this.postUX = 0;
        this.postUY = 0;
        this.maze = maze;
        columns = maze.length;
        rows = maze[0].length;
        userFound = findUser();
        if (userFound && (x == 0 || y == 0)) {
            canMove = setCanMove();
            if (!canMove) {
                throw new Exception("Novement has failed.");
            }
        } else {
            throw new Exception("Either movement error, or movement input error.");
        }
    }

    public boolean isLocked() {
        return locked;
    }

    public int[][] array() {
        maze[uy][ux] = 2;
        maze[doorY][doorX] = -3;
        maze[postUY][postUX] = -1;
        return maze;
    }

    private boolean setCanMove() {
        if (maze[doorY][doorX] == 3 || maze[doorY][doorX] == -3) { //if the spot intending to move is a door, move person
            if (x != 0) {
                postUX = doorX + x;
            }
            if (y != 0) {
                postUY = doorY + y;
            }
            if (maze[doorY][doorX] == 3) {
                locked = true;
            }
            return true;
        }
        return false;
    }


    private boolean findUser() { //if spot = user, record spot and return true, else false
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if (maze[i][j] == -1) {
                    uy = i;
                    ux = j;
                    userFound = true;
                    return true;
                }
            }
        }
    userFound = false;
    return false;
    }
}
