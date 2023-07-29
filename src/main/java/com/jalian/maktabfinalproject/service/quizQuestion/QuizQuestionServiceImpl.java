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
    public List<Question> getQuestions(Quiz quiz) {
        return getRepository().getQuestions(quiz.getId());
    }

    @Override
    public void addQuestion(Quiz quiz, Question question, double score) {
        QuizQuestionJoinTable quizQuestionJoinTable = QuizQuestionJoinTable.builder()
                .quizQuestionKey(new QuizQuestionKey(quiz.getId(), question.getId()))
                .score(score)
                .quiz(quiz)
                .question(question)
                .build();
        //are lines 48 and 49 needed???????????????????????????????????????????????????
        question.getQuizzes().add(quizQuestionJoinTable);
        quiz.getQuestions().add(quizQuestionJoinTable);
        getRepository().save(quizQuestionJoinTable);
    }

    @Override
    public Double getScore(Quiz quiz, Question question) {
        return getRepository().getScore(quiz.getId(), question.getId());
    }
}
