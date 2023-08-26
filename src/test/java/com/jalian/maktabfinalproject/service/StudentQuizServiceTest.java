package com.jalian.maktabfinalproject.service;

import com.jalian.maktabfinalproject.entity.*;
import com.jalian.maktabfinalproject.repository.StudentQuizRepository;
import com.jalian.maktabfinalproject.service.studentQuiz.StudentQuizService;
import com.jalian.maktabfinalproject.service.studentQuiz.StudentQuizServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class StudentQuizServiceTest extends BaseEntityServiceTest<StudentQuizKey, StudentQuiz, StudentQuizRepository, StudentQuizService> {

    @Override
    protected StudentQuizService getService() {
        return new StudentQuizServiceImpl(repository);
    }

    @Override
    protected Class<StudentQuizRepository> getRepositoryClass() {
        return StudentQuizRepository.class;
    }

    @Override
    @BeforeEach
    protected void setup() {
        Student student = Student.builder().
                firstName("amirhosein").
                lastName("jalian").
                password("1382").
                id("aj").
                quizzes(new ArrayList<>()).
                birthDate(new Date(System.currentTimeMillis())).
                role(new Role(RoleNames.STUDENT))
                .build();
        Quiz quiz = Quiz.builder().
                id(1L).
                description("this is quiz 1").
                students(new ArrayList<>()).
                date(new Date(System.currentTimeMillis())).
                time(2).
                title("quiz 1").
                build();
        value = StudentQuiz.builder()
                .id(new StudentQuizKey(student.getId(), quiz.getId()))
                .quiz(quiz)
                .student(student)
                .grade(1.)
                .build();
        student.getQuizzes().add(value);
        quiz.getStudents().add(value);
    }

    @Override
    protected StudentQuiz newInstance() {
        Student student = Student.builder().
                firstName("mohammad").
                lastName("jalian").
                password("1375").
                id("mj").
                quizzes(new ArrayList<>())
                .birthDate(new Date(System.currentTimeMillis())).
                        role(new Role(RoleNames.STUDENT))
                .build();
        Quiz quiz = Quiz.builder().
                id(2L).
                students(new ArrayList<>())
                .description("this is quiz 2").
                        date(new Date(System.currentTimeMillis())).
                        time(5).
                        title("quiz 2").
                        build();
        StudentQuiz studentQuiz = StudentQuiz.builder()
                .id(new StudentQuizKey(student.getId(), quiz.getId()))
                .quiz(quiz)
                .student(student)
                .grade(15.2)
                .build();
        student.getQuizzes().add(studentQuiz);
        quiz.getStudents().add(studentQuiz);
        return studentQuiz;
    }

    @Override
    protected StudentQuiz getNewValueForUpdate() {
        value.setGrade(11.6);
        return value;
    }

    @Test
    void joinedAQuizTest() {
        willDoNothing().given(repository).joinedAQuiz(value.getStudent().getId(), value.getQuiz().getId());
        service.joinedAQuiz(value.getStudent(), value.getQuiz());
        verify(repository, times(1)).joinedAQuiz(value.getStudent().getId(), value.getQuiz().getId());
    }

    @Test
    void getStudentsOfAQuizTest() {
        given(repository.getStudentsOfAQuiz(value.getQuiz().getId())).willReturn(List.of(value.getStudent()));
        List<Student> students = service.getStudentsOfAQuiz(value.getQuiz());
        assertThat(students).isEqualTo(List.of(value.getStudent()));
    }

    @Test
    void getQuizzesOfAStudentTest() {
        given(repository.getQuizzesOfAStudent(value.getStudent().getId())).willReturn(List.of(value.getQuiz()));
        List<Quiz> quizzes = service.getQuizzesOfAStudent(value.getStudent());
        assertThat(quizzes).isEqualTo(List.of(value.getQuiz()));
    }

    @Test
    void getPassedStudentsTest() {
        given(repository.getPassedStudents(newInstance().getQuiz().getId())).willReturn(List.of(newInstance().getStudent()));
        List<Student> students = service.getPassedStudents(newInstance().getQuiz());
        assertThat(students).isEqualTo(List.of(newInstance().getStudent()));
    }

    @Test
    void addStudentTest() {
        StudentQuiz studentQuiz = StudentQuiz.builder()
                .id(new StudentQuizKey(newInstance().getStudent().getId(), value.getQuiz().getId()))
                .student(newInstance().getStudent())
                .quiz(value.getQuiz())
                .build();
        given(repository.save(studentQuiz)).willReturn(studentQuiz);
        service.addStudent(value.getQuiz(), newInstance().getStudent());
        List<Student> students = List.of(value.getStudent(), newInstance().getStudent());
        assertThat(students.contains(newInstance().getStudent())).isTrue();
    }
}
