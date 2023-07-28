package com.jalian.maktabfinalproject.service.person.teacher;

import com.jalian.maktabfinalproject.entity.Course;
import com.jalian.maktabfinalproject.entity.Teacher;
import com.jalian.maktabfinalproject.repository.TeacherRepository;
import com.jalian.maktabfinalproject.repository.util.TeacherCourseRepository;
import com.jalian.maktabfinalproject.service.person.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeacherServiceImpl extends PersonServiceImpl<Teacher, TeacherRepository> implements TeacherService {

    @Autowired
    private TeacherCourseRepository teacherCourseRepository;

    public TeacherServiceImpl(TeacherRepository repository) {
        super(repository);
    }

    @Override
    public void addCourse(Teacher teacher, Course course) {
        teacherCourseRepository.addTeacherToCourse(teacher.getId(), course.getId());
    }
}
