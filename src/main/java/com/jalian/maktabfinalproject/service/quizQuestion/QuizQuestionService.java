package com.jalian.maktabfinalproject.service.quizQuestion;

import com.jalian.maktabfinalproject.entity.*;
import com.jalian.maktabfinalproject.service.base.BaseService;

import java.util.List;

public interface QuizQuestionService extends BaseService<QuizQuestionJoinTable, QuizQuestionKey> {

    <Value extends Question> List<Value> getQuestions(Course course, Class<Value> questionType);

    List<Quiz> getQuizzes(Question question);

    List<Question> getQuestions(Quiz quiz);

    void addQuestion(Quiz quiz, Question question, double score);

    Double getScore(Quiz quiz, Question question);
}
