package com.jalian.maktabfinalproject.service;

import com.jalian.maktabfinalproject.entity.Course;
import com.jalian.maktabfinalproject.entity.Role;
import com.jalian.maktabfinalproject.entity.RoleNames;
import com.jalian.maktabfinalproject.entity.Teacher;
import com.jalian.maktabfinalproject.repository.TeacherRepository;
import com.jalian.maktabfinalproject.service.person.teacher.TeacherService;
import com.jalian.maktabfinalproject.service.person.teacher.TeacherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;

import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TeacherServiceTest extends PersonServiceTest<Teacher, TeacherRepository, TeacherService> {

    @Override
    protected TeacherService getService() {
        return new TeacherServiceImpl(repository);
    }

    @Override
    protected Class<TeacherRepository> getRepositoryClass() {
        return TeacherRepository.class;
    }

    @Override
    @BeforeEach
    protected void setup() {
        value = Teacher.builder().
                firstName("amirhosein").
                lastName("jalian").
                password("1382").
                id("aj").
                birthDate(new Date(System.currentTimeMillis())).
                role(new Role(RoleNames.TEACHER))
                .build();
    }

    @Override
    protected Teacher newInstance() {
        return Teacher.builder().
                firstName("mohammad").
                lastName("jalian").
                password("1375").
                id("mj").
                birthDate(new Date(System.currentTimeMillis())).
                role(new Role(RoleNames.TEACHER))
                .build();
    }

    @Override
    protected Teacher getNewValueForUpdate() {
        return Teacher.builder().
                firstName("amirhosein 2 ").
                lastName("jalian 2").
                password("1382").
                id("aj").
                birthDate(new Date(System.currentTimeMillis())).
                role(new Role(RoleNames.TEACHER))
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
        willDoNothing().given(repository).addTeacherToCourse(value.getId(), course.getId());
        service.addCourse(value, course);
        verify(repository, times(1)).addTeacherToCourse(value.getId(), course.getId());
    }
}
