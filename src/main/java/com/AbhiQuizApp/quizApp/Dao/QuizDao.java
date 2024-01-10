package com.AbhiQuizApp.quizApp.Dao;

import com.AbhiQuizApp.quizApp.Model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
