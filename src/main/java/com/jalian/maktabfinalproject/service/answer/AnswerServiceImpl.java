package com.jalian.maktabfinalproject.service.answer;

import com.jalian.maktabfinalproject.entity.Answer;
import com.jalian.maktabfinalproject.repository.AnswerRepository;
import com.jalian.maktabfinalproject.service.base.BaseServiceImpl;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class AnswerServiceImpl<Value extends Answer, Repository extends AnswerRepository<Value>> extends
        BaseServiceImpl<Value, Long, Repository> implements AnswerService<Value> {

    private Repository repository;

    public AnswerServiceImpl(Repository repository) {
        super(repository);
        this.repository = repository;
    }
}
