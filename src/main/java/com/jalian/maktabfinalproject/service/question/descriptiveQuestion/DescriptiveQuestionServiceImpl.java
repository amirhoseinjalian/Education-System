package com.jalian.maktabfinalproject.service.question.descriptiveQuestion;

import com.jalian.maktabfinalproject.entity.Course;
import com.jalian.maktabfinalproject.entity.DescriptiveQuestion;
import com.jalian.maktabfinalproject.repository.DescriptiveQuestionRepository;
import com.jalian.maktabfinalproject.service.question.QuestionServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DescriptiveQuestionServiceImpl extends QuestionServiceImpl<DescriptiveQuestion, DescriptiveQuestionRepository> implements DescriptiveQuestionService {

    public DescriptiveQuestionServiceImpl(DescriptiveQuestionRepository repository) {
        super(repository);
    }


    @Override
    public List<DescriptiveQuestion> questionBank(Course course) {
        return getRepository().questionBank(course.getId());
    }
}
