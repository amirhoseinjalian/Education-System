package com.jalian.maktabfinalproject.service.answer;

import com.jalian.maktabfinalproject.entity.Answer;
import com.jalian.maktabfinalproject.entity.Quiz;
import com.jalian.maktabfinalproject.entity.Student;
import com.jalian.maktabfinalproject.repository.AnswerRepository;
import com.jalian.maktabfinalproject.service.base.BaseServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public abstract class AnswerServiceImpl<Value extends Answer, Repository extends AnswerRepository<Value>> extends
        BaseServiceImpl<Value, Long, Repository> implements AnswerService<Value> {

    public AnswerServiceImpl(Repository repository) {
        super(repository);
    }

    @Override
    public List<Value> getAnswers(Student student, Quiz quiz) {
        return getRepository().getAnswers(student.getId(), quiz.getId());
    }
}
