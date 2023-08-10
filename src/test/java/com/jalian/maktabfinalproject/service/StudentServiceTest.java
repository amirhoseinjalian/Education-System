package com.jalian.maktabfinalproject.service;

import com.jalian.maktabfinalproject.entity.Course;
import com.jalian.maktabfinalproject.entity.Role;
import com.jalian.maktabfinalproject.entity.RoleNames;
import com.jalian.maktabfinalproject.entity.Student;
import com.jalian.maktabfinalproject.repository.StudentRepository;
import com.jalian.maktabfinalproject.service.person.student.StudentService;
import com.jalian.maktabfinalproject.service.person.student.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest extends PersonServiceTest<Student, StudentRepository, StudentService> {

    @Override
    protected StudentService getService() {
        return new StudentServiceImpl(repository);
    }

    @Override
    @BeforeEach
    protected void setup() {
        value = Student.builder().
                firstName("amirhosein").
                lastName("jalian").
                password("1382").
                id("aj").
                courses(new ArrayList<>()).
                birthDate(new Date(System.currentTimeMillis())).
                role(new Role(RoleNames.STUDENT))
                .build();
    }

    @Override
    protected Student newInstance() {
        return Student.builder().
                firstName("mohammad").
                lastName("jalian").
                password("1375").
                id("mj").
                courses(new ArrayList<>()).
                birthDate(new Date(System.currentTimeMillis())).
                role(new Role(RoleNames.STUDENT))
                .build();
    }

    @Override
    protected Student getNewValueForUpdate() {
        return Student.builder().
                firstName("amirhosein 2").
                lastName("jalian 2").
                password("1382").
                id("aj").
                courses(new ArrayList<>()).
                birthDate(new Date(System.currentTimeMillis())).
                role(new Role(RoleNames.STUDENT))
                .build();
    }

    @Test
    void addCourseTest() {
        Course course = Course.builder()
                .id(1L)
                .title("java")
                .beginning(new Date(System.currentTimeMillis()))
                .ending(new Date(System.currentTimeMillis()))
                .build();
        List<Course> courses = List.of(course);
        willDoNothing().given(repository).addStudentToCourse(value.getId(), course.getId());
        service.addCourse(value, course);
        verify(repository, times(1)).addStudentToCourse(value.getId(), course.getId());
    }
}
