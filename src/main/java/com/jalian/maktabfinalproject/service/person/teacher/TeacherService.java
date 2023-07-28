package com.jalian.maktabfinalproject.service.person.teacher;

import com.jalian.maktabfinalproject.entity.Course;
import com.jalian.maktabfinalproject.entity.Teacher;
import com.jalian.maktabfinalproject.service.person.PersonService;

public interface TeacherService extends PersonService<Teacher> {

    void addCourse(Teacher teacher, Course course);
}
