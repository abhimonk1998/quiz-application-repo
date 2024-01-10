package com.AbhiQuizApp.quizApp.Service;

import com.AbhiQuizApp.quizApp.Constants.Category;
import com.AbhiQuizApp.quizApp.Dao.QuizDao;
import com.AbhiQuizApp.quizApp.Model.Question;
import com.AbhiQuizApp.quizApp.Model.Quiz;
import com.AbhiQuizApp.quizApp.Model.QuizClientDTO;
import com.AbhiQuizApp.quizApp.Repository.QuestionRepository;
import com.AbhiQuizApp.quizApp.Mapper.QuizMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    private static final Logger logger = LoggerFactory.getLogger(QuizService.class);
    @Autowired
    private QuizDao quizDao;

    @Autowired
    private QuizMapper quizMapper;
    @Autowired
    private QuestionRepository questionRepository;

    public String createQuiz(String title, Category category, int numQuestions) {
        int categoryInteger = category.getValue();
        System.out.println(categoryInteger);
        try {
            List<Question> questions = questionRepository.findRandomQuestionByCategory(categoryInteger, numQuestions);
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);
            if(!StringUtils.isEmpty(quizDao.save(quiz))) {
                return "SUCCESS";
            }
        } catch(NullPointerException e){
            e.printStackTrace();
            logger.info(e.getMessage());
        }


        return "FAILED";
    }

    public QuizClientDTO getQuiz(Integer quizId) {
        Optional<Quiz> optionalQuiz = quizDao.findById(quizId);
        QuizClientDTO quizClientDTO = null;
        if(optionalQuiz.isPresent()){
            quizClientDTO = quizMapper.quizToQuizClientDTO(optionalQuiz.get());
        }
        return quizClientDTO;
    }
}
