package com.jalian.maktabfinalproject.service.question;

import com.jalian.maktabfinalproject.entity.Question;
import com.jalian.maktabfinalproject.entity.Quiz;
import com.jalian.maktabfinalproject.service.base.BaseService;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionService<Value extends Question> extends BaseService<Value, Long> {

    List<Value> questionBank(@Param("id") Long id);

    List<Value> getQuestions(@Param("id") Long id);

    Double getScore(Quiz quiz, Question question);
}
