package com.jalian.maktabfinalproject.repository;

import com.jalian.maktabfinalproject.entity.Role;
import com.jalian.maktabfinalproject.entity.RoleNames;
import com.jalian.maktabfinalproject.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;

@DataJpaTest
public class TeacherRepositoryTest extends PersonRepositoryTest<Teacher, TeacherRepository> {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    protected TeacherRepository repository() {
        return teacherRepository;
    }

    @Override
    protected Teacher setup() {
        value = Teacher.builder().
                firstName("amirhosein").
                lastName("jalian").
                password("1382").
                id("aj").
                birthDate(new Date(System.currentTimeMillis())).
                role(new Role(RoleNames.TEACHER))
                .build();
        return value;
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

    @Test
    void addStudentToTeacherTest() {

    }

    @Test
    void addTeacherToCourseTest() {

    }
}
