package com.jalian.maktabfinalproject.repository;

import com.jalian.maktabfinalproject.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Transactional
public abstract class QuestionRepositoryTest<Value extends Question, Repository extends QuestionRepository<Value>> extends BaseEntityRepositoryTest<Long, Value, Repository> {

    @Autowired
    protected CourseRepository courseRepository;

    @Autowired
    protected QuizRepository quizRepository;

    @Autowired
    protected QuizQuestionRepository quizQuestionRepository;

    @Test
    void questionBankTest() {
        Course course = Course.builder()
                .title("java")
                .beginning(new Date(System.currentTimeMillis()))
                .ending(new Date(System.currentTimeMillis()))
                .build();
        course = courseRepository.save(course);
        Quiz quiz = Quiz.builder().
                description("this is quiz 1").
                date(new Date(System.currentTimeMillis())).
                time(2).
                title("quiz 1").
                course(course).
                build();
        Quiz quiz2 = Quiz.builder().
                description("this is quiz 2").
                date(new Date(System.currentTimeMillis())).
                time(10).
                title("quiz 2").
                course(course).
                build();
        quizRepository.saveAll(List.of(quiz, quiz2));
        course.setQuizzes(List.of(quiz, quiz2));
        value = repository().save(value);
        Value value2 = newInstance();
        value2 = repository().save(value2);
        QuizQuestionJoinTable quizQuestionJoinTable = QuizQuestionJoinTable.builder()
                .quiz(quiz)
                .question(value)
                .score(10.0)
                .quizQuestionKey(new QuizQuestionKey(quiz.getId(), value.getId()))
                .build();
        value.setQuizzes(List.of(quizQuestionJoinTable));
        quiz.setQuestions(List.of(quizQuestionJoinTable));
        QuizQuestionJoinTable quizQuestionJoinTable2 = QuizQuestionJoinTable.builder()
                .quiz(quiz2)
                .question(value2)
                .score(10.0)
                .quizQuestionKey(new QuizQuestionKey(quiz.getId(), value2.getId()))
                .build();
        value2.setQuizzes(List.of(quizQuestionJoinTable2));
        quiz2.setQuestions(List.of(quizQuestionJoinTable));
        /*Value testQuestion = createInstance(myGetClass());*//*TestQuestion.builder()
                .options(List.of(new Option("option 1"), new Option("option 2")))
                .description("test question description")
                .title("test question title")
                .build();*//*
        testQuestion = ((TestQuestionRepository)repository()).save(testQuestion);
        DescriptiveQuestion descriptiveQuestion = DescriptiveQuestion.builder()
                .question("descriptive question")
                .description("descriptive question description")
                .title("descriptive question title")
                .build();
        descriptiveQuestion = ((DescriptiveQuestionRepository)repository()).save(descriptiveQuestion);
        QuizQuestionJoinTable quizQuestionJoinTable3 = QuizQuestionJoinTable.builder()
                .quiz(quiz2)
                .question(testQuestion)
                .score(10.0)
                .quizQuestionKey(new QuizQuestionKey(quiz2.getId(), testQuestion.getId()))
                .build();
        quizQuestionJoinTable3 = quizQuestionRepository.save(quizQuestionJoinTable3);
        QuizQuestionJoinTable quizQuestionJoinTable4 = QuizQuestionJoinTable.builder()
                .quiz(quiz2)
                .question(descriptiveQuestion)
                .score(10.0)
                .quizQuestionKey(new QuizQuestionKey(quiz2.getId(), descriptiveQuestion.getId()))
                .build();
        quizQuestionJoinTable4 = quizQuestionRepository.save(quizQuestionJoinTable4);*/
        List<Value> questions = repository().questionBank(course.getId());
        assertThat(questions).isEqualTo(List.of(value, value2));
        assertThat(questions.size()).isEqualTo(2);
    }

    @Test
    void getQuestionTest() {
        Quiz quiz = Quiz.builder().
                description("this is quiz 1").
                date(new Date(System.currentTimeMillis())).
                time(2).
                title("quiz 1").
                build();
        quiz = quizRepository.save(quiz);
        value = repository().save(value);
        Value value2 = newInstance();
        value2 = repository().save(value2);
        QuizQuestionJoinTable quizQuestionJoinTable = QuizQuestionJoinTable.builder()
                .quiz(quiz)
                .question(value)
                .score(10.0)
                .quizQuestionKey(new QuizQuestionKey(quiz.getId(), value.getId()))
                .build();
        value.setQuizzes(List.of(quizQuestionJoinTable));
        QuizQuestionJoinTable quizQuestionJoinTable2 = QuizQuestionJoinTable.builder()
                .quiz(quiz)
                .question(value2)
                .score(10.0)
                .quizQuestionKey(new QuizQuestionKey(quiz.getId(), value2.getId()))
                .build();
        value2.setQuizzes(List.of(quizQuestionJoinTable2));
        quiz.setQuestions(List.of(quizQuestionJoinTable, quizQuestionJoinTable2));
        List<Value> questions = repository().getQuestions(quiz.getId());
        assertThat(questions).isEqualTo(List.of(value2, value));
        assertThat(questions.size()).isEqualTo(2);
    }
}
