package com.company;

public class Controller {
    public void move(int theDirection, int [][] theMaze) {
        if(theDirection==0){
            this.moveUp(theMaze);
        }
        else if (theDirection==1){
            this.moveDown(theMaze);
        }
        else if (theDirection==2){
            this.moveLeft(theMaze);
        }
        else if (theDirection==3){
            this.moveRight(theMaze);
        }
        else{
            System.out.println("not a direction");
        }
    }

    public void save(){
        //model call for serialization
    }

    public void load(){
        //model call for deserialization
    }

    public void getMaze(){
        //movement.array();
    }

    public void checkLock(){
        //movement.isLocked();
    }

    public void getQuestion(int [][] theMaze){
        //may be done in view
    }

    public void setMove(){
        //movement.setCanMove();
    }

    public void help(){
        //model class to display help
    }

    public void findPlayer(){
        //Movement.findUser();
    }

    private void moveUp(int [][] theMaze){
        //make conversions for model call here
        //model.Movement(0,1, maze);
    }

    private void moveDown(int [][] theMaze){
        //make conversions for model call here
        //model.Movement(0,1, maze);

    }

    private void moveLeft(int [][] theMaze){
        //make conversions for model call here
        //model.Movement(0,1, maze);
    }

    private void moveRight(int [][] theMaze){
        //make conversions for model call here
        //model.Movement(0,1, maze);
    }
}


