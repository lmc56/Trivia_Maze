package com.company;
import java.sql.*;
public class Room {

    int num;
    private String question;
    private String answer;
    boolean result;

    public Room(){

    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isResult() {
        return result;
    }
}
