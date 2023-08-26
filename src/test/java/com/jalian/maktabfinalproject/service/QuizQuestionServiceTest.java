package com.jalian.maktabfinalproject.service;

import com.jalian.maktabfinalproject.entity.*;
import com.jalian.maktabfinalproject.repository.QuizQuestionRepository;
import com.jalian.maktabfinalproject.service.quizQuestion.QuizQuestionService;
import com.jalian.maktabfinalproject.service.quizQuestion.QuizQuestionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class QuizQuestionServiceTest extends BaseEntityServiceTest<QuizQuestionKey, QuizQuestionJoinTable, QuizQuestionRepository, QuizQuestionService> {

    @Override
    protected QuizQuestionService getService() {
        return new QuizQuestionServiceImpl(repository);
    }

    @Override
    protected Class<QuizQuestionRepository> getRepositoryClass() {
        return QuizQuestionRepository.class;
    }

    @Override
    @BeforeEach
    protected void setup() {
        Quiz quiz = Quiz.builder().
                id(1L).
                build();
        TestQuestion testQuestion = TestQuestion.builder()
                .id(2L)
                .build();
        value = QuizQuestionJoinTable.builder()
                .quiz(quiz)
                .question(testQuestion)
                .score(0.5)
                .id(new QuizQuestionKey(quiz.getId(), testQuestion.getId()))
                .build();
        quiz.setQuestions(new ArrayList<>(List.of(value)));
        testQuestion.setQuizzes(List.of(value));
    }

    @Override
    protected QuizQuestionJoinTable newInstance() {
        Quiz quiz = Quiz.builder().
                id(3L).
                build();
        DescriptiveQuestion descriptiveQuestion = DescriptiveQuestion.builder()
                .id(4L)
                .build();
        QuizQuestionJoinTable quizQuestionJoinTable = QuizQuestionJoinTable.builder()
                .quiz(quiz)
                .question(descriptiveQuestion)
                .score(5.5)
                .id(new QuizQuestionKey(quiz.getId(), descriptiveQuestion.getId()))
                .build();
        quiz.setQuestions(List.of(quizQuestionJoinTable));
        descriptiveQuestion.setQuizzes(new ArrayList<>(List.of(quizQuestionJoinTable)));
        return quizQuestionJoinTable;
    }

    @Override
    protected QuizQuestionJoinTable getNewValueForUpdate() {
        value.setScore(1.);
        return value;
    }

    @Test
    void getQuizzesTest() {
        given(repository.getQuizzes(value.getQuestion().getId())).willReturn((List.of(value.getQuiz())));
        List<Quiz> quizzes = service.getQuizzes(value.getQuestion());
        assertThat(quizzes).isEqualTo((List.of(value.getQuiz())));
    }

    @Test
    void getQuestionsTest() {
        given(repository.getQuestions(value.getQuiz().getId())).willReturn((List.of(value.getQuestion())));
        List<Question> questions = service.getQuestions(value.getQuiz());
        assertThat(questions).isEqualTo((List.of(value.getQuestion())));
    }

    @Test
    void getScoreTest() {
        given(repository.getScore(value.getQuiz().getId(), value.getQuestion().getId())).willReturn(value.getScore());
        Double score = service.getScore(value.getQuiz(), value.getQuestion());
        assertThat(score).isEqualTo(value.getScore());
    }

    @Test
    void addQuestionTest() {
        QuizQuestionJoinTable quizQuestionJoinTable = QuizQuestionJoinTable
                .builder()
                .question(newInstance().getQuestion())
                .quiz(value.getQuiz())
                .score(5.)
                .id(new QuizQuestionKey(value.getQuiz().getId(), newInstance().getQuestion().getId()))
                .build();
        given(repository.save(quizQuestionJoinTable)).willReturn(quizQuestionJoinTable);
        service.addQuestion(value.getQuiz(), newInstance().getQuestion(), 5.);
        assertThat(value.getQuiz().getQuestions().contains(quizQuestionJoinTable)).isTrue();
    }

    @Test
    void getTestQuestionsTest() {
        Course course = Course.builder()
                .id(5L)
                .quizzes(List.of(value.getQuiz()))
                .build();
        given(repository.getQuestions(course.getId(), TestQuestion.class)).willReturn(List.of((TestQuestion) value.getQuestion()));
        List<TestQuestion> testQuestions = service.getQuestions(course, TestQuestion.class);
        assertThat(testQuestions).isEqualTo(List.of((TestQuestion) value.getQuestion()));
    }

    @Test
    void getDescriptiveQuestionsTest() {
        Course course = Course.builder()
                .id(5L)
                .quizzes(List.of(newInstance().getQuiz()))
                .build();
        given(repository.getQuestions(course.getId(), DescriptiveQuestion.class)).willReturn(List.of((DescriptiveQuestion) newInstance().getQuestion()));
        List<DescriptiveQuestion> descriptiveQuestions = service.getQuestions(course, DescriptiveQuestion.class);
        assertThat(descriptiveQuestions).isEqualTo(List.of((DescriptiveQuestion) newInstance().getQuestion()));
    }
}
