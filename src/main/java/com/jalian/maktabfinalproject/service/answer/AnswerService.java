package com.jalian.maktabfinalproject.service.answer;

import com.jalian.maktabfinalproject.entity.Answer;
import com.jalian.maktabfinalproject.entity.Quiz;
import com.jalian.maktabfinalproject.entity.Student;
import com.jalian.maktabfinalproject.service.base.BaseService;

import java.util.List;

public interface AnswerService<Value extends Answer> extends BaseService<Value, Long> {

    List<Value> getAnswers(Student student, Quiz quiz);
}
