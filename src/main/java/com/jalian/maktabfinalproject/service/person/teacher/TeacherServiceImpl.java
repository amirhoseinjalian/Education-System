package com.jalian.maktabfinalproject.service.person.teacher;

import com.jalian.maktabfinalproject.entity.Course;
import com.jalian.maktabfinalproject.entity.Person;
import com.jalian.maktabfinalproject.entity.Quiz;
import com.jalian.maktabfinalproject.entity.Teacher;
import com.jalian.maktabfinalproject.repository.PersonRepository;
import com.jalian.maktabfinalproject.repository.TeacherRepository;
import com.jalian.maktabfinalproject.service.person.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class TeacherServiceImpl extends PersonServiceImpl<Teacher, TeacherRepository> implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository repository) {
        super(repository);
    }

    @Override
    public List<Course> getAllCourses(Teacher teacher) {
        return teacher.getCourses();
    }

    @Override
    public List<Course> addCourse(Teacher teacher, Course course) {
        List<Course> courses = new ArrayList<>(teacher.getCourses());
        courses.add(course);
        teacher.setCourses(courses);
        teacherRepository.save(teacher);
        return teacherRepository.findById(teacher.getId()).get().getCourses();
    }
}
