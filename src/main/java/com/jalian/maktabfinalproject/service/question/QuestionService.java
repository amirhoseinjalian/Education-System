package com.jalian.maktabfinalproject.service.question;

import com.jalian.maktabfinalproject.entity.Course;
import com.jalian.maktabfinalproject.entity.Question;
import com.jalian.maktabfinalproject.entity.Quiz;
import com.jalian.maktabfinalproject.service.base.BaseService;

import java.util.List;

public interface QuestionService<Value extends Question> extends BaseService<Value, Long> {

    List<Value> questionBank(Course course);

    List<Value> getQuestions(Quiz quiz);
}
