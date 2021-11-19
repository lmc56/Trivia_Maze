package com.company;
import java.sql.*;
import java.util.Scanner;

public class Room {

    Scanner in = new Scanner(System.in);
    String url = "jdbc:sqlite:C:/Users/andre/Trivia_Maze/src/com/company/new_file";


    public Room(){
        String question = "";
        String answer = "";
        try{
            Connection con = DriverManager.getConnection(url);
            Statement statement = con.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from QUESTIONS ORDER BY random() LIMIT 1");
            question = resultSet.getString(1);
            answer = resultSet.getString(2);
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }

        System.out.println(question);
        System.out.println(isResult(answer));
    }

    public boolean isResult(String answer) {
        String myAnswer = in.nextLine();

        return myAnswer.equals(answer);
    }
}
