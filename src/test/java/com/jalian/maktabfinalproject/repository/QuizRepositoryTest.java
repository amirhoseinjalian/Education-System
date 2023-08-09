package com.jalian.maktabfinalproject.repository;

import com.jalian.maktabfinalproject.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Transactional
public class QuizRepositoryTest extends BaseEntityRepositoryTest<Long, Quiz, QuizRepository> {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private StudentQuizRepository studentQuizRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    protected QuizRepository repository() {
        return quizRepository;
    }

    @Override
    @BeforeEach
    protected void setup() {
        value = Quiz.builder().
                description("this is quiz 1").
                date(new Date(System.currentTimeMillis())).
                time(2).
                title("quiz 1").
                build();
    }

    @Override
    protected Quiz newInstance() {
        return Quiz.builder().
                description("this is quiz 2").
                date(new Date(System.currentTimeMillis())).
                time(25).
                title("quiz 2").
                build();
    }

    @Test
    void getAllowedQuizzesTest() {
        value = repository().save(value);
        Quiz quiz = newInstance();
        quiz = repository().save(quiz);
        Quiz quiz2 = Quiz.builder().
                description("this is quiz 3").
                date(new Date(System.currentTimeMillis())).
                time(20).
                title("quiz 3").
                build();
        quiz2 = repository().save(quiz2);
        Student student = Student.builder().
                firstName("mohammad").
                lastName("jalian").
                password("1375").
                id("mj").
                birthDate(new Date(System.currentTimeMillis())).
                role(new Role(RoleNames.STUDENT))
                .build();
        StudentQuiz studentQuiz = StudentQuiz.builder()
                .grade(2.)
                .student(student)
                .quiz(value)
                .studentQuizKey(new StudentQuizKey(student.getId(), value.getId()))
                .build();
        StudentQuiz studentQuiz2 = StudentQuiz.builder()
                .grade(2.3)
                .student(student)
                .quiz(quiz)
                .isJoined(true)
                .studentQuizKey(new StudentQuizKey(student.getId(), quiz.getId()))
                .build();
        StudentQuiz studentQuiz3 = StudentQuiz.builder()
                .grade(2.7)
                .student(student)
                .quiz(quiz2)
                .studentQuizKey(new StudentQuizKey(student.getId(), quiz2.getId()))
                .build();
        student.setQuizzes(List.of(studentQuiz, studentQuiz2, studentQuiz3));
        value.setStudents(List.of(studentQuiz));
        quiz.setStudents(List.of(studentQuiz2));
        quiz2.setStudents(List.of(studentQuiz3));
        List<Quiz> allowedQuizzes = repository().getAllowedQuizzes(student.getId());
        assertThat(allowedQuizzes).isEqualTo(List.of(quiz2, value));
    }
}
