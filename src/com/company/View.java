package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private JPanel viewPanelNorth;
    private JPanel viewPanelSouth;
    private JPanel viewPanelCenter;
    private int myWidth;
    private int myLength;

    public View(int mazeSize, int width, int length) {
        myWidth = width;
        myLength = length;
        setSize(myWidth,myLength);
        setTitle("Movie Trivia Maze");
        setLocationRelativeTo(null);

        //create items
        var exitButton = new JButton("Quit");
        var saveButton = new JButton("Save Not Working");
        JLabel fontLabel = new JLabel("Font");

        Font bigText = fontLabel.getFont().deriveFont(Font.PLAIN, 30f);
        exitButton.setFont(bigText);
        saveButton.setFont(bigText);

        viewPanelNorth = new JPanel();
        viewPanelCenter = new JPanel();
        viewPanelSouth = new JPanel();


        //add buttons and text input
        viewPanelSouth.add(exitButton);
        viewPanelSouth.add(saveButton);

        //add panel to frame
        add(viewPanelNorth, BorderLayout.NORTH);
        add(viewPanelCenter, BorderLayout.CENTER);
        add(viewPanelSouth, BorderLayout.SOUTH);

        //create button action
        var exitAction = new View.ExitAction();
        var saveAction = new View.SaveAction();

        //associate action with button
        exitButton.addActionListener(exitAction);
        saveButton.addActionListener(saveAction);

        setVisible(true);

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
