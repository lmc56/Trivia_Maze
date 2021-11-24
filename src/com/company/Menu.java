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
    private int myWidth = 1920;
    private int myLength = 1050;

    public Menu(){
        setSize(myWidth,myLength);
        setTitle("Movie Trivia Maze");
        setLocationRelativeTo(null);

        //create items
        var helpButton = new JButton("Help");
        var smallButton = new JButton("Small");
        var largeButton = new JButton("Large");
        JLabel sizeLabel = new JLabel("Set Screen Size: ");
        textFieldSize = new JTextField();
        var startButton = new JButton("Start");
        setLabel = new JLabel("", SwingConstants.CENTER);
        setLabel.setText("Set size of Maze from 4 to 20: ");
        var exitButton = new JButton("Quit");
        textFieldSize.setColumns(10);
        Font bigText = setLabel.getFont().deriveFont(Font.PLAIN, 30f);
        setLabel.setFont(bigText);
        exitButton.setFont(bigText);
        startButton.setFont(bigText);
        helpButton.setFont(bigText);
        smallButton.setFont(bigText);
        largeButton.setFont(bigText);
        sizeLabel.setFont(bigText);
        textFieldSize.setFont(bigText);

        menuPanelNorth = new JPanel();
        menuPanelCenter = new JPanel();
        menuPanelCenterC1 = new JPanel();
        menuPanelCenterC2 = new JPanel();
        menuPanelSouth = new JPanel();
        menuPanelCenter.setLayout(new GridBagLayout());

        //add buttons and text input
        menuPanelNorth.add(helpButton);
        menuPanelNorth.add(sizeLabel);
        menuPanelNorth.add(smallButton);
        menuPanelNorth.add(largeButton);
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
        var smallAction = new SmallAction();
        var largeAction = new LargeAction();
        var startAction = new StartAction();
        var exitAction = new ExitAction();

        //associate action with button
        helpButton.addActionListener(helpAction);
        smallButton.addActionListener(smallAction);
        largeButton.addActionListener(largeAction);
        startButton.addActionListener(startAction);
        exitButton.addActionListener(exitAction);

        setVisible(true);
    }

    public void MenuSizeError(){
        setLabel.setText("Error: Set size of Maze with Number 4 to 20: ");
    }

    public void setSizeCall(int width, int length){
        myWidth = width;
        myLength = length;
        setSize(myWidth, myLength);
        setLocationRelativeTo(null);
    }

    public void setCenterSmall(){
        Font smallText = setLabel.getFont().deriveFont(Font.PLAIN, 25f);
        setLabel.setFont(smallText);
        textFieldSize.setColumns(5);
    }

    public void setCenterLarge(){
        Font largeText = setLabel.getFont().deriveFont(Font.PLAIN, 30f);
        setLabel.setFont(largeText);
        textFieldSize.setColumns(10);
    }

    public int getWidthFrame(){
        return myWidth;
    }

    public int getLengthFrame(){
        return myLength;
    }

    private class HelpAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            Help myHelp = new Help();
        }
    }

    private class SmallAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            setSizeCall(800,790);
            setCenterSmall();
        }
    }

    private class LargeAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            setSizeCall(1920,1050);
            setCenterLarge();
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
                if(mazeSize > 3 && mazeSize < 21) {
                    Maze myMaze = new Maze(mazeSize, mazeSize);
                    myMaze.display();
                    dispose();
                    Model myModel = new Model(mazeSize, getWidthFrame(), getLengthFrame());
                }
                else {
                    MenuSizeError();
                }
            }
            catch(Exception ex) {
                MenuSizeError();
            }
        }
    }

    private class ExitAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            dispose();
        }
    }
}
