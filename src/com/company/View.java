package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class View extends JFrame implements ActionListener{
    private JPanel viewPanelNorth;
    private JPanel viewPanelSouth;
    private JPanel viewPanelCenter;
    private int myWidth;
    private int myLength;
    Door myDoor = new Door();
    JButton doorKnob = new JButton();

    public View(int height, int width) {
//        myWidth = width;
//        myLength = length;
//        setSize(myWidth,myLength);
//        setTitle("Movie Trivia Maze");
//        setLocationRelativeTo(null);
//
//        //create items
//        var helpButton = new JButton("Help");
//        var exitButton = new JButton("Quit");
//        var saveButton = new JButton("Save Not Working");
//        JLabel fontLabel = new JLabel("Font");
//
//        Font bigText = fontLabel.getFont().deriveFont(Font.PLAIN, 30f);
//        helpButton.setFont(bigText);
//        exitButton.setFont(bigText);
//        saveButton.setFont(bigText);
//
//        viewPanelNorth = new JPanel();
//        viewPanelCenter = new JPanel();
//        viewPanelSouth = new JPanel();
//
//
//        //add buttons and text input
//        viewPanelNorth.add(helpButton);
//        viewPanelSouth.add(exitButton);
//        viewPanelSouth.add(saveButton);
//
//        //add panel to frame
//        add(viewPanelNorth, BorderLayout.NORTH);
//        add(viewPanelCenter, BorderLayout.CENTER);
//        add(viewPanelSouth, BorderLayout.SOUTH);
//
//        //create button action
//        var helpAction = new View.HelpAction();
//        var exitAction = new View.ExitAction();
//        var saveAction = new View.SaveAction();
//
//        //associate action with button
//        helpButton.addActionListener(helpAction);
//        exitButton.addActionListener(exitAction);
//        saveButton.addActionListener(saveAction);
//
//        this.getContentPane().add(viewPanelCenter);
//        this.getContentPane().add(viewPanelNorth);
//        this.getContentPane().add(viewPanelCenter);
        Maze myMaze = new Maze(4, 4);
        int myUY = 0;
        int myUX = 0;
        int buttons[][] = myMaze.array();
        int userDisplay[][] = new int[3][3];
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons.length; j++) {
                if (buttons[i][j] == -1) {
                    myUY = i;
                    myUX = j;
                }
            }
        }
        for (int i = 0; i < userDisplay.length; i++) {
            for (int j = 0; j < userDisplay.length; j++) {
                userDisplay[i][j] = buttons[myUX-1+i][myUY-1+j];
            }
        }


        JPanel rooms = new JPanel();
        JPanel layout = new JPanel();
        JPanel select = new JPanel();
        rooms.setLayout(new GridLayout(3, 3));
        for(int[] r : userDisplay){
//            JButton myRoom = new JButton();
//            myRoom.addActionListener(this);
//            buttons[i] = myRoom;

            //Room myRoom = new Room();
            for (int c : r) {
                JPanel myRoom = new JPanel(new FlowLayout());
                //JButton doorKnob = new JButton();
                doorKnob.addActionListener(this);
                if (c == 7 || c == 1){
                    myRoom.setBackground(Color.BLACK);
                }
                else if(c == 8 || c == 9 || c == 0){
                    myRoom.setBackground(Color.WHITE);
                }
                else if (c == 3){
                    myRoom.setBackground(Color.red);
                    myRoom.add(doorKnob);
                }
                else if (c == -1){
                    myRoom.setBackground(Color.yellow);
                }
                else{
                    myRoom.setBackground(Color.BLUE);
                }
                JButton leftBtn = new JButton();
//            leftBtn.addActionListener(this);
//            rightBtn.addActionListener(this);
//            topBtn.addActionListener(this);
//            botBtn.addActionListener(this);
//            myRoom.add(leftBtn);
//            buttons[i] = leftBtn;
                rooms.add(myRoom);
            }
        }
        rooms.setPreferredSize(new Dimension(500, 400));
        JButton help = new JButton("Help");
        JButton exit = new JButton("Exit");
        select.add(help);
        select.add(exit);
//        help.addActionListener(this);
//        exit.addActionListener(this);
        layout.add(rooms);

        this.getContentPane().add(layout);
        this.getContentPane().add(select, BorderLayout.EAST);

        this.pack();
        setVisible(true);

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

    public void displayRoom(){
        Door myDoor = new Door();
        String question = myDoor.getQuestion();
        String answer = myDoor.getAnswer();
        Object[] possibleAnswers = myDoor.getAnswerSet();
        Random text = new Random();
        int n = text.nextInt(possibleAnswers.length - 1);

        Object selectedValue = JOptionPane.showInputDialog(null,
                question, "Room",
                JOptionPane.INFORMATION_MESSAGE, null,
                possibleAnswers, possibleAnswers[n]);

        System.out.println(question);
        System.out.println(myDoor.isResult(answer,(String) selectedValue));
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == doorKnob){
            displayRoom();
        }
    }

    private class HelpAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            Help myHelp = new Help();
        }
    }

    private class SaveAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            System.out.println("SAVE");
        }
    }

    private class ExitAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            dispose();
        }
    }
}
