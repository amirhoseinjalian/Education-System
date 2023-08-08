package com.jalian.maktabfinalproject.repository;

import com.jalian.maktabfinalproject.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Transactional
public class TestAnswerRepositoryTest extends AnswerRepositoryTest<TestAnswer, TestAnswerRepository> {

    @Autowired
    private TestAnswerRepository testAnswerRepository;

    @Override
    protected TestAnswerRepository repository() {
        return testAnswerRepository;
    }

    @Override
    @BeforeEach
    protected void setup() {
        value = TestAnswer.builder().correctOption(new Option("correct option")).build();
    }

    @Override
    protected TestAnswer newInstance() {
        return TestAnswer.builder().correctOption(new Option("correct option 2")).build();
    }

    @Test
    void getAnswersTest() {
        value = repository().save(value);
        TestAnswer testAnswer = newInstance();
        testAnswer = repository().saveAndFlush(testAnswer);
        Student student = Student.builder().
                firstName("amirhosein").
                lastName("jalian").
                password("1382").
                id("aj").
                courses(new ArrayList<>()).
                birthDate(new Date(System.currentTimeMillis())).
                role(new Role(RoleNames.STUDENT))
                .build();
        Quiz quiz = Quiz.builder().
                description("this is quiz 1").
                date(new Date(System.currentTimeMillis())).
                time(2).
                title("quiz 1").
                build();
        StudentQuiz studentQuiz = StudentQuiz.builder().
                student(student).
                quiz(quiz).
                studentQuizKey(new StudentQuizKey(student.getId(), quiz.getId())).
                isJoined(false).
                grade(10.0).
                answers(List.of(value, testAnswer)).
                build();
        quiz.setStudents(List.of(studentQuiz));
        quiz = quizRepository.save(quiz);
        student.setQuizzes(List.of(studentQuiz));
        //student = studentRepository.save(student); why makes it wrong????????????????????????????????????????????????
        //studentQuiz = studentQuizRepository.save(studentQuiz); why makes it wrong??????????????????????????????????????????
        //cascades are really important!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        testAnswer.setStudentQuiz(studentQuiz);
        value.setStudentQuiz(studentQuiz);
        List<TestAnswer> testAnswers = repository().getAnswers(student.getId(), quiz.getId());
        assertThat(testAnswers).isNotNull();
        assertThat(testAnswers).isEqualTo(List.of(value, testAnswer));
        assertThat(testAnswers.size()).isEqualTo(2);
    }
}
