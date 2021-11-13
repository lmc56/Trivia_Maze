package com.company;

//import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private JPanel menuPanelNorth;
    private JPanel menuPanelSouth;
    private JPanel menuPanelCenter;
    private JTextField textFieldSize;
    private JLabel setLabel;
    private static final int myWidth = 1920;
    private static final int myLength = 1050;

    public Menu(){
        setSize(myWidth,myLength);

        //create items
        var helpButton = new JButton("Help");
        //var textFieldSize = new JTextField();
        textFieldSize = new JTextField();
        var startButton = new JButton("Start");
        setLabel = new JLabel("", SwingConstants.RIGHT);
        setLabel.setText("Set size of Maze: ");
        var exitButton = new JButton("Quit");

        menuPanelNorth = new JPanel();
        menuPanelCenter = new JPanel();
        menuPanelSouth = new JPanel();
        menuPanelCenter.setLayout(new GridLayout(1,1));

        //add buttons and text input
        menuPanelNorth.add(helpButton);
       // menuPanelCenter.add(new JLabel("Set size of Maze: ", SwingConstants.RIGHT));
        menuPanelCenter.add(setLabel);
        menuPanelCenter.add(textFieldSize);
        menuPanelSouth.add(startButton);
        menuPanelSouth.add(exitButton);

        //add panel to frame
        add(menuPanelNorth, BorderLayout.NORTH);
        add(menuPanelCenter, BorderLayout.CENTER);
        add(menuPanelSouth, BorderLayout.SOUTH);

        //create button action
        var helpAction = new HelpAction();
        //var startAction = new StartAction(textFieldSize.getText());
        var startAction = new StartAction();
        var exitAction = new ExitAction();

        //associate action with button
        helpButton.addActionListener(helpAction);
        startButton.addActionListener(startAction);
        exitButton.addActionListener(exitAction);
        //Int
        //startButton.addActionListener(event ->
              // new StartAction());
        //startButton.addActionListener(startAction);

        setVisible(true);

    }

    public void MenuSizeError(){
        setLabel.setText("Error: Set size of Maze with Number: ");
        //menuPanelCenter.add(new JLabel("Error: Set size of Maze with number: "));
    }


    private class HelpAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            Help myHelp = new Help();
        }
    }

    private class StartAction implements ActionListener {

        private int mazeSize;
        private String size;

        //public StartAction(String size){
            //test = size;

       // }

        @Override
        public void actionPerformed(ActionEvent event) {
            size = textFieldSize.getText();
            //mazeSize = Integer.parseInt(size);
            try {
                mazeSize = Integer.parseInt(size);
                Maze myMaze = new Maze(mazeSize,mazeSize);
                //System.out.println(mazeSize);
            }
            catch(Exception ex) {
                MenuSizeError();
            }

            System.out.println("maze size " + mazeSize);
            System.out.println("maze size 2" + size);
        }
    }

    private class ExitAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            dispose();
        }
    }
}
