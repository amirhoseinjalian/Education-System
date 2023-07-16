package com.jalian.maktabfinalproject.service.person.teacher;

import com.jalian.maktabfinalproject.entity.Course;
import com.jalian.maktabfinalproject.entity.Quiz;
import com.jalian.maktabfinalproject.entity.Teacher;
import com.jalian.maktabfinalproject.service.person.PersonService;

import java.util.List;

public interface TeacherService extends PersonService<Teacher> {

    List<Course> getAllCourses(Teacher teacher);

    List<Course> addCourse(Teacher teacher, Course course);
}
