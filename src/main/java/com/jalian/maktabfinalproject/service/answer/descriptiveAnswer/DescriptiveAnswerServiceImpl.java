package com.jalian.maktabfinalproject.service.answer.descriptiveAnswer;

import com.jalian.maktabfinalproject.entity.DescriptiveAnswer;
import com.jalian.maktabfinalproject.repository.DescriptiveAnswerRepository;
import com.jalian.maktabfinalproject.service.answer.AnswerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DescriptiveAnswerServiceImpl extends AnswerServiceImpl<DescriptiveAnswer, DescriptiveAnswerRepository> implements DescriptiveAnswerService {

    private DescriptiveAnswerRepository descriptiveAnswerRepository;

    @Autowired
    public DescriptiveAnswerServiceImpl(DescriptiveAnswerRepository repository) {
        super(repository);
        descriptiveAnswerRepository = repository;
    }
}
