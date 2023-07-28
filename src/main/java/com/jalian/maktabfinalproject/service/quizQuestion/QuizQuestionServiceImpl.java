package com.jalian.maktabfinalproject.service.quizQuestion;

import com.jalian.maktabfinalproject.entity.Question;
import com.jalian.maktabfinalproject.entity.Quiz;
import com.jalian.maktabfinalproject.entity.QuizQuestionJoinTable;
import com.jalian.maktabfinalproject.entity.QuizQuestionKey;
import com.jalian.maktabfinalproject.repository.QuizQuestionRepository;
import com.jalian.maktabfinalproject.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QuizQuestionServiceImpl extends BaseServiceImpl<QuizQuestionJoinTable, QuizQuestionKey, QuizQuestionRepository> implements QuizQuestionService {

    @Autowired
    public QuizQuestionServiceImpl(QuizQuestionRepository repository) {
        super(repository);
    }


    @Override
    public <Values extends Question> List<Values> getQuestions(Quiz quiz, String questionType) {
        return getRepository().<Values>getQuestions(quiz.getId(), questionType);
    }

    @Override
    public List<Quiz> getQuizzes(Question question) {
        return getRepository().getQuizzes(question.getId());
    }

    @Override
    public Double getScore(Quiz quiz, Question question) {
        return getRepository().getScore(quiz.getId(), question.getId());
    }
}
