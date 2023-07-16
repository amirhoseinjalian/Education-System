package com.jalian.maktabfinalproject.service.quiz;

import com.jalian.maktabfinalproject.entity.Question;
import com.jalian.maktabfinalproject.entity.Quiz;
import com.jalian.maktabfinalproject.service.base.BaseService;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizService extends BaseService<Quiz, Long> {

    List<Question> questionBank(@Param("id") Long id);

    /*<Value extends Question> List<Value> getQuestions();*/

    void addQuestion(Quiz quiz, Question question, Double score);

    List<Quiz> getAllowedQuizzes(String studentId);
}
