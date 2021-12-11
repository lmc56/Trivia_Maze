package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

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
        var helpButton = new JButton("Help");
        var exitButton = new JButton("Quit");
        var saveButton = new JButton("Save Not Working");
        JLabel fontLabel = new JLabel("Font");

        Font bigText = fontLabel.getFont().deriveFont(Font.PLAIN, 30f);
        helpButton.setFont(bigText);
        exitButton.setFont(bigText);
        saveButton.setFont(bigText);

        viewPanelNorth = new JPanel();
        viewPanelCenter = new JPanel();
        viewPanelSouth = new JPanel();


        //add buttons and text input
        viewPanelNorth.add(helpButton);
        viewPanelSouth.add(exitButton);
        viewPanelSouth.add(saveButton);

        //add panel to frame
        add(viewPanelNorth, BorderLayout.NORTH);
        add(viewPanelCenter, BorderLayout.CENTER);
        add(viewPanelSouth, BorderLayout.SOUTH);

        //create button action
        var helpAction = new View.HelpAction();
        var exitAction = new View.ExitAction();
        var saveAction = new View.SaveAction();

        //associate action with button
        helpButton.addActionListener(helpAction);
        exitButton.addActionListener(exitAction);
        saveButton.addActionListener(saveAction);

        setVisible(true);

    }

    public void viewRoom(){
        String question;
        String answer;
        String a1;
        String a2;

        //Code obtained from javadoc oracle
        // (https://docs.oracle.com/javase/7/docs/api/javax/swing/JOptionPane.html)
        Object[] possibleAnswers = getAnswerSet(new String[]{answer, a1, a2}, answer);
        Random text = new Random();
        int n = text.nextInt(possibleAnswers.length - 1);

        Object selectedValue = JOptionPane.showInputDialog(null,
                question, "Room",
                JOptionPane.INFORMATION_MESSAGE, null,
                possibleAnswers, possibleAnswers[n]);

        System.out.println(question);
        System.out.println(isResult(answer,(String) selectedValue));
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
