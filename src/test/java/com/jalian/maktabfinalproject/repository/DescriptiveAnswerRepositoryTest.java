package com.jalian.maktabfinalproject.repository;

import com.jalian.maktabfinalproject.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class DescriptiveAnswerRepositoryTest extends AnswerRepositoryTest<DescriptiveAnswer, DescriptiveAnswerRepository> {

    @Autowired
    private DescriptiveAnswerRepository descriptiveAnswerRepository;

    @Override
    protected DescriptiveAnswerRepository repository() {
        return descriptiveAnswerRepository;
    }

    @Override
    @BeforeEach
    protected void setup() {
        value = DescriptiveAnswer.builder().answer("correct answer").build();
    }

    @Override
    protected DescriptiveAnswer newInstance() {
        return DescriptiveAnswer.builder().answer("correct answer 2").build();
    }

    @Test
    void getAnswersTest() {
        value = repository().save(value);
        DescriptiveAnswer descriptiveAnswer = newInstance();
        descriptiveAnswer = repository().saveAndFlush(descriptiveAnswer);
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
                answers(List.of(value, descriptiveAnswer)).
                build();
        quiz.setStudents(List.of(studentQuiz));
        quiz = quizRepository.save(quiz);
        student.setQuizzes(List.of(studentQuiz));
        //student = studentRepository.save(student); why makes it wrong????????????????????????????????????????????????
        //studentQuiz = studentQuizRepository.save(studentQuiz); why makes it wrong??????????????????????????????????????????
        //cascades are really important!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        descriptiveAnswer.setStudentQuiz(studentQuiz);
        value.setStudentQuiz(studentQuiz);
        List<DescriptiveAnswer> descriptiveAnswers = repository().getAnswers(student.getId(), quiz.getId());
        assertThat(descriptiveAnswers).isNotNull();
        assertThat(descriptiveAnswers).isEqualTo(List.of(value, descriptiveAnswer));
        assertThat(descriptiveAnswers.size()).isEqualTo(2);
    }
}
