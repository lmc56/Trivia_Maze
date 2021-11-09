import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Stack;

public class Maze {

    private class Node{
        public int x;
        public int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Node(Node node) {
            this.x = node.x;
            this.y = node.y;
        }
    }
    private int height;
    private int width;
    private int[][] maze;
    private Random rand = new Random();
    private Stack<Node> stack = new Stack<>();
    private Stack<Node> chosen = new Stack<>();
    private ArrayList<Node> path = new ArrayList<>();
    private Node lastNode;

    public Maze(int height, int width) {
        this.width = width * 2 + 1;
        this.height = height * 2 + 1;

        buildGraph();
        buildMaze();
        display();

        solveMaze();
        display();
    }

    public void buildGraph() {
        maze = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                maze[i][j] = 0;
            }
        }
        for (int i = 0; i < width; i++) {
            maze[0][i] = 7;
            maze[height - 1][i] = 7;
        }

        for (int i = 1; i < height - 1; i++) {
            for (int j = 0; j < width; j++) {
                if (j % 2 == 0) {
                    maze[i][j] = 1;
                }
                if (i % 2 == 0) {
                    maze[i][j] = 1;
                }
                if (j == 0 || j == width-1) {
                    maze[i][j] = 7;
                }
            }
        }
        maze[0][1] = 8;
        maze[height - 1][width - 2] = 9;
        maze[height - 2][width - 2] = 4; //exitNode
    }
    //0 = not visited,   1 = wall,     2 = visited,  3 = no wall,  4= exitNode
    //7 = barrier,     8 = entrance, 9 = exit,
    private void connectNodes(Node next, Node tempNode) {
        int dx = tempNode.x - next.x;
        int dy = tempNode.y - next.y;
        if(dy != 0) {
            if(dy > 0) {
                maze[next.y + 1][next.x] = 3;
            }
            if(dy < 0) {
                maze[next.y - 1][next.x] = 3;
            }
        }
        if(dx != 0) {
            if(dx > 0) {
                maze[next.y][next.x + 1] = 3;
            }
            if(dx < 0) {
                maze[next.y][next.x - 1] = 3;
            }
        }
    }

    public void buildMaze() {
        stack.push(new Node(1,1));
        path.add(new Node(1,1));
        boolean solved = false;
        while (!stack.empty()) {
            Node next = stack.pop();

            if (maze[next.y][next.x] == 4) {
                solved = true;
                path.add(next);
            }
            if (solved == false) {
                path.add(next);
            }

            if (maze[next.y][next.x] != 4) {
                maze[next.y][next.x] = 2;
            }
            if (hasNeighbors(next)) {

                ArrayList<Node> neighbors = findNeighbors(next);
                int randomSelection = rand.nextInt(neighbors.size());

                Node tempNode = new Node(neighbors.remove(randomSelection)); //x=1,y=3
                connectNodes(next, tempNode);

                while(!neighbors.isEmpty()) {
                    randomSelection = rand.nextInt(neighbors.size());
                    Node stackNode = neighbors.remove(randomSelection);
                            if (!(maze[stackNode.y][stackNode.x] == 2) || !(maze[stackNode.y][stackNode.x] == 4)) {
                                stack.push(stackNode);

                                maze[stackNode.y][stackNode.x] = 2;
                                connectNodes(next,stackNode);
                            }
                }
                stack.push(tempNode);
                display();
            }
        }
    }

    public void solveMaze() {
        System.out.println("Solving Maze... ");
        maze[height - 2][width - 2] = 4; //exitNode

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (maze[i][j] == 2) {
                    maze[i][j] = 0;
                }
            }
        }

        int sol[][] = new int[height][width];
        if(solveMazeRecursive(maze, 1, 1, sol) == false) {
            System.out.println("solution does not exist");
            return;
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                if(sol[i][j] == 6) {
                    maze[i][j] = 6;
                }
            }
        }

    }

    private boolean isSafe(int maze[][], int y, int x) {
        if ((maze[y][x] == 0) || (maze[y][x] == 3)) {
            return true;
        } else return false;
    }

    public boolean solveMazeRecursive(int maze[][], int x, int y, int sol[][]) {
        if(x == width - 2 && y == height - 2 && maze[y][x] == 4) {
            sol[x][y] = 6;
            return true;
        }
        if (isSafe(maze, x, y) == true) {
            if (sol[x][y] == 6) {
                return false;
            }

            sol[x][y] = 6;

            if (solveMazeRecursive(maze, x + 1, y, sol))
                return true;
            if (solveMazeRecursive(maze, x, y + 1, sol))
                return true;
            if (solveMazeRecursive(maze, x - 1, y, sol))
                return true;
            if (solveMazeRecursive(maze, x, y - 1, sol))
                return true;

            sol[x][y] = 0;
            return false;
        }
        return false;
    }
    //find neighbor method
    private ArrayList<Node> findNeighbors(Node node) {
        ArrayList<Node> neighbors = new ArrayList<>();
        if(maze[node.y][node.x - 1] == 1 || maze[node.y][node.x - 1] == 3) { //left check
            if (maze[node.y][node.x - 2] == 0 || maze[node.y][node.x - 2] == 4) {
                neighbors.add(new Node(node.x - 2, node.y));
            }
        } if(maze[node.y][node.x + 1] == 1 || maze[node.y][node.x + 1] == 3) { //right check
            if (maze[node.y][node.x + 2] == 0 || maze[node.y][node.x + 2] == 4) {
                neighbors.add(new Node(node.x + 2, node.y));
            }
        } if(maze[node.y + 1][node.x] == 1 || maze[node.y + 1][node.x] == 3) { //bottom check
            if (maze[node.y + 2][node.x] == 0 || maze[node.y + 2][node.x] == 4) {
                neighbors.add(new Node(node.x, node.y + 2));
            }

        } if(maze[node.y - 1][node.x] == 1 || maze[node.y - 1][node.x] == 3) { //top check
            if (maze[node.y - 2][node.x] == 0 || maze[node.y - 2][node.x] == 4) {
                neighbors.add(new Node(node.x, node.y - 2));
            }
        }
        return neighbors;
    }
    //checks if it has neighbors
    private boolean hasNeighbors(Node node) {
        int neighbors = 0;
        if(maze[node.y][node.x - 1] == 1 || maze[node.y][node.x - 1] == 3) { //left check
            if (maze[node.y][node.x - 2] == 0 || maze[node.y][node.x - 2] == 4) {
                neighbors++;
            }

        } if(maze[node.y][node.x + 1] == 1 || maze[node.y][node.x + 1] == 3) { //right check
            if (maze[node.y][node.x + 2] == 0 || maze[node.y][node.x + 2] == 4) {
                neighbors++;
            }

        } if(maze[node.y + 1][node.x] == 1 || maze[node.y + 1][node.x] == 3) { //top check
            if (maze[node.y + 2][node.x] == 0) {
                neighbors++;
            }

        } if(maze[node.y - 1][node.x] == 1 || maze[node.y - 1][node.x] == 3) { //bottom check
            if (maze[node.y - 2][node.x] == 0 || maze[node.y - 2][node.x] == 4) {
                neighbors++;
            }
        }
        return (neighbors != 0);
    }
    //0 = not visited,   1 = wall,     2 = visited,  3 = no wall,  4 = exitNode, 6 = final path
    //7 = barrier,       8 = entrance, 9 = exit
    public void display() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sb.append(maze[i][j] == 1 ? "*" : maze[i][j] == 7 ? "#"
                        : maze[i][j] == 0 || maze[i][j] == 8 || maze[i][j] == 9 || maze[i][j] == 3 ? " "
                        : maze[i][j] == 2 ? "" +
                        "X"
                        : maze[i][j] == 6 ? "." : maze[i][j]);
                sb.append("  ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
