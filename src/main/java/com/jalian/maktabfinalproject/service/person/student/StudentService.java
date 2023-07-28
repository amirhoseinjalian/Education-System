package com.jalian.maktabfinalproject.service.person.student;

import com.jalian.maktabfinalproject.entity.Course;
import com.jalian.maktabfinalproject.entity.Student;
import com.jalian.maktabfinalproject.service.person.PersonService;

public interface StudentService extends PersonService<Student> {

    void addCourse(Student student, Course course);
}
