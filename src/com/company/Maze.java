package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Maze implements ActionListener{
    private final int USER = -1;
    private final int UNVISITED = 0;
    private final int WALL = 1;
    private final int VISITED = 2;
    private final int DOOR = 3;
    private final int EXIT_NODE = 4;
    private final int FINAL_PATH = 6;
    private final int BARRIER = 7;
    private final int ENTRANCE = 8;
    private final int EXIT = 9;

    private class Node{
        public int myX;
        public int myY;

        public Node(int x1, int y1) {
            this.myX = x1;
            this.myY = y1;
        }

        public Node(Node node) {
            this.myX = node.myX;
            this.myY = node.myY;
        }
    }
    private int myHeight;
    private int myWidth;
    private int[][] myMaze;
    private Random myRand = new Random();
    private Stack<Node> myStack = new Stack<>();
    private ArrayList<Node> myPath = new ArrayList<>();
    JButton buttons[];
    JButton help;
    JButton exit;

    public Maze(int height1, int width1) {
        this.myWidth = width1 * 2 + 1;
        this.myHeight = height1 * 2 + 1;
        this.buttons = new JButton[height1 * width1];

        buildGraph();
        buildMaze();
        unvisit();



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (JButton x : buttons){
            if(e.getSource() == x){
                Door myDoor = new Door();
            }
        }

        if (e.getSource() == exit){
            System.exit(0);
        }

    }

    private void buildGraph() {
        myMaze = new int[myHeight][myWidth];
        for (int i = 0; i < myHeight; i++) {
            for (int j = 0; j < myWidth; j++) {
                myMaze[i][j] = UNVISITED;
            }
        }
        for (int i = 0; i < myWidth; i++) {
            myMaze[0][i] = BARRIER;
            myMaze[myHeight - 1][i] = BARRIER;
        }

        for (int i = 1; i < myHeight - 1; i++) {
            for (int j = 0; j < myWidth; j++) {
                if (j % 2 == 0) {
                    myMaze[i][j] = WALL;
                }
                if (i % 2 == 0) {
                    myMaze[i][j] = WALL;
                }
                if (j == 0 || j == myWidth -1) {
                    myMaze[i][j] = BARRIER;
                }
            }
        }
        myMaze[0][1] = ENTRANCE;
        myMaze[myHeight - 1][myWidth - 2] = EXIT;
        myMaze[myHeight - 2][myWidth - 2] = EXIT_NODE;
    }
    //0 = not visited,   1 = wall,     2 = visited,  3 = no wall,  4= exitNode
    //7 = barrier,     8 = entrance, 9 = exit,
    private void connectNodes(Node next1, Node tempNode1) {
        int dx = tempNode1.myX - next1.myX;
        int dy = tempNode1.myY - next1.myY;
        if(dy != 0) {
            if(dy > 0) {
                myMaze[next1.myY + 1][next1.myX] = DOOR;
            }
            if(dy < 0) {
                myMaze[next1.myY - 1][next1.myX] = DOOR;
            }
        }
        if(dx != 0) {
            if(dx > 0) {
                myMaze[next1.myY][next1.myX + 1] = DOOR;
            }
            if(dx < 0) {
                myMaze[next1.myY][next1.myX - 1] = DOOR;
            }
        }
    }

    private void buildMaze() {
        myStack.push(new Node(1,1));
        myPath.add(new Node(1,1));
        boolean solved = false;
        while (!myStack.empty()) {
            Node next = myStack.pop();

            if (myMaze[next.myY][next.myX] == EXIT_NODE) {
                solved = true;
                myPath.add(next);
            }
            if (solved == false) {
                myPath.add(next);
            }

            if (myMaze[next.myY][next.myX] != EXIT_NODE) {
                myMaze[next.myY][next.myX] = VISITED;
            }
            if (hasNeighbors(next)) {

                ArrayList<Node> neighbors = findNeighbors(next);
                int randomSelection = myRand.nextInt(neighbors.size());

                Node tempNode = new Node(neighbors.remove(randomSelection)); //x=1,y=3
                connectNodes(next, tempNode);

                while(!neighbors.isEmpty()) {
                    randomSelection = myRand.nextInt(neighbors.size());
                    Node stackNode = neighbors.remove(randomSelection);
                    if (!(myMaze[stackNode.myY][stackNode.myX] == VISITED) || !(myMaze[stackNode.myY][stackNode.myX] == EXIT_NODE)) {
                        myStack.push(stackNode);

                        myMaze[stackNode.myY][stackNode.myX] = VISITED;
                        connectNodes(next,stackNode);
                    }
                }
                myStack.push(tempNode);
            }
        }
        myMaze[1][1] = USER;
        myMaze[myHeight - 2][myWidth - 2] = EXIT_NODE;
    }

    private void unvisit() {
        for (int i = 0; i < myHeight; i++) {
            for (int j = 0; j < myWidth; j++) {
                if (myMaze[i][j] == VISITED) {
                    myMaze[i][j] = UNVISITED;
                }
            }
        }
    }

    void displayMaze(){
        JPanel mazeView = new JPanel();
        mazeView.setLayout(new GridLayout(myHeight, myWidth));
    }

    //find neighbor method
    private ArrayList<Node> findNeighbors(Node node) {
        ArrayList<Node> neighbors = new ArrayList<>();
        if(myMaze[node.myY][node.myX - 1] == WALL || myMaze[node.myY][node.myX - 1] == DOOR) { //left check
            if (myMaze[node.myY][node.myX - 2] == UNVISITED || myMaze[node.myY][node.myX - 2] == EXIT_NODE) {
                neighbors.add(new Node(node.myX - 2, node.myY));
            }
        } if(myMaze[node.myY][node.myX + 1] == WALL || myMaze[node.myY][node.myX + 1] == DOOR) { //right check
            if (myMaze[node.myY][node.myX + 2] == UNVISITED || myMaze[node.myY][node.myX + 2] == EXIT_NODE) {
                neighbors.add(new Node(node.myX + 2, node.myY));
            }
        } if(myMaze[node.myY + 1][node.myX] == WALL || myMaze[node.myY + 1][node.myX] == DOOR) { //bottom check
            if (myMaze[node.myY + 2][node.myX] == UNVISITED || myMaze[node.myY + 2][node.myX] == EXIT_NODE) {
                neighbors.add(new Node(node.myX, node.myY + 2));
            }

        } if(myMaze[node.myY - 1][node.myX] == WALL || myMaze[node.myY - 1][node.myX] == DOOR) { //top check
            if (myMaze[node.myY - 2][node.myX] == UNVISITED || myMaze[node.myY - 2][node.myX] == EXIT_NODE) {
                neighbors.add(new Node(node.myX, node.myY - 2));
            }
        }
        return neighbors;
    }
    //checks if it has neighbors
    private boolean hasNeighbors(Node node) {
        int neighbors = 0;
        if(myMaze[node.myY][node.myX - 1] == WALL || myMaze[node.myY][node.myX - 1] == DOOR) { //left check
            if (myMaze[node.myY][node.myX - 2] == UNVISITED || myMaze[node.myY][node.myX - 2] == EXIT_NODE) {
                neighbors++;
            }

        } if(myMaze[node.myY][node.myX + 1] == WALL || myMaze[node.myY][node.myX + 1] == DOOR) { //right check
            if (myMaze[node.myY][node.myX + 2] == UNVISITED || myMaze[node.myY][node.myX + 2] == EXIT_NODE) {
                neighbors++;
            }

        } if(myMaze[node.myY + 1][node.myX] == WALL || myMaze[node.myY + 1][node.myX] == DOOR) { //top check
            if (myMaze[node.myY + 2][node.myX] == UNVISITED) {
                neighbors++;
            }
        } if(myMaze[node.myY - 1][node.myX] == WALL || myMaze[node.myY - 1][node.myX] == DOOR) { //bottom check
            if (myMaze[node.myY - 2][node.myX] == UNVISITED || myMaze[node.myY - 2][node.myX] == EXIT_NODE) {
                neighbors++;
            }
        }
        return (neighbors != 0);
    }
    //0 = not visited,   1 = wall,     2 = visited,  3 = no wall,  4 = exitNode, 6 = final path
    //7 = barrier,       8 = entrance, 9 = exit
    public void display() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < myHeight; i++) {
            for (int j = 0; j < myWidth; j++) {
                sb.append(myMaze[i][j] == WALL ? "*" : myMaze[i][j] == BARRIER ? "#"
                        : myMaze[i][j] == UNVISITED || myMaze[i][j] == ENTRANCE || myMaze[i][j] == EXIT || myMaze[i][j] == DOOR ? " "
                        : myMaze[i][j] == VISITED ? "" +
                        "X"
                        : myMaze[i][j] == FINAL_PATH ? "." : myMaze[i][j]);
                sb.append("  ");
            }
            sb.append("\n");
        }
    }

    public int getHeight() {
        return myHeight;
    }

    public int getMyWidth() {
        return myWidth;
    }

    public int[][] array() {
        return myMaze;
    }
}