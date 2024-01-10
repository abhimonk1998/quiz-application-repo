package com.AbhiQuizApp.quizApp.Mapper;

import com.AbhiQuizApp.quizApp.Model.Question;
import com.AbhiQuizApp.quizApp.Model.Quiz;
import com.AbhiQuizApp.quizApp.Model.QuizClientDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuizMapper {

    @Mappings({
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "questions", target = "questions")
    })
    QuizClientDTO quizToQuizClientDTO(Quiz quiz);

    @AfterMapping
    default void excludeCorrectOption(Quiz quiz, @MappingTarget QuizClientDTO quizClientDTO) {
        List<Question> questions = quizClientDTO.getQuestions();
        if (questions != null) {
            for (Question question : questions) {
                question.setCorrectOption(null); // Excluding the correctOption field
            }
        }
    }
}