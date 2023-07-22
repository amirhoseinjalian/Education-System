package com.jalian.maktabfinalproject.service;

import com.jalian.maktabfinalproject.entity.*;
import com.jalian.maktabfinalproject.repository.StudentRepository;
import com.jalian.maktabfinalproject.service.person.student.StudentServiceImpl;
import com.jalian.maktabfinalproject.service.person.teacher.TeacherServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    @MockBean
    private TeacherServiceImpl teacherService;

    private Student student, student1, student2;

    @Before
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Before
    void setUpTeacherService() {
        teacherService = Mockito.mock(TeacherServiceImpl.class);
    }

    @BeforeEach
    void setup() {
        student = Student.builder().
                firstName("amirhosein").
                lastName("jalian").
                password("1382").
                id("aj").
                birthDate(new Date(System.currentTimeMillis())).
                role(new Role(RoleNames.STUDENT))
                .build();

        student1 = Student.builder()
                .firstName("robyn")
                .lastName("fenty")
                .password("1988")
                .id("rihanna")
                .birthDate(new Date(System.currentTimeMillis()))
                .role(new Role(RoleNames.STUDENT))
                .build();

        student2 = new Student();
        student2.setFirstName("taylor");
        student2.setLastName("swift");
        student2.setPassword("1989");
        student2.setId("ts");
        student2.setBirthDate(new Date(System.currentTimeMillis()));
        student2.setRole(new Role(RoleNames.STUDENT));
    }

    @AfterEach
    void delete() {
        student = null;
        student1 = null;
        student2 = null;
    }

    @Test
    void findById() {
        when(studentRepository.findById("aj")).thenReturn(Optional.of(student));
        Student found = studentService.findById("aj").get();
        assertThat(found).isNotNull();
        assertEquals(student, found);
    }

    @Test
    void save() {
        given(studentRepository.save(student)).willReturn(student);
        Student saved = studentService.save(student);
        assertEquals(saved, student);
    }

    @Test
    void findAll() {
        List<Student> students = new ArrayList<>();
        students.add(student);
        students.add(student1);
        students.add(student2);
        when(studentRepository.findAll()).thenReturn(students);
        List<Student> gottenStudents = studentService.findAll();
        assertEquals(students, gottenStudents);
        assertThat(gottenStudents.size()).isEqualTo(3);
    }

    @Test
    void getAllWaitingStudents() {
        List<Person> students = new ArrayList<>();
        students.add(student);
        students.add(student1);
        students.add(student2);
        when(studentRepository.getAllWaitingPersons()).thenReturn(students);
        List<Person> gottenStudents = studentService.getAllWaitingPersons();
        assertEquals(students, gottenStudents);
        assertThat(gottenStudents.size()).isEqualTo(3);
    }

    @Test
    void confirmUserReg() {
        studentService.confirmUsersReg("aj");
        studentService.confirmUsersReg("rihanna");
        studentService.confirmUsersReg("ts");
        verify(studentRepository, times(1)).confirmUsersReg("aj");
        verify(studentRepository, times(1)).confirmUsersReg("rihanna");
        verify(studentRepository, times(1)).confirmUsersReg("ts");
    }

    @Test
        //fails!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    void addCourse() {
        Course course = Course.builder().
                teacher(new Teacher()).
                title("java").
                students(new ArrayList<>()).
                build();
        student.setCourses(new ArrayList<>());
        student.setTeachers(new ArrayList<>());
        when(teacherService.save(course.getTeacher())).thenReturn(new Teacher());
        studentService.addCourse(student, course);
        verify(student, times(1)).getCourses();
        verify(course, times(1)).getTeacher();
    }
}
