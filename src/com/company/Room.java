package com.company;


import javax.swing.*;
import java.awt.*;

public class Room {


     public Room(){
         JFrame myFrame = new JFrame();
         JPanel myRoom = new JPanel();
         myRoom.setPreferredSize(new Dimension(500, 500));
         JButton leftBtn = new JButton();
         JButton rightBtn = new JButton();
         JButton topBtn = new JButton();
         JButton botBtn = new JButton();
         myRoom.add(leftBtn);
         leftBtn.setBounds(0, -100, 50, 50);
//         myRoom.add(rightBtn);
//         rightBtn.setLocation(0, 250);
//         myRoom.add(topBtn);
//         topBtn.setLocation(250, 0);
//         myRoom.add(botBtn);
//         botBtn.setLocation(0, -250);
         myFrame.getContentPane().add(myRoom);
         myFrame.pack();
         myFrame.setVisible(true);
     }


}
