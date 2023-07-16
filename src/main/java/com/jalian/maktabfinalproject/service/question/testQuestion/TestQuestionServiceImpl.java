package com.jalian.maktabfinalproject.service.question.testQuestion;

import com.jalian.maktabfinalproject.entity.TestQuestion;
import com.jalian.maktabfinalproject.repository.QuestionRepository;
import com.jalian.maktabfinalproject.repository.TestQuestionRepository;
import com.jalian.maktabfinalproject.service.question.QuestionServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TestQuestionServiceImpl extends QuestionServiceImple<TestQuestion, TestQuestionRepository> implements TestQuestionService {


    @Autowired
    private TestQuestionRepository testQuestionRepository;

    public TestQuestionServiceImpl(TestQuestionRepository repository) {
        super(repository);
    }

    @Override
    public List<TestQuestion> questionBank(Long id) {
        return testQuestionRepository.questionBank(id);
    }
}
