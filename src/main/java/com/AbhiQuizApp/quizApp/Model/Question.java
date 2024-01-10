package com.AbhiQuizApp.quizApp.Model;


import com.AbhiQuizApp.quizApp.Constants.Category;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionText;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question", fetch = FetchType.EAGER)
    private List<Option> options = new java.util.ArrayList<>();

    @OneToOne
    private Option correctOption;

    private Category category;

    // Getters and setters

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public Option getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(Option correctOption) {
        this.correctOption = correctOption;
    }

    public com.AbhiQuizApp.quizApp.Constants.Category getCategory() {
        return category;
    }

    public void setCategory(com.AbhiQuizApp.quizApp.Constants.Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionText='" + questionText + '\'' +
                ", options=" + options +
                ", correctOption=" + correctOption +
                ", category=" + category +
                '}';
    }
}