package com.jalian.maktabfinalproject.service.quizQuestion;

import com.jalian.maktabfinalproject.entity.*;
import com.jalian.maktabfinalproject.service.base.BaseService;

import java.util.List;

public interface QuizQuestionService extends BaseService<QuizQuestionJoinTable, QuizQuestionKey> {

    <Values extends Question> List<Values> getQuestions(Course course, String questionType);

    List<Quiz> getQuizzes(Question question);

    List<Question> getQuestions(Quiz quiz);

    void addQuestion(Quiz quiz, Question question, double score);

    Double getScore(Quiz quiz, Question question);
}
