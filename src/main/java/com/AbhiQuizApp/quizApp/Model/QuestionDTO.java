package com.AbhiQuizApp.quizApp.Model;

public class QuestionDTO {
    private String questionText;
    private String correctOptionText;

    // Constructors, getters, and setters

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getCorrectOptionText() {
        return correctOptionText;
    }

    public void setCorrectOptionText(String correctOptionText) {
        this.correctOptionText = correctOptionText;
    }
}
