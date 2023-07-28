package com.jalian.maktabfinalproject.service.course;

import com.jalian.maktabfinalproject.entity.*;
import com.jalian.maktabfinalproject.repository.CourseRepository;
import com.jalian.maktabfinalproject.repository.util.StudentCourseRepository;
import com.jalian.maktabfinalproject.repository.util.StudentTeacherRepository;
import com.jalian.maktabfinalproject.repository.util.TeacherCourseRepository;
import com.jalian.maktabfinalproject.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CourseServiceImpl extends BaseServiceImpl<Course, Long, CourseRepository> implements CourseService {

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    @Autowired
    private TeacherCourseRepository teacherCourseRepository;

    @Autowired
    private StudentTeacherRepository studentTeacherRepository;

    public CourseServiceImpl(CourseRepository repository) {
        super(repository);
    }

    @Override
    public void addStudent(Course course, Student student) {
        studentCourseRepository.addStudentToCourse(student.getId(), course.getId());
        studentTeacherRepository.addStudentToTeacher(student.getId(), course.getTeacher().getId());
    }

    @Override
    public List<Quiz> getAllQuizzes(Course course) {
        return course.getQuizzes();
    }

    @Override
    public void addQuiz(Course course, Quiz quiz) {
        List<Quiz> quizzes = new ArrayList<>(course.getQuizzes());
        quizzes.add(quiz);
        quiz.setCourse(course);
        course.setQuizzes(quizzes);
        getRepository().save(course);
    }

    @Override
    public void addTeacher(Course course, Teacher teacher) {
        teacherCourseRepository.addTeacherToCourse(teacher.getId(), course.getId());
        course.getStudents().forEach(student -> studentTeacherRepository.addStudentToTeacher(student.getId(), teacher.getId()));
    }

    @Override
    public List<Person> getAllUsers(Course course) {
        List<Person> persons = new ArrayList<>(course.getStudents());
        persons.add(course.getTeacher());
        return persons;
    }
}
