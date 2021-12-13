package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Scanner;
import java.util.jar.JarEntry;

public class View extends JFrame implements ActionListener, Observer {

    private JPanel viewPanelNorth;
    private JPanel viewPanelSouth;
    private JPanel viewPanelCenter;
    private int myWidth;
    private int myLength;
    Door myDoor = new Door();

    int fullDisplay[][];
    boolean cheatMode = false;
    JPanel rooms;
    JPanel layout = new JPanel();
    JPanel select = new JPanel();

    public View(int height, int width) {

        Maze myMaze = new Maze(height, width);

        fullDisplay = myMaze.array();
        rooms = userView(fullDisplay);

        JButton help = new JButton("Help");
        help.setActionCommand("help");
        help.addActionListener(this);
        JButton exit = new JButton("Exit");
        exit.setActionCommand("exit");
        exit.addActionListener(this);
        JButton cheatBtn = new JButton("Cheat Mode");
        cheatBtn.setActionCommand("cheat");
        cheatBtn.addActionListener(this);
        select.add(help);
        select.add(exit);
        select.add(cheatBtn);
        layout.add(rooms);

        this.getContentPane().add(layout);
        this.getContentPane().add(select, BorderLayout.EAST);

        this.pack();
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }


    public JPanel userView(int[][] maze){
        int myUY = 0;
        int myUX = 0;

        int userDisplay[][] = new int[3][3];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                System.out.print(maze[i][j]);
                if (maze[i][j] == -1) {
                    myUY = j;
                    myUX = i;
                }
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(maze[myUX-1+i][myUY-1+j]);
                userDisplay[i][j] = maze[myUX-1+i][myUY-1+j];
            }
            System.out.println();
        }
        System.out.println();
        JPanel rooms = new JPanel();
        rooms.setLayout(new GridLayout(userDisplay.length, userDisplay.length));
        for(int i = 0; i < userDisplay.length; i++){

            for (int j = 0; j < userDisplay.length; j++) {
                JPanel myRoom = new JPanel(new GridBagLayout());
                int c = userDisplay[i][j];

                if (c == 7 || c == 1){
                    myRoom.setBackground(Color.BLACK);
                }
                else if(c == 8 || c == 9 || c == 0){
                    myRoom.setBackground(Color.WHITE);
                    if (c == 9) {
                        JButton exitKnob = new JButton("exit to win");
                        exitKnob.setActionCommand("win");
                        if (j-1 != 0) {
                            exitKnob.setPreferredSize(new Dimension(50,300));
                        }else {
                            exitKnob.setPreferredSize(new Dimension(300,50));
                        }
                        exitKnob.addActionListener(this);
                        myRoom.add(exitKnob);
                    }
                }
                else if (c == 3){
                    myRoom.setBackground(Color.red);
                    JButton doorKnobTemp = new JButton();
                    doorKnobTemp.setActionCommand((j-1)+" "+(i-1));
                    if (j-1 != 0) {
                        doorKnobTemp.setPreferredSize(new Dimension(50,300));
                    }else {
                        doorKnobTemp.setPreferredSize(new Dimension(300,50));
                    }

                    doorKnobTemp.addActionListener(this);
                    myRoom.add(doorKnobTemp, SwingConstants.CENTER);
                } else if (c == -3) {
                    myRoom.setBackground(Color.green);
                    JButton doorKnobTemp = new JButton();
                    doorKnobTemp.setActionCommand((j-1)+" "+(i-1));
                    if (j-1 != 0) {
                        doorKnobTemp.setPreferredSize(new Dimension(50,300));
                    }else {
                        doorKnobTemp.setPreferredSize(new Dimension(300,50));
                    }
                    doorKnobTemp.addActionListener(this);
                    myRoom.add(doorKnobTemp);
                }
                else if (c == -1){
                    myRoom.setBackground(Color.yellow);
                }
                else{
                    myRoom.setBackground(Color.BLUE);
                }

                rooms.add(myRoom);
            }
        }
        rooms.setPreferredSize(new Dimension(600, 600));
        setLocationRelativeTo(null);
        return rooms;
    }


    public void viewRoom(){
        String question;
        String answer;
        String a1;
        String a2;

        //Code obtained from javadoc oracle
        // (https://docs.oracle.com/javase/7/docs/api/javax/swing/JOptionPane.html)
//        Object[] possibleAnswers = getAnswerSet(new String[]{answer, a1, a2}, answer);
//        Random text = new Random();
//        int n = text.nextInt(possibleAnswers.length - 1);
//
//        Object selectedValue = JOptionPane.showInputDialog(null,
//                question, "Room",
//                JOptionPane.INFORMATION_MESSAGE, null,
//                possibleAnswers, possibleAnswers[n]);
//
//        System.out.println(question);
//        System.out.println(isResult(answer,(String) selectedValue));
    }

    public boolean displayRoom(){
        Door myDoor = new Door();
        String question = myDoor.getQuestion();
        String a1 = myDoor.getA1();
        String a2 = myDoor.getA2();
        String answer = "";
        if (cheatMode == true) {
            answer = "<html><font color=\"red\">" + myDoor.getAnswer() + "</font></html>";
        }
        else {
            answer = myDoor.getAnswer();
        }
        Object[] possibleAnswers = myDoor.getAnswerSet(answer, a1, a2);
        Random text = new Random();
        int n = text.nextInt(possibleAnswers.length - 1);

        Object selectedValue = JOptionPane.showInputDialog(null,
                question, "Room",
                JOptionPane.INFORMATION_MESSAGE, null,
                possibleAnswers, possibleAnswers[n]);

        System.out.println(question);
        System.out.println(myDoor.isResult(answer,(String) selectedValue));
        return myDoor.isResult(answer, (String) selectedValue);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "win") {
            dispose();
            JFrame newJFrame = new JFrame();
            JPanel newJPanel = new JPanel();
            JButton newJButton = new JButton("New game");
            var newGame = new NewGame();
            newJButton.addActionListener(newGame);

            JLabel newJLabel = new JLabel("You've won.");
            newJPanel.add(newJButton);
            newJPanel.add(newJLabel);
            newJFrame.getContentPane().add(newJPanel);
            newJFrame.pack();
            newJFrame.setSize(500, 300);
            newJFrame.setLocationRelativeTo(null);
            newJFrame.setVisible(true);
            newJFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            return;
        } else if(e.getActionCommand() == "help") {
            Help myHelp = new Help();
            return;
        } else if (e.getActionCommand() == "exit") {
            dispose();
            return;
        }
        else if (e.getActionCommand() == "cheat"){
            if(cheatMode == false){
                cheatMode = true;
                return;
            }
            else {
                cheatMode = false;
                return;
            }
        }

        String coordinates = e.getActionCommand();
        Scanner sc = new Scanner(coordinates);

        int x = sc.nextInt();
        int y = sc.nextInt();

        this.setVisible(false);
        try {
            Movement move = new Movement(x, y, fullDisplay);
            if (move.isLocked()) {
                //provide question
                //if question is answered correctly do this
                if (displayRoom()) {
                    fullDisplay = move.array();
                    JPanel updatedView = userView(move.array());
                    this.remove(layout);
                    layout = new JPanel();
                    layout.add(updatedView);
                    this.getContentPane().add(layout);
                    this.setVisible(true);
                    //if question is answered incorrectly, just display the answer is wrong.
                    return;
                }
                else{
                    JFrame wrong = new JFrame();
                    wrong.setLocationRelativeTo(null);
                    JPanel wrongP = new JPanel();
                    wrongP.setPreferredSize(new Dimension(400, 100));
                    JLabel wrongL = new JLabel("Wrong Answer");
                    wrongP.add(wrongL);
                    wrong.getContentPane().add(wrongP);
                    wrong.pack();
                    this.setVisible(true);
                    wrong.setVisible(true);
                    return;
                }
            } else if (!move.isLocked()) {
                fullDisplay = move.array();
                JPanel updatedView = userView(move.array());
                this.remove(layout);
                layout = new JPanel();
                layout.add(updatedView);
                this.getContentPane().add(layout);
                this.setVisible(true);
                return;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    private class HelpAction implements ActionListener, Serializable {

        @Override
        public void actionPerformed(ActionEvent event) {
            Help myHelp = new Help();
        }
    }

    private class SaveAction implements ActionListener, Serializable {

        @Override
        public void actionPerformed(ActionEvent event) {
            System.out.println("SAVE");
        }
    }

    private class NewGame implements ActionListener, Serializable {

        @Override
        public void actionPerformed(ActionEvent event) {
            dispose();
            Menu myMenu = new Menu();

        }
    }
}