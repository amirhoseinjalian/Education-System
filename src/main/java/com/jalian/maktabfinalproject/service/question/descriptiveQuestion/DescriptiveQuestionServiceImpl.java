package com.jalian.maktabfinalproject.service.question.descriptiveQuestion;

import com.jalian.maktabfinalproject.entity.DescriptiveQuestion;
import com.jalian.maktabfinalproject.repository.DescriptiveQuestionRepository;
import com.jalian.maktabfinalproject.service.question.QuestionServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DescriptiveQuestionServiceImpl extends QuestionServiceImpl<DescriptiveQuestion, DescriptiveQuestionRepository> implements DescriptiveQuestionService {

    public DescriptiveQuestionServiceImpl(DescriptiveQuestionRepository repository) {
        super(repository);
    }
}
