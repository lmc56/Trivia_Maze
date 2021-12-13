package com.company;

import javax.swing.*;
import java.sql.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Door {

    String url = "jdbc:sqlite:C:/Users/andre/Trivia_Maze/src/com/company/new_file";
    String question;
    String answer;
    String a1;
    String a2;

    public Door(){

        try{
            Connection con = DriverManager.getConnection("jdbc:sqlite:src/com/company/new_file");
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
//        Object[] possibleAnswers = getAnswerSet(new String[]{answer, a1, a2}, answer);
//        Random text = new Random();
//        int n = text.nextInt(possibleAnswers.length - 1);
//
//        Object selectedValue = JOptionPane.showInputDialog(null,
//                question, "Room",
//                JOptionPane.INFORMATION_MESSAGE, null,
//                possibleAnswers, possibleAnswers[n]);
//
//        System.out.println(question);
//        System.out.println(isResult(answer,(String) selectedValue));
    }

    public String getQuestion(){
        return question;
    }

    public String getAnswer(){
        return answer;
    }
    public String getA1(){
        return a1;
    }
    public String getA2(){
        return a2;
    }
    public boolean isResult(String answer,String myAnswer) {
        return myAnswer.equals(answer);
    }

    public Object[] getAnswerSet(String answer, String a1, String a2){
        String[] ans = new String[]{answer, a1, a2};
        if (answer.equals("T") || answer.equals("F")){
            ans = new String[]{"T", "F"};
            return ans;
        }

        List<String> strList = Arrays.asList(ans);
        Collections.shuffle(strList);
        ans = strList.toArray(new String[strList.size()]);

        return ans;
    }
}
