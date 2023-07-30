package com.jalian.maktabfinalproject.service.person.student;

import com.jalian.maktabfinalproject.entity.Course;
import com.jalian.maktabfinalproject.entity.Student;
import com.jalian.maktabfinalproject.repository.StudentRepository;
import com.jalian.maktabfinalproject.service.person.PersonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentServiceImpl extends PersonServiceImpl<Student, StudentRepository> implements StudentService {

    public StudentServiceImpl(StudentRepository repository) {
        super(repository);
    }

    @Override
    public void addCourse(Student student, Course course) {
        getRepository().addStudentToCourse(student.getId(), course.getId());
    }
}
