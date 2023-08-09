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
public class StudentQuizRepositoryTest extends BaseEntityRepositoryTest<StudentQuizKey, StudentQuiz, StudentQuizRepository> {

    @Autowired
    private StudentQuizRepository studentQuizRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Override
    protected StudentQuizRepository repository() {
        return studentQuizRepository;
    }

    @Override
    @BeforeEach
    protected void setup() {
        Student student = Student.builder().
                firstName("amirhosein").
                lastName("jalian").
                password("1382").
                id("aj").
                courses(new ArrayList<>()).
                birthDate(new Date(System.currentTimeMillis())).
                role(new Role(RoleNames.STUDENT))
                .build();
        student = studentRepository.save(student);
        Quiz quiz = Quiz.builder().
                description("this is quiz 1").
                date(new Date(System.currentTimeMillis())).
                time(2).
                title("quiz 1").
                build();
        quiz = quizRepository.save(quiz);
        value = StudentQuiz.builder()
                .id(new StudentQuizKey(student.getId(), quiz.getId()))
                .quiz(quiz)
                .student(student)
                .grade(10.)
                .build();
    }

    @Override
    protected StudentQuiz newInstance() {
        Student student = Student.builder().
                firstName("mohammad").
                lastName("jalian").
                password("1375").
                id("mj").
                birthDate(new Date(System.currentTimeMillis())).
                role(new Role(RoleNames.STUDENT))
                .build();
        student = studentRepository.save(student);
        Quiz quiz = Quiz.builder().
                description("this is quiz 2").
                date(new Date(System.currentTimeMillis())).
                time(5).
                title("quiz 2").
                build();
        quiz = quizRepository.save(quiz);
        return StudentQuiz.builder()
                .id(new StudentQuizKey(student.getId(), quiz.getId()))
                .quiz(quiz)
                .student(student)
                .grade(15.)
                .build();
    }

    @Test
        //it fails, why??????????????????????????????
    void joinedAQuizTest() {
        StudentQuiz studentQuiz = repository().save(value);
        repository().joinedAQuiz(value.getStudent().getId(), value.getQuiz().getId());
        studentQuiz = repository().findById(studentQuiz.getId()).get();
        assertThat(studentQuiz.getIsJoined()).isTrue();
    }

    @Test
    void getStudentsOfAQuizTest() {
        StudentQuiz studentQuiz = repository().save(value);
        List<Student> students = repository().getStudentsOfAQuiz(studentQuiz.getQuiz().getId());
        assertThat(students).isEqualTo(List.of(studentQuiz.getStudent()));
    }

    @Test
    void getQuizzesOfAStudentTest() {
        value = repository().save(value);
        List<Quiz> quizzes = repository().getQuizzesOfAStudent(value.getStudent().getId());
        assertThat(quizzes).isEqualTo(List.of(value.getQuiz()));
    }

    @Test
    void getPassedStudentsTest() {
        value = repository().save(value);
        Student student = Student.builder().
                firstName("mohammad").
                lastName("jalian").
                password("1375").
                id("mj").
                birthDate(new Date(System.currentTimeMillis())).
                role(new Role(RoleNames.STUDENT))
                .build();
        student = studentRepository.save(student);
        StudentQuiz studentQuiz = StudentQuiz.builder()
                .id(new StudentQuizKey(student.getId(), value.getQuiz().getId()))
                .quiz(value.getQuiz())
                .student(student)
                .grade(9.999999)
                .build();
        student.setQuizzes(List.of(studentQuiz));
        List<Student> students = repository().getPassedStudents(value.getQuiz().getId());
        assertThat(students).isEqualTo(List.of(value.getStudent()));
    }
}
