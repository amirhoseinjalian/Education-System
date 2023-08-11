package com.jalian.maktabfinalproject.service;

import com.jalian.maktabfinalproject.entity.*;
import com.jalian.maktabfinalproject.repository.QuestionRepository;
import com.jalian.maktabfinalproject.service.question.QuestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public abstract class QuestionServiceTest<Value extends Question, Repository extends QuestionRepository<Value>, Service extends QuestionService<Value>>
        extends BaseEntityServiceTest<Long, Value, Repository, Service> {

    @Test
    void questionBankTest() {
        Course course = Course.builder()
                .id(3L)
                .title("java")
                .beginning(new Date(System.currentTimeMillis()))
                .ending(new Date(System.currentTimeMillis()))
                .build();
        Quiz quiz = Quiz.builder().
                id(4L).
                description("this is quiz 1").
                date(new Date(System.currentTimeMillis())).
                time(2).
                title("quiz 1").
                course(course).
                build();
        Quiz quiz2 = Quiz.builder()
                .id(5L)
                .description("this is quiz 2").
                        date(new Date(System.currentTimeMillis())).
                        time(10).
                        title("quiz 2").
                        course(course).
                        build();
        course.setQuizzes(List.of(quiz, quiz2));
        Value value2 = newInstance();
        QuizQuestionJoinTable quizQuestionJoinTable = QuizQuestionJoinTable.builder()
                .quiz(quiz)
                .question(value)
                .score(10.0)
                .id(new QuizQuestionKey(quiz.getId(), value.getId()))
                .build();
        value.setQuizzes(List.of(quizQuestionJoinTable));
        quiz.setQuestions(List.of(quizQuestionJoinTable));
        QuizQuestionJoinTable quizQuestionJoinTable2 = QuizQuestionJoinTable.builder()
                .quiz(quiz2)
                .question(value2)
                .score(10.0)
                .id(new QuizQuestionKey(quiz.getId(), value2.getId()))
                .build();
        given(repository.questionBank(course.getId())).willReturn(List.of(value, value2));
        List<Value> values = service.questionBank(course);
        assertThat(values).isEqualTo(List.of(value, value2));
    }

    @Test
    void getQuestionsTest() {
        Quiz quiz = Quiz.builder()
                .id(3L)
                .description("this is quiz 1").
                        date(new Date(System.currentTimeMillis())).
                        time(2).
                        title("quiz 1").
                        build();
        Value value2 = newInstance();
        QuizQuestionJoinTable quizQuestionJoinTable = QuizQuestionJoinTable.builder()
                .quiz(quiz)
                .question(value)
                .score(10.0)
                .id(new QuizQuestionKey(quiz.getId(), value.getId()))
                .build();
        value.setQuizzes(List.of(quizQuestionJoinTable));
        QuizQuestionJoinTable quizQuestionJoinTable2 = QuizQuestionJoinTable.builder()
                .quiz(quiz)
                .question(value2)
                .score(10.0)
                .id(new QuizQuestionKey(quiz.getId(), value2.getId()))
                .build();
        value2.setQuizzes(List.of(quizQuestionJoinTable2));
        quiz.setQuestions(List.of(quizQuestionJoinTable, quizQuestionJoinTable2));
        given(repository.getQuestions(quiz.getId())).willReturn(List.of(value2, value));
        List<Value> questions = service.getQuestions(quiz);
        assertThat(questions).isEqualTo(List.of(value2, value));
        assertThat(questions.size()).isEqualTo(2);
    }
}
