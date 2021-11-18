package com.company;
import java.sql.*;
import java.util.Scanner;

public class Room {

//    int num;
//    private String question;
//    private String answer;
//    boolean result;
    Scanner in = new Scanner(System.in);
    String url = "jdbc:sqlite:C:/Users/andre/Trivia_Maze/src/com/company/new_file";


    public Room(){
        getQuestion();
        getAnswer();
        System.out.println(isResult());
    }



    public void getQuestion() {
        String question = "";
        try{
            Connection con = DriverManager.getConnection(url);
            Statement statement = con.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from QUESTIONS");
            question = resultSet.getString(1);
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }

        System.out.println(question);
    }

    public String getAnswer() {
        String answer = "";

        try{
            Connection con = DriverManager.getConnection(url);
            Statement statement = con.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from QUESTIONS");
            answer = resultSet.getString(2);
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }

        return answer;
    }

    public boolean isResult() {
        String myAnswer = in.nextLine();

        if (myAnswer.equals(getAnswer()))
            return true;

        return false;
    }
}
