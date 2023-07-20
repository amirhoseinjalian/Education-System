package com.jalian.maktabfinalproject.service.person.teacher;

import com.jalian.maktabfinalproject.entity.Course;
import com.jalian.maktabfinalproject.entity.Student;
import com.jalian.maktabfinalproject.entity.Teacher;
import com.jalian.maktabfinalproject.repository.TeacherRepository;
import com.jalian.maktabfinalproject.service.person.PersonServiceImpl;
import com.jalian.maktabfinalproject.service.person.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TeacherServiceImpl extends PersonServiceImpl<Teacher, TeacherRepository> implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    @Lazy
    private StudentService studentService;

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
        List<Student> students = new ArrayList<>(course.getStudents());
        List<Teacher> teachers = null;
        for (Student student : students) {
            teachers = new ArrayList<>(student.getTeachers());
            teachers.add(teacher);
            student.setTeachers(teachers);
            studentService.save(student);
        }
        students.addAll(teacher.getStudents());
        teacher.setStudents(students);
        teacherRepository.save(teacher);
        return teacherRepository.findById(teacher.getId()).get().getCourses();
    }
}
