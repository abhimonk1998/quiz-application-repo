package com.AbhiQuizApp.quizApp.Repository;

import com.AbhiQuizApp.quizApp.Constants.Category;
import com.AbhiQuizApp.quizApp.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByCategory(Category category);
}
