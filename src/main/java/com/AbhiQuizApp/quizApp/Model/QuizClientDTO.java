package com.AbhiQuizApp.quizApp.Model;

import java.util.List;

public class QuizClientDTO {
    private String title;

    private List<Question> questions;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
