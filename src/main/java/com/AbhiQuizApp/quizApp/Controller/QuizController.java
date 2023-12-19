package com.AbhiQuizApp.quizApp.Controller;

import com.AbhiQuizApp.quizApp.Constants.Category;
import com.AbhiQuizApp.quizApp.Model.Question;
import com.AbhiQuizApp.quizApp.Repository.OptionRepository;
import com.AbhiQuizApp.quizApp.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// QuizController.java
@RestController
@RequestMapping("/api/questions")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private OptionRepository optionRepository;

    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            List<Question> questions = quizService.getAllQuestions();
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            // Handle unexpected errors
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byCategory")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@RequestParam Category category) {
        try {
            List<Question> questions = quizService.getQuestionsByCategory(category);
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            // Handle validation errors (e.g., invalid input data)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Handle other unexpected errors
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/insert-dummy-data")
    public ResponseEntity<String> insertDummyData() {
        quizService.insertDummyData();
        return ResponseEntity.ok("Dummy data inserted successfully");
    }

    @PostMapping("/create")
    public ResponseEntity<Question> createNewQuestion(@RequestBody Question question) {
        // You can add validation or error handling here if needed
        try {
            Question createdQuestion = quizService.createQuestion(question);
            return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Handle validation errors (e.g., invalid input data)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Handle other unexpected errors
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
