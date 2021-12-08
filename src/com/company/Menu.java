package com.company;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements java.io.Serializable{
    private JPanel myMenuPanelNorth;
    private JPanel myMenuPanelSouth;
    private JPanel myMenuPanelCenter;
    private JPanel myMenuPanelCenterC1;
    private JPanel myMenuPanelCenterC2;
    private JTextField myTextFieldSize;
    private JLabel mySetLabel;
    private int myWidth;
    private int myLength;
    private final Menu THIS_CLASS = this;
    private boolean myIsLoad = false;

    public Menu(){
        if(!myIsLoad) {
            myWidth = 1920;
            myLength = 1050;
        }
        setSize(myWidth,myLength);
        setTitle("Movie Trivia Maze");
        setLocationRelativeTo(null);

        //create items
        var helpButton = new JButton("Help");
        var smallButton = new JButton("Small");
        var largeButton = new JButton("Large");
        JLabel sizeLabel = new JLabel("Set Screen Size: ");
        myTextFieldSize = new JTextField();
        var startButton = new JButton("Start");
        mySetLabel = new JLabel("", SwingConstants.CENTER);
        mySetLabel.setText("Set size of Maze with a Number from 4 to 20: ");
        var saveButton = new JButton("Save");
        var loadButton = new JButton("Load");
        var exitButton = new JButton("Quit");
        myTextFieldSize.setColumns(10);
        Font bigText = mySetLabel.getFont().deriveFont(Font.PLAIN, 30f);
        mySetLabel.setFont(bigText);
        saveButton.setFont(bigText);
        loadButton.setFont(bigText);
        exitButton.setFont(bigText);
        startButton.setFont(bigText);
        helpButton.setFont(bigText);
        smallButton.setFont(bigText);
        largeButton.setFont(bigText);
        sizeLabel.setFont(bigText);
        myTextFieldSize.setFont(bigText);

        myMenuPanelNorth = new JPanel();
        myMenuPanelCenter = new JPanel();
        myMenuPanelCenterC1 = new JPanel();
        myMenuPanelCenterC2 = new JPanel();
        myMenuPanelSouth = new JPanel();
        myMenuPanelCenter.setLayout(new GridBagLayout());

        //add buttons and text input
        myMenuPanelNorth.add(helpButton);
        myMenuPanelNorth.add(sizeLabel);
        myMenuPanelNorth.add(smallButton);
        myMenuPanelNorth.add(largeButton);
        myMenuPanelCenterC1.add(mySetLabel, SwingConstants.CENTER);
        myMenuPanelCenterC2.add(myTextFieldSize, SwingConstants.CENTER);
        myMenuPanelCenter.add(myMenuPanelCenterC1, new GridBagConstraints());
        myMenuPanelCenter.add(myMenuPanelCenterC2, new GridBagConstraints());
        myMenuPanelSouth.add(startButton);
        myMenuPanelSouth.add(saveButton);
        myMenuPanelSouth.add(loadButton);
        myMenuPanelSouth.add(exitButton);

        //add panel to frame
        add(myMenuPanelNorth, BorderLayout.NORTH);
        add(myMenuPanelCenter, BorderLayout.CENTER);
        add(myMenuPanelSouth, BorderLayout.SOUTH);

        //create button action
        var helpAction = new HelpAction();
        var smallAction = new SmallAction();
        var largeAction = new LargeAction();
        var startAction = new StartAction();
        var saveAction = new SaveAction();
        var loadAction = new LoadAction();
        var exitAction = new ExitAction();

        //associate action with button
        helpButton.addActionListener(helpAction);
        smallButton.addActionListener(smallAction);
        largeButton.addActionListener(largeAction);
        startButton.addActionListener(startAction);
        saveButton.addActionListener(saveAction);
        loadButton.addActionListener(loadAction);
        exitButton.addActionListener(exitAction);

        setVisible(true);
    }


    public void menuSizeError(){
        mySetLabel.setText("Error: Set size of Maze with a Number from 4 to 20: ");
    }

    public void setSizeCall(int width, int length){
        myWidth = width;
        myLength = length;
        setSize(myWidth, myLength);
        setLocationRelativeTo(null);
    }

    public void setCenterSmall(){
        Font smallText = mySetLabel.getFont().deriveFont(Font.PLAIN, 25f);
        mySetLabel.setFont(smallText);
        myTextFieldSize.setColumns(5);
    }

    public void setCenterLarge(){
        Font largeText = mySetLabel.getFont().deriveFont(Font.PLAIN, 30f);
        mySetLabel.setFont(largeText);
        myTextFieldSize.setColumns(10);
    }

    public int getWidthFrame(){
        return myWidth;
    }

    public int getLengthFrame(){
        return myLength;
    }

    public Menu getMyClass(){
        myIsLoad = true;
        return THIS_CLASS;
    }

    private class HelpAction implements ActionListener, Serializable {

        @Override
        public void actionPerformed(ActionEvent event) {
            Help myHelp = new Help();
        }
    }

    private class SmallAction implements ActionListener, Serializable {

        @Override
        public void actionPerformed(ActionEvent event) {
            setSizeCall(800,790);
            setCenterSmall();
        }
    }

    private class LargeAction implements ActionListener, Serializable {

        @Override
        public void actionPerformed(ActionEvent event) {
            setSizeCall(1920,1050);
            setCenterLarge();
        }
    }

    private class StartAction implements ActionListener, Serializable {
        private int myMazeSize;
        private String mySize;

        @Override
        public void actionPerformed(ActionEvent event) {
            mySize = myTextFieldSize.getText();
            try {
                myMazeSize = Integer.parseInt(mySize);
                if(myMazeSize > 3 && myMazeSize < 21) {
                    Maze myMaze = new Maze(myMazeSize, myMazeSize);
                    myMaze.display();
                    dispose();
                    Model myModel = new Model(myMazeSize, getWidthFrame(), getLengthFrame());
                }
                else {
                    menuSizeError();
                }
            }
            catch(Exception ex) {
                menuSizeError();
            }
        }
    }

    private class SaveAction implements ActionListener, Serializable {

        @Override
        public void actionPerformed(ActionEvent event) {
            /*
            Menu myClass = getMyClass();
            System.out.println(myClass.myWidth);
            System.out.println(myClass.myLength);
            try {
                FileOutputStream fileOut = new FileOutputStream("D:/tcss 360/git_repos/Trivia_Maze/src/com/company/game.ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(myClass);
                out.close();
                fileOut.close();
                System.out.println("Serialized data is saved in game.ser");
            }
            catch (IOException i) {
                i.printStackTrace();
            }

             */
        }
    }

    private class LoadAction implements ActionListener, Serializable {

        @Override
        public void actionPerformed(ActionEvent event) {
            /*
            Menu newMenu;
            try {
                dispose();
                FileInputStream fileIn = new FileInputStream("D:/tcss 360/git_repos/Trivia_Maze/src/com/company/game.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                newMenu = (Menu) in.readObject();
                //System.out.println(newMenu.myWidth);
                in.close();
                fileIn.close();
                System.out.println("loaded");
                setVisible(true);
            } catch (IOException i) {
                i.printStackTrace();
                return;
            } catch (ClassNotFoundException c) {
                System.out.println("No Game found");
                c.printStackTrace();
                return;
            }
            */


        }


    }

    private class ExitAction implements ActionListener, Serializable {

        @Override
        public void actionPerformed(ActionEvent event) {
            dispose();
        }
    }
}
