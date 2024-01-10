package com.AbhiQuizApp.quizApp.Constants;

public enum Category {
    JAVA(0),
    PYTHON(1),
    CPP(2);

    private int value;

    public int getValue() {
        return value;
    }

    Category(int value) {
        this.value = value;
    }
}
