package com.jalian.maktabfinalproject.service;

import com.jalian.maktabfinalproject.entity.*;
import com.jalian.maktabfinalproject.repository.CourseRepository;
import com.jalian.maktabfinalproject.service.course.CourseService;
import com.jalian.maktabfinalproject.service.course.CourseServiceImpl;
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
public class CourseServiceTest extends BaseEntityServiceTest<Long, Course, CourseRepository, CourseService> {

    @Override
    protected CourseService getService() {
        //repository = Mockito.mock(CourseRepository.class);
        return new CourseServiceImpl(repository);
    }

    @Override
    protected Class<CourseRepository> getRepositoryClass() {
        return CourseRepository.class;
    }

    @Override
    @BeforeEach
    protected void setup() {
        value = Course.builder()
                .id(1L)
                .title("java")
                .beginning(new Date(System.currentTimeMillis()))
                .ending(new Date(System.currentTimeMillis()))
                .students(new ArrayList<>())
                .build();
    }

    @Override
    protected Course newInstance() {
        return Course.builder()
                .id(2L)
                .title("java and lambdas")
                .beginning(new Date(System.currentTimeMillis()))
                .ending(new Date(System.currentTimeMillis()))
                .students(new ArrayList<>())
                .build();
    }

    @Override
    protected Course getNewValueForUpdate() {
        return Course.builder()
                .id(1L)
                .title("java 2")
                .beginning(new Date(System.currentTimeMillis()))
                .ending(new Date(System.currentTimeMillis()))
                .students(new ArrayList<>())
                .build();
    }

    @Test
    void addStudentTest() {
        Student student = Student.builder().
                firstName("amirhosein").
                lastName("jalian").
                password("1382").
                id("aj").
                birthDate(new Date(System.currentTimeMillis())).
                role(new Role(RoleNames.STUDENT))
                .build();
        willDoNothing().given(repository).addStudentToCourse(student.getId(), value.getId());
        service.addStudent(value, student);
        verify(repository, times(1)).addStudentToCourse(student.getId(), value.getId());
    }

    @Test
    void addTeacherTest() {
        Teacher teacher = Teacher.builder().
                firstName("amirhosein").
                lastName("jalian").
                password("1382").
                id("aj").
                birthDate(new Date(System.currentTimeMillis())).
                role(new Role(RoleNames.TEACHER))
                .build();
        willDoNothing().given(repository).addTeacherToCourse(teacher.getId(), value.getId());
        service.addTeacher(value, teacher);
        verify(repository, times(1)).addTeacherToCourse(teacher.getId(), value.getId());
    }

    @Test
    void getAllUsersTest() {
        Teacher teacher = Teacher.builder().
                firstName("amirhosein").
                lastName("jalian").
                password("1382").
                id("aj").
                birthDate(new Date(System.currentTimeMillis())).
                role(new Role(RoleNames.TEACHER))
                .build();
        Student student = Student.builder().
                firstName("mohammad").
                lastName("jalian").
                password("1375").
                id("mj").
                birthDate(new Date(System.currentTimeMillis())).
                role(new Role(RoleNames.STUDENT))
                .build();
        List<Person> users = List.of(student, teacher);
        value.getStudents().add(student);
        value.setTeacher(teacher);
        service.getAllUsers(value);
        List<Person> people = service.getAllUsers(value);
        assertThat(people).isEqualTo(users);
    }

    @Test
    void addQuizTest() {
        Quiz quiz = Quiz.builder().
                id(2L).
                description("this is quiz 1").
                date(new Date(System.currentTimeMillis())).
                time(2).
                title("quiz 1").
                build();
        given(repository.save(value)).willReturn(value);
        service.addQuiz(value, quiz);
        assertThat(value.getQuizzes().contains(quiz)).isTrue();
    }
}
