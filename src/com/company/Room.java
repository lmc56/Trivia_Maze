package com.company;
import javax.swing.*;
import java.util.*;
import java.sql.*;

public class Room {

    String url = "jdbc:sqlite:C:/Users/andre/Trivia_Maze/src/com/company/new_file";


    public Room(){
        String question = "";
        String answer = "";
        String a1 = "";
        String a2 = "";

        try{
            Connection con = DriverManager.getConnection(url);
            Statement statement = con.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from QUESTIONS ORDER BY random() LIMIT 1");
            question = resultSet.getString(1);
            answer = resultSet.getString("ANSWER");
            a1 = resultSet.getString("A1");
            a2 = resultSet.getString("A2");

        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }

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

    public boolean isResult(String answer,String myAnswer) {
        return myAnswer.equals(answer);
    }

    public Object[] getAnswerSet(String[] ans, String a){
        if (a.equals("T") || a.equals("F")){
            ans = new String[]{"T", "F"};
            return ans;
        }

        List<String> strList = Arrays.asList(ans);
        Collections.shuffle(strList);
        ans = strList.toArray(new String[strList.size()]);

        return ans;
    }
}
