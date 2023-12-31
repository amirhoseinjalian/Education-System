package com.jalian.maktabfinalproject.service.answer.testAnswer;

import com.jalian.maktabfinalproject.entity.TestAnswer;
import com.jalian.maktabfinalproject.repository.TestAnswerRepository;
import com.jalian.maktabfinalproject.service.answer.AnswerServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TestAnswerServiceImpl extends AnswerServiceImpl<TestAnswer, TestAnswerRepository> implements TestAnswerService {

    public TestAnswerServiceImpl(TestAnswerRepository repository) {
        super(repository);
    }
}
