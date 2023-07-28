package com.jalian.maktabfinalproject.service.quizQuestion;

import com.jalian.maktabfinalproject.entity.Question;
import com.jalian.maktabfinalproject.entity.Quiz;
import com.jalian.maktabfinalproject.entity.QuizQuestionJoinTable;
import com.jalian.maktabfinalproject.entity.QuizQuestionKey;
import com.jalian.maktabfinalproject.service.base.BaseService;

import java.util.List;

public interface QuizQuestionService extends BaseService<QuizQuestionJoinTable, QuizQuestionKey> {

    <Values extends Question> List<Values> getQuestions(Quiz quiz, String questionType);

    List<Quiz> getQuizzes(Question question);

    List<Question> getQuestions(Quiz quiz);

    Double getScore(Quiz quiz, Question question);
}
