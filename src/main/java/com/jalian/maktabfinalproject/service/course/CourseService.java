package com.jalian.maktabfinalproject.service.course;

import com.jalian.maktabfinalproject.entity.*;
import com.jalian.maktabfinalproject.service.base.BaseService;

import java.util.List;

public interface CourseService extends BaseService<Course, Long> {

    void addStudent(Course course, Student student);

    void addQuiz(Course course, Quiz quiz);

    void addTeacher(Course course, Teacher teacher);

    List<Person> getAllUsers(Course course);
}
