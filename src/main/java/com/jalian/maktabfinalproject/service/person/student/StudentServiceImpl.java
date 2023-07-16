package com.jalian.maktabfinalproject.service.person.student;

import com.jalian.maktabfinalproject.entity.Course;
import com.jalian.maktabfinalproject.entity.Person;
import com.jalian.maktabfinalproject.entity.Student;
import com.jalian.maktabfinalproject.repository.PersonRepository;
import com.jalian.maktabfinalproject.repository.StudentRepository;
import com.jalian.maktabfinalproject.service.course.CourseService;
import com.jalian.maktabfinalproject.service.person.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
//dast kari kardan dade ha dar service anjam mishavad
public class StudentServiceImpl extends PersonServiceImpl<Student, StudentRepository> implements StudentService {

    @Autowired
    @Qualifier(value = "studentRepository")
    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public void addCourse(Student student, Course course) {
        ArrayList<Course> courses= new ArrayList<>(student.getCourses());
        courses.add(course);
        student.setCourses(courses);
        studentRepository.save(student);
    }
}
