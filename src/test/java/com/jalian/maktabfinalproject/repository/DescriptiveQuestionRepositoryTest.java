package com.jalian.maktabfinalproject.repository;

import com.jalian.maktabfinalproject.entity.DescriptiveQuestion;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@Transactional
public class DescriptiveQuestionRepositoryTest extends QuestionRepositoryTest<DescriptiveQuestion, DescriptiveQuestionRepository> {

    @Autowired
    private DescriptiveQuestionRepository descriptiveQuestionRepository;

    @Override
    protected DescriptiveQuestionRepository repository() {
        return descriptiveQuestionRepository;
    }

    @Override
    @BeforeEach
    protected void setup() {
        value = DescriptiveQuestion.builder()
                .question("descriptive question")
                .description("descriptive question description")
                .title("descriptive question title")
                .build();
    }

    @Override
    protected DescriptiveQuestion newInstance() {
        return DescriptiveQuestion.builder()
                .question("descriptive question 2")
                .description("descriptive question description 2")
                .title("descriptive question title 2")
                .build();
    }
}
