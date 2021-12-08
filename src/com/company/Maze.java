package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Maze {

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

    public Maze(int height1, int width1) {
        myWidth = width1 * 2 + 1;
        myHeight = height1 * 2 + 1;

        buildGraph();
        buildMaze();
        unvisit();
    }

    private void buildGraph() {
        myMaze = new int[myHeight][myWidth];
        for (int i = 0; i < myHeight; i++) {
            for (int j = 0; j < myWidth; j++) {
                myMaze[i][j] = 0;
            }
        }
        for (int i = 0; i < myWidth; i++) {
            myMaze[0][i] = 7;
            myMaze[myHeight - 1][i] = 7;
        }

        for (int i = 1; i < myHeight - 1; i++) {
            for (int j = 0; j < myWidth; j++) {
                if (j % 2 == 0) {
                    myMaze[i][j] = 1;
                }else if (i % 2 == 0) {
                    myMaze[i][j] = 1;
                }else if (j == 0 || j == myWidth-1) {
                    myMaze[i][j] = 7;
                }
            }
        }
        myMaze[0][1] = 8;
        myMaze[myHeight - 1][myWidth - 2] = 9;
        myMaze[myHeight - 2][myWidth - 2] = 4; //exitNode
    }
    //0 = not visited,   1 = wall,     2 = visited,  3 = door,  4= exitNode
    //7 = barrier,     8 = entrance, 9 = exit, -1 = user
    private void connectNodes(Node next1, Node tempNode1) { //used in build maze
        Node next = next1;
        Node tempNode = tempNode1;

        int dx = tempNode.myX - next.myX;
        int dy = tempNode.myY - next.myY;
        if(dy != 0) {
            if(dy > 0) {
                myMaze[next.myY + 1][next.myX] = 3;
            }
            if(dy < 0) {
                myMaze[next.myY - 1][next.myX] = 3;
            }
        }
        if(dx != 0) {
            if(dx > 0) {
                myMaze[next.myY][next.myX + 1] = 3;
            }
            if(dx < 0) {
                myMaze[next.myY][next.myX - 1] = 3;
            }
        }
    }

    private void buildMaze() {
        myStack.push(new Node(1,1));
        myPath.add(new Node(1,1));
        boolean solved = false;
        while (!myStack.empty()) {
            Node next = myStack.pop();

            if (myMaze[next.myY][next.myX] == 4) {
                solved = true;
                myPath.add(next);
            }
            if (solved == false) {
                myPath.add(next);
            }

            if (myMaze[next.myY][next.myX] != 4) {
                myMaze[next.myY][next.myX] = 2;
            }
            if (hasNeighbors(next)) {

                ArrayList<Node> neighbors = findNeighbors(next);
                int randomSelection = myRand.nextInt(neighbors.size());

                Node tempNode = new Node(neighbors.remove(randomSelection)); //x=1,y=3
                connectNodes(next, tempNode);

                while(!neighbors.isEmpty()) {
                    randomSelection = myRand.nextInt(neighbors.size());
                    Node stackNode = neighbors.remove(randomSelection);
                            if (!(myMaze[stackNode.myY][stackNode.myX] == 2) || !(myMaze[stackNode.myY][stackNode.myX] == 4)) {
                                myStack.push(stackNode);

                                myMaze[stackNode.myY][stackNode.myX] = 2;
                                connectNodes(next,stackNode);
                            }
                }
                myStack.push(tempNode);
            }
        }
        myMaze[1][1] = -1;
        myMaze[myHeight - 2][myWidth - 2] = 4;
    }

    private void unvisit() {
        for (int i = 0; i < myHeight; i++) {
            for (int j = 0; j < myWidth; j++) {
                if (myMaze[i][j] == 2) {
                    myMaze[i][j] = 0;
                }
            }
        }
    }
    //find neighbor method
    private ArrayList<Node> findNeighbors(Node node) {
        ArrayList<Node> neighbors = new ArrayList<>();
        if(myMaze[node.myY][node.myX - 1] == 1 || myMaze[node.myY][node.myX - 1] == 3) { //left check
            if (myMaze[node.myY][node.myX - 2] == 0 || myMaze[node.myY][node.myX - 2] == 4) {
                neighbors.add(new Node(node.myX - 2, node.myY));
            }
        } if(myMaze[node.myY][node.myX + 1] == 1 || myMaze[node.myY][node.myX + 1] == 3) { //right check
            if (myMaze[node.myY][node.myX + 2] == 0 || myMaze[node.myY][node.myX + 2] == 4) {
                neighbors.add(new Node(node.myX + 2, node.myY));
            }
        } if(myMaze[node.myY + 1][node.myX] == 1 || myMaze[node.myY + 1][node.myX] == 3) { //bottom check
            if (myMaze[node.myY + 2][node.myX] == 0 || myMaze[node.myY + 2][node.myX] == 4) {
                neighbors.add(new Node(node.myX, node.myY + 2));
            }

        } if(myMaze[node.myY - 1][node.myX] == 1 || myMaze[node.myY - 1][node.myX] == 3) { //top check
            if (myMaze[node.myY - 2][node.myX] == 0 || myMaze[node.myY - 2][node.myX] == 4) {
                neighbors.add(new Node(node.myX, node.myY - 2));
            }
        }
        return neighbors;
    }
    //checks if it has neighbors
    private boolean hasNeighbors(Node node1) {
        int neighbors = 0;
        if(myMaze[node1.myY][node1.myX - 1] == 1 || myMaze[node1.myY][node1.myX - 1] == 3) { //left check
            if (myMaze[node1.myY][node1.myX - 2] == 0 || myMaze[node1.myY][node1.myX - 2] == 4) {
                neighbors++;
            }

        } if(myMaze[node1.myY][node1.myX + 1] == 1 || myMaze[node1.myY][node1.myX + 1] == 3) { //right check
            if (myMaze[node1.myY][node1.myX + 2] == 0 || myMaze[node1.myY][node1.myX + 2] == 4) {
                neighbors++;
            }

        } if(myMaze[node1.myY + 1][node1.myX] == 1 || myMaze[node1.myY + 1][node1.myX] == 3) { //top check
            if (myMaze[node1.myY + 2][node1.myX] == 0) {
                neighbors++;
            }

        } if(myMaze[node1.myY - 1][node1.myX] == 1 || myMaze[node1.myY - 1][node1.myX] == 3) { //bottom check
            if (myMaze[node1.myY - 2][node1.myX] == 0 || myMaze[node1.myY - 2][node1.myX] == 4) {
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
                sb.append(myMaze[i][j] == 1 ? "*" : myMaze[i][j] == 7 ? "#"
                        : myMaze[i][j] == 0 || myMaze[i][j] == 8 || myMaze[i][j] == 9 || myMaze[i][j] == 3 ? " "
                        : myMaze[i][j] == 2 ? "" +
                        "X"
                        : myMaze[i][j] == 6 ? "." : myMaze[i][j]);
                sb.append("  ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public int[][] array() {
        return myMaze;
    }
}
