package com.jalian.maktabfinalproject.service.quizQuestion;

import com.jalian.maktabfinalproject.entity.*;
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
    public <Value extends Question> List<Value> getQuestions(Course course, Class<Value> questionType) {
        return getRepository().getQuestions(course.getId(), questionType);
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
                .id(new QuizQuestionKey(quiz.getId(), question.getId()))
                .score(score)
                .quiz(quiz)
                .question(question)
                .build();
        //it is needed!!!!!!!!!!!!!!!!!!!!!!!!!!!Why????????????????????????????????????????????????????????????????
        quizQuestionJoinTable = getRepository().save(quizQuestionJoinTable);
        //are two lines below needed???????????????????????????????????????????????????
        if (question.getQuizzes() != null) {
            question.getQuizzes().add(quizQuestionJoinTable);
        }
        if (quiz.getQuestions() != null) {
            quiz.getQuestions().add(quizQuestionJoinTable);
        }
        getRepository().save(quizQuestionJoinTable);
    }

    @Override
    public Double getScore(Quiz quiz, Question question) {
        return getRepository().getScore(quiz.getId(), question.getId());
    }
}
