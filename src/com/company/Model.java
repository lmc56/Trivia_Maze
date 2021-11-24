package com.company;

public class Model {

    public Model(int mazeSize, int width, int length){

        //start view
        View myView = new View(mazeSize, width, length);
        //start room
        Door basic = new Door();
    }
}
