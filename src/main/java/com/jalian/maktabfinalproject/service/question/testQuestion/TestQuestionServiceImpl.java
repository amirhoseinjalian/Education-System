package com.jalian.maktabfinalproject.service.question.testQuestion;

import com.jalian.maktabfinalproject.entity.TestQuestion;
import com.jalian.maktabfinalproject.repository.TestQuestionRepository;
import com.jalian.maktabfinalproject.service.question.QuestionServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TestQuestionServiceImpl extends QuestionServiceImpl<TestQuestion, TestQuestionRepository> implements TestQuestionService {

    public TestQuestionServiceImpl(TestQuestionRepository repository) {
        super(repository);
    }
}
