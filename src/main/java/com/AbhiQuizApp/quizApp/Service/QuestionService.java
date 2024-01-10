package com.AbhiQuizApp.quizApp.Service;

import com.AbhiQuizApp.quizApp.Constants.Category;
import com.AbhiQuizApp.quizApp.Model.Option;
import com.AbhiQuizApp.quizApp.Model.Question;
import com.AbhiQuizApp.quizApp.Repository.OptionRepository;
import com.AbhiQuizApp.quizApp.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// QuizService.java
@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private OptionRepository optionRepository;

    public List<Question> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        return questions;
    }

    private Category getRandomCategory() {
        Category[] categories = Category.values();
        int randomIndex = new Random().nextInt(categories.length);
        return categories[randomIndex];
    }

    public void insertDummyData() {
        // Inserting 10 dummy questions with options and correct options
        for (int i = 1; i <= 10; i++) {
            Question question = new Question();
            question.setQuestionText("Question " + i);

            List<Option> options = new ArrayList<>();
            for (char optionChar = 'A'; optionChar <= 'D'; optionChar++) {
                Option option = new Option();
                option.setOptionText("Option " + optionChar);
                option.setQuestion(question);
//                options.add(option);
                question.getOptions().add(option);
            }
            question.setCategory(getRandomCategory());

            // Set a random correct option
            int correctOptionIndex = new Random().nextInt(question.getOptions().size());
            question.setCorrectOption(question.getOptions().get(correctOptionIndex));


            questionRepository.save(question);
        }
    }

    public List<Question> getQuestionsByCategory(Category category) {
        return questionRepository.findByCategory(category);
    }

    public Question createQuestion(Question question) {
        // created and saved correct option
        Option toCreateCorrectOption = new Option();
        toCreateCorrectOption.setOptionText(question.getCorrectOption().getOptionText());
        Option savedCorrectOption = optionRepository.save(toCreateCorrectOption);

        // create the question to save to DB
        Question toSaveQuestion = new Question();
        toSaveQuestion.setQuestionText(question.getQuestionText());
        toSaveQuestion.setCategory(question.getCategory());

        // create options to save
        // add option to question and question to option <manual bidirectional mapping>
        List<Option> toSaveOptions = new ArrayList<>();
        // looping over the options from user and storing it in the list of options, directly adding to the memory space of Question
        question.getOptions().stream()
                .forEach((option) -> {
                    option.setQuestion(toSaveQuestion);
                    toSaveQuestion.getOptions().add(option);
                });

        // Create the Question and set the correctOption
        toSaveQuestion.setCorrectOption(savedCorrectOption); // Set the saved Option as correctOption

        return questionRepository.save(toSaveQuestion);
        // Save the Question to the database
    }
}

