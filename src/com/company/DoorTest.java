package com.company;

import static org.junit.jupiter.api.Assertions.*;

class DoorTest {
    Door testDoor = new Door();
    String testQuestion;
    String testAnswer;

    @org.junit.jupiter.api.Test
    void getQuestion() {
        assertEquals(testDoor.question,testDoor.getQuestion());
    }

    @org.junit.jupiter.api.Test
    void getAnswer() {
        assertEquals(testDoor.answer, testDoor.getAnswer());
    }

    @org.junit.jupiter.api.Test
    void getA1() {
        assertEquals(testDoor.a1, testDoor.getA1());
    }

    @org.junit.jupiter.api.Test
    void getA2() {
        assertEquals(testDoor.a2, testDoor.getA2());
    }

    @org.junit.jupiter.api.Test
    void isResult() {
    }

    @org.junit.jupiter.api.Test
    void getAnswerSet() {
    }
}