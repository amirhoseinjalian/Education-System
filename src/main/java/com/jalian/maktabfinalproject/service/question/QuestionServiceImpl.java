package com.jalian.maktabfinalproject.service.question;

import com.jalian.maktabfinalproject.entity.Question;
import com.jalian.maktabfinalproject.entity.Quiz;
import com.jalian.maktabfinalproject.repository.QuestionRepository;
import com.jalian.maktabfinalproject.service.base.BaseServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public abstract class QuestionServiceImpl<Value extends Question, Repository extends QuestionRepository<Value>>
        extends BaseServiceImpl<Value, Long, Repository> implements QuestionService<Value> {

    private Repository questionRepository;

    public QuestionServiceImpl(Repository repository) {
        super(repository);
        this.questionRepository = repository;
    }

    @Override
    public List<Value> getQuestions(Quiz quiz) {
        return questionRepository.getQuestions(quiz.getId());
    }
}
