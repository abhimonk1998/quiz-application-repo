package com.AbhiQuizApp.quizApp.Repository;

import com.AbhiQuizApp.quizApp.Constants.Category;
import com.AbhiQuizApp.quizApp.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByCategory(Category category);

    @Query(value = "Select * from question q where q.category=:category order by random() limit :numQuestions", nativeQuery = true)
    List<Question> findRandomQuestionByCategory(int category, int numQuestions);
}
