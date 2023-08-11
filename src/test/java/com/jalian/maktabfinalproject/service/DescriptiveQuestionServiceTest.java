package com.jalian.maktabfinalproject.service;

import com.jalian.maktabfinalproject.entity.DescriptiveQuestion;
import com.jalian.maktabfinalproject.repository.DescriptiveQuestionRepository;
import com.jalian.maktabfinalproject.service.question.descriptiveQuestion.DescriptiveQuestionService;
import com.jalian.maktabfinalproject.service.question.descriptiveQuestion.DescriptiveQuestionServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DescriptiveQuestionServiceTest extends QuestionServiceTest<DescriptiveQuestion, DescriptiveQuestionRepository, DescriptiveQuestionService> {

    @Override
    protected DescriptiveQuestionService getService() {
        return new DescriptiveQuestionServiceImpl(repository);
    }

    @Override
    protected void setup() {
        value = DescriptiveQuestion.builder()
                .id(1L)
                .question("descriptive question")
                .description("descriptive question description")
                .title("descriptive question title")
                .build();
    }

    @Override
    protected DescriptiveQuestion newInstance() {
        return DescriptiveQuestion.builder()
                .id(2L)
                .question("new descriptive question")
                .description("new descriptive question description")
                .title("new descriptive question title")
                .build();
    }

    @Override
    protected DescriptiveQuestion getNewValueForUpdate() {
        return DescriptiveQuestion.builder()
                .id(1L)
                .question("descriptive question 2")
                .description("descriptive question description 2")
                .title("descriptive question title 2")
                .build();
    }
}
