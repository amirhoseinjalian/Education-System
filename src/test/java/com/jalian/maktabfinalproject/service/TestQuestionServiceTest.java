package com.jalian.maktabfinalproject.service;

import com.jalian.maktabfinalproject.entity.Option;
import com.jalian.maktabfinalproject.entity.TestQuestion;
import com.jalian.maktabfinalproject.repository.TestQuestionRepository;
import com.jalian.maktabfinalproject.service.question.testQuestion.TestQuestionService;
import com.jalian.maktabfinalproject.service.question.testQuestion.TestQuestionServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TestQuestionServiceTest extends QuestionServiceTest<TestQuestion, TestQuestionRepository, TestQuestionService> {

    @Override
    protected TestQuestionService getService() {
        return new TestQuestionServiceImpl(repository);
    }

    @Override
    protected void setup() {
        value = TestQuestion.builder()
                .id(1L)
                .options(List.of(new Option("option 1"), new Option("option 2")))
                .description("test question description")
                .title("test question title")
                .build();
    }

    @Override
    protected TestQuestion newInstance() {
        return TestQuestion.builder()
                .id(2L)
                .options(List.of(new Option("new option 1"), new Option("new option 2")))
                .description("new test question description")
                .title("new test question title")
                .build();
    }

    @Override
    protected TestQuestion getNewValueForUpdate() {
        return TestQuestion.builder()
                .id(1L)
                .options(List.of(new Option("option 1"), new Option("option 2")))
                .description("test question description 2")
                .title("test question title 2")
                .build();
    }
}
