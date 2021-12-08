package com.company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Help extends JFrame {
    private JPanel myMenuPanelNorth;
    private JPanel myMenuPanelSouth;
    private JPanel myMenuPanelCenter;
    private JLabel mySetLabel;

    private static final int WIDTH = 500;
    private static final int LENGTH = 300;
    public Help(){
        setSize(WIDTH,LENGTH);
        setTitle("Help");
        setLocationRelativeTo(null);

        //create items
        var howToPlayButton = new JButton("How To Play");
        var controlsButton = new JButton("Controls");
        var exitButton = new JButton("Exit");
        mySetLabel = new JLabel("", SwingConstants.CENTER);
        mySetLabel.setText("<html>Open doors to move around the maze by answering <br /> movie trivia questions " +
                "correctly. You have three lives <br /> so don't get more than three questions wrong!</html>");

        myMenuPanelNorth = new JPanel();
        myMenuPanelCenter = new JPanel();
        myMenuPanelSouth = new JPanel();
        myMenuPanelCenter.setLayout(new GridLayout(1,0));

        //add buttons and text input
        myMenuPanelNorth.add(howToPlayButton);
        myMenuPanelNorth.add(controlsButton);
        myMenuPanelCenter.add(mySetLabel);
        myMenuPanelSouth.add(exitButton);

        //add panel to frame
        add(myMenuPanelNorth, BorderLayout.NORTH);
        add(myMenuPanelCenter, BorderLayout.CENTER);
        add(myMenuPanelSouth, BorderLayout.SOUTH);

        //create button action
        var howToAction = new HowToAction();
        var controlsAction = new ControlsAction();
        var exitAction = new ExitAction();

        //associate action with button
        howToPlayButton.addActionListener(howToAction);
        controlsButton.addActionListener(controlsAction);
        exitButton.addActionListener(exitAction);

        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void setHelpHowTo(){

        mySetLabel.setText("<html>Open doors to move around the maze by answering <br /> movie trivia questions " +
                "correctly. You have three lives <br /> so don't get more than three questions wrong!</html>");

    }

    public void setHelpControls(){

        mySetLabel.setText("Use the arrow keys to move left, right, up, and down around the maze.");

    }

    private class HowToAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            setHelpHowTo();
        }
    }

    private class ControlsAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            setHelpControls();
        }
    }

    private class ExitAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            dispose();
        }
    }

}
