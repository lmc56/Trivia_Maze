package com.company;
import java.sql.*;
public class Room {

    int num;
    private String question;
    private String answer;
    boolean result;
    String url = "jdbc:sqlite:C:/Users/andre/Trivia_Maze/src/com/company/new_file";

    public Room(){

    }

    public String getQuestion() {
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

        return question;
    }

    public String getAnswer() {

        return answer;
    }

    public boolean isResult() {

        return false;
    }
}
