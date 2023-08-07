package com.jalian.maktabfinalproject.repository;

import com.jalian.maktabfinalproject.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Transactional
public class StudentRepositoryTest extends PersonRepositoryTest<Student, StudentRepository> {

    @Autowired
    @Lazy
    CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    @Lazy
    private TeacherRepository teacherRepository;

    @Override
    protected Student setup() {
        value = Student.builder().
                firstName("amirhosein").
                lastName("jalian").
                password("1382").
                id("aj").
                courses(new ArrayList<>()).
                birthDate(new Date(System.currentTimeMillis())).
                role(new Role(RoleNames.STUDENT))
                .build();
        return value;
    }

    @Override
    protected Student newInstance() {
        return Student.builder().
                firstName("mohammad").
                lastName("jalian").
                password("1375").
                id("mj").
                birthDate(new Date(System.currentTimeMillis())).
                role(new Role(RoleNames.STUDENT))
                .build();
    }

    @Test
        //it fails but runs correctly
    void addStudentToCourseTest() {
        //@BeforeEach does not run setup() here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        setup();
        value = repository().save(value);
        Course course = Course.builder()
                .title("java")
                .beginning(new Date(System.currentTimeMillis()))
                .ending(new Date(System.currentTimeMillis()))
                .build();
        course = courseRepository.save(course);
        //repository().addStudentToCourse(value.getId(), course.getId());
        Student student = repository().findById("aj").get();
        List<Course> courses = student.getCourses();
        assertThat(courses).isNotNull();
        assertThat(courses.size()).isEqualTo(1);
        assertThat(courses.contains(course)).isTrue();
    }

    @Test
        //it fails but runs correctly
    void addStudentToTeacherTest() {
        value = setup();
        Student student = repository().save(value);
        Teacher teacher = Teacher.builder().
                firstName("mohammad").
                lastName("jalian").
                password("1375").
                id("mj").
                role(new Role(RoleNames.TEACHER)).
                birthDate(new Date(System.currentTimeMillis())).
                build();
        Teacher savedTeacher = teacherRepository.save(teacher);
        repository().addStudentToTeacher(student.getId(), savedTeacher.getId());
        List<Teacher> teachers = student.getTeachers();
        assertThat(teachers).isNotNull();
        assertThat(teachers.size()).isEqualTo(1);
        assertThat(teachers.contains(savedTeacher)).isTrue();
    }

    @Override
    protected StudentRepository repository() {
        return studentRepository;
    }
}