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
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    private Student student;

    @BeforeEach
    public void setup() {
        student = new Student();
        student.setFirstName("amirhosein");
        student.setLastName("jalian");
        student.setPassword("1382");
        student.setId("aj");
        student.setBirthDate(new Date(System.currentTimeMillis()));
        student.setRole(new Role(RoleNames.STUDENT));
    }

    @Test
    public void save() {
        Student savedStudent = studentRepository.save(student);
        assertThat(savedStudent).isNotNull();
        assertThat(savedStudent.getId()).isEqualTo("aj");
    }

    @Test
    public void findAll() {
        Student student1 = new Student();
        student1.setFirstName("robyn");
        student1.setLastName("fenty");
        student1.setPassword("1988");
        student1.setId("rihanna");
        student1.setBirthDate(new Date(System.currentTimeMillis()));
        student1.setRole(new Role(RoleNames.TEACHER));
        studentRepository.save(student1);
        studentRepository.save(student);
        List<Student> students = studentRepository.findAll();
        assertThat(students).isNotNull();
        assertThat(students.size()).isEqualTo(2);
    }

    @Test
    public void findById() {
        studentRepository.save(student);
        student = studentRepository.findById("aj").get();
        assertThat(student).isNotNull();
        assertThat(student).isEqualTo(student);
    }

    @Test
    public void getAllWaitingUsers() {
        Student student1 = new Student();
        student1.setFirstName("robyn");
        student1.setLastName("fenty");
        student1.setPassword("1988");
        student1.setId("rihanna");
        student1.setStatus(RegistrationStatus.CONFIRMED);
        student1.setBirthDate(new Date(System.currentTimeMillis()));
        student1.setRole(new Role(RoleNames.TEACHER));
        studentRepository.save(student1);
        studentRepository.save(student);
        List<Person> waitingStudents = studentRepository.getAllWaitingPersons();
        assertThat(waitingStudents).isNotNull();
        assertThat(waitingStudents.size()).isEqualTo(1);
        assertThat(student).isEqualTo((Student) waitingStudents.get(0));
    }

    @Test
    @Transactional
    //fails!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public void confirmUsersReg() {
        studentRepository.save(student);
        studentRepository.confirmUsersReg(student.getId());
        student = studentRepository.findById("aj").get();
        assertThat(student.getStatus()).isEqualTo(RegistrationStatus.CONFIRMED);
    }
}