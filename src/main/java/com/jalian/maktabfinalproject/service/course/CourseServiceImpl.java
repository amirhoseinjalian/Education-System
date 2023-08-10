package com.jalian.maktabfinalproject.service.course;

import com.jalian.maktabfinalproject.entity.*;
import com.jalian.maktabfinalproject.repository.CourseRepository;
import com.jalian.maktabfinalproject.repository.StudentRepository;
import com.jalian.maktabfinalproject.repository.TeacherRepository;
import com.jalian.maktabfinalproject.service.base.BaseServiceImpl;
import com.jalian.maktabfinalproject.service.studentQuiz.StudentQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CourseServiceImpl extends BaseServiceImpl<Course, Long, CourseRepository> implements CourseService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentQuizService studentQuizService;

    public CourseServiceImpl(CourseRepository repository) {
        super(repository);
    }

    @Override
    public void addStudent(Course course, Student student) {
        getRepository().addStudentToCourse(student.getId(), course.getId());
        if (course.getTeacher() != null) {
            //should I use repository or service????????????????????????????????????????????????
            studentRepository.addStudentToTeacher(student.getId(), course.getTeacher().getId());
        }
    }

    @Override
    public void addQuiz(Course course, Quiz quiz) {
        List<Quiz> quizzes = new ArrayList<>();
        if (course.getQuizzes() != null) {
            quizzes.addAll(course.getQuizzes());
        }
        quizzes.add(quiz);
        quiz.setCourse(course);
        course.setQuizzes(quizzes);
        getRepository().save(course);
        if (course.getStudents() != null) {
            course.getStudents().forEach(student -> studentQuizService.addStudent(quiz, student));
        }
    }

    @Override
    public void addTeacher(Course course, Teacher teacher) {
        getRepository().addTeacherToCourse(teacher.getId(), course.getId());
        if (course.getStudents() != null) {
            //should I use repository or service????????????????????????????????????????????????
            course.getStudents().forEach(student -> teacherRepository.addStudentToTeacher(student.getId(), teacher.getId()));
        }
    }

    @Override
    public List<Person> getAllUsers(Course course) {
        List<Person> persons = new ArrayList<>(course.getStudents());
        persons.add(course.getTeacher());
        return persons;
    }
}
