package com.jalian.maktabfinalproject.service;

import com.jalian.maktabfinalproject.entity.DescriptiveAnswer;
import com.jalian.maktabfinalproject.repository.DescriptiveAnswerRepository;
import com.jalian.maktabfinalproject.service.answer.descriptiveAnswer.DescriptiveAnswerService;
import com.jalian.maktabfinalproject.service.answer.descriptiveAnswer.DescriptiveAnswerServiceImpl;
import org.junit.jupiter.api.BeforeEach;

public class DescriptiveAnswerServiceTest extends AnswerServiceTest<DescriptiveAnswer, DescriptiveAnswerRepository, DescriptiveAnswerService> {

    @Override
    protected DescriptiveAnswerService getService() {
        return new DescriptiveAnswerServiceImpl(repository);
    }

    @Override
    @BeforeEach
    protected void setup() {
        value = DescriptiveAnswer.builder().id(1L).answer("correct answer").build();
    }

    @Override
    protected DescriptiveAnswer newInstance() {
        return DescriptiveAnswer.builder().id(2L).answer("correct answer 2").build();
    }

    @Override
    protected DescriptiveAnswer getNewValueForUpdate() {
        return DescriptiveAnswer.builder().id(1L).answer("correct answer 2").build();
    }
}
