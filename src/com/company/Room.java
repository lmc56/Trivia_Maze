package com.company;
import javax.swing.*;
import java.sql.*;

public class Room {

    String url = "jdbc:sqlite:C:/Users/andre/Trivia_Maze/src/com/company/new_file";


    public Room(){
        String question = "";
        String answer = "";
        try{
            Connection con = DriverManager.getConnection(url);
            Statement statement = con.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from QUESTIONS ORDER BY random() LIMIT 1");
            question = resultSet.getString(1);
            answer = resultSet.getString("ANSWER");
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }

        //Code obtained from javadoc oracle
        // (https://docs.oracle.com/javase/7/docs/api/javax/swing/JOptionPane.html)
        Object[] possibleAnswers = { answer, "Second", "Third" };
        Object selectedValue = JOptionPane.showInputDialog(null,
                question, "Room",
                JOptionPane.INFORMATION_MESSAGE, null,
                possibleAnswers, possibleAnswers[0]);

        System.out.println(question);
        System.out.println(isResult(answer,(String) selectedValue));
    }

    public boolean isResult(String answer,String myAnswer) {
        return myAnswer.equals(answer);
    }
}
