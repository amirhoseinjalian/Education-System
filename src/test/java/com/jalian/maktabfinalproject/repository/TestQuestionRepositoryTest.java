package com.jalian.maktabfinalproject.repository;

import com.jalian.maktabfinalproject.entity.Option;
import com.jalian.maktabfinalproject.entity.TestQuestion;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@DataJpaTest
@Transactional
public class TestQuestionRepositoryTest extends QuestionRepositoryTest<TestQuestion, TestQuestionRepository> {

    @Autowired
    private TestQuestionRepository testQuestionRepository;

    @Override
    protected TestQuestionRepository repository() {
        return testQuestionRepository;
    }

    @Override
    @BeforeEach
    protected void setup() {
        value = TestQuestion.builder()
                .options(List.of(new Option("option 1"), new Option("option 2")))
                .description("test question description")
                .title("test question title")
                .build();
    }

    @Override
    protected TestQuestion newInstance() {
        return TestQuestion.builder()
                .options(List.of(new Option("option 1"), new Option("option 2")))
                .description("test question description 2")
                .title("test question title 2")
                .build();
    }
}
