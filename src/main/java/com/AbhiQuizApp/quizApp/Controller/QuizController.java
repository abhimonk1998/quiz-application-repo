package com.AbhiQuizApp.quizApp.Controller;

import com.AbhiQuizApp.quizApp.Constants.Category;
import com.AbhiQuizApp.quizApp.Model.QuizClientDTO;
import com.AbhiQuizApp.quizApp.Service.QuizService;
import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    private static final Logger logger = LoggerFactory.getLogger(QuizService.class);
    private static final String SUCCESS = "SUCCESS";
    private static final String FAILED = "FAILED";
    @Autowired
    private QuizService quizService;

    @GetMapping("get/{id}")
    private ResponseEntity<QuizClientDTO> getQuiz(@PathVariable Integer id){
        try {
            QuizClientDTO quiz = quizService.getQuiz(id);
            if(Objects.nonNull(quiz)) {
                return new ResponseEntity<>(quiz, HttpStatus.OK);
            } else {
                throw new ObjectNotFoundException(QuizClientDTO.class,"Object not found");
            }

        } catch (Exception e){
            logger.error("Error occurred to fetch quiz");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String title, @RequestParam Category category, @RequestParam int numQuestions) {

        String result = quizService.createQuiz(title, category, numQuestions);
        if(result.equals(SUCCESS)){
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(FAILED, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
