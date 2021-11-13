package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private JPanel menuPanelNorth;
    private JPanel menuPanelSouth;
    private JPanel menuPanelCenter;
    private JPanel menuPanelCenterC1;
    private JPanel menuPanelCenterC2;
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
        setLabel = new JLabel("", SwingConstants.CENTER);
        setLabel.setText("Set size of Maze: ");
        var exitButton = new JButton("Quit");
        textFieldSize.setColumns(10);
        Font bigText = setLabel.getFont().deriveFont(Font.PLAIN, 30f);
        setLabel.setFont(bigText);
        exitButton.setFont(bigText);
        startButton.setFont(bigText);
        helpButton.setFont(bigText);
        textFieldSize.setFont(bigText);

        menuPanelNorth = new JPanel();
        menuPanelCenter = new JPanel();
        menuPanelCenterC1 = new JPanel();
        menuPanelCenterC2 = new JPanel();
        menuPanelSouth = new JPanel();
        menuPanelCenter.setLayout(new GridBagLayout());
        //menuPanelCenterC1.setLayout(new GridLayout(3,1));

        //textFieldSize.setColumns(10);

        //add buttons and text input
        menuPanelNorth.add(helpButton);
       // menuPanelCenter.add(new JLabel("Set size of Maze: ", SwingConstants.RIGHT));
        menuPanelCenterC1.add(setLabel, SwingConstants.CENTER);
        menuPanelCenterC2.add(textFieldSize, SwingConstants.CENTER);
        menuPanelCenter.add(menuPanelCenterC1, new GridBagConstraints());
        menuPanelCenter.add(menuPanelCenterC2, new GridBagConstraints());
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

        @Override
        public void actionPerformed(ActionEvent event) {
            size = textFieldSize.getText();
            try {
                mazeSize = Integer.parseInt(size);
                Maze myMaze = new Maze(mazeSize,mazeSize);
                //setVisible(false);
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
