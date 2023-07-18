package com.jalian.maktabfinalproject.service.quizQuestion;

import com.jalian.maktabfinalproject.entity.Question;
import com.jalian.maktabfinalproject.entity.Quiz;
import com.jalian.maktabfinalproject.entity.QuizQuestionJoinTable;
import com.jalian.maktabfinalproject.entity.QuizQuestionKey;
import com.jalian.maktabfinalproject.service.base.BaseService;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizQuestionService extends BaseService<QuizQuestionJoinTable, QuizQuestionKey> {

    List<Question> getQuestions(Quiz quiz);

    List<Quiz> getQuizzes(Question question);
}
