package com.jalian.maktabfinalproject.service;

import com.jalian.maktabfinalproject.entity.Option;
import com.jalian.maktabfinalproject.entity.TestAnswer;
import com.jalian.maktabfinalproject.repository.TestAnswerRepository;
import com.jalian.maktabfinalproject.service.answer.testAnswer.TestAnswerService;
import com.jalian.maktabfinalproject.service.answer.testAnswer.TestAnswerServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestAnswerServiceTest extends AnswerServiceTest<TestAnswer, TestAnswerRepository, TestAnswerService> {

    @Override
    protected TestAnswerService getService() {
        return new TestAnswerServiceImpl(repository);
    }

    @Override
    protected void setup() {
        value = TestAnswer.builder().id(1L).correctOption(new Option("correct option")).build();
    }

    @Override
    protected TestAnswer newInstance() {
        return TestAnswer.builder().id(2L).correctOption(new Option("correct option")).build();
    }

    @Override
    protected TestAnswer getNewValueForUpdate() {
        return TestAnswer.builder().id(1L).correctOption(new Option("correct option 2")).build();
    }
}
