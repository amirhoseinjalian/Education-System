package com.jalian.maktabfinalproject.service.course;

import com.jalian.maktabfinalproject.entity.*;
import com.jalian.maktabfinalproject.repository.CourseRepository;
import com.jalian.maktabfinalproject.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
//validation tu service bashe ya coontroller???????????????????????????????????????
public class CourseServiceImpl extends BaseServiceImpl<Course, Long, CourseRepository> implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository repository) {
        super(repository);
        courseRepository = repository;//////////////////////////////////////////
    }

    @Override
    @Transactional
    public void addStudent(Course course, List<Student> students) {
        List<Student> students1 = new ArrayList<>(students);
        students1.addAll(course.getStudents());
        course.setStudents(students1);
        courseRepository.save(course);
    }

    @Override
    public List<Quiz> getAllQuizzes(Course course) {
        return course.getQuizzes();
    }

    @Override
    public List<Quiz> addQuiz(Course course, Quiz quiz) {
        List<Quiz> quizzes = new ArrayList<>(course.getQuizzes());
        quizzes.add(quiz);
        course.setQuizzes(quizzes);
        courseRepository.save(course);
        return course.getQuizzes();
    }

    @Override
    public Course addTeacher(Course course, Teacher teacher) {
        course.setTeacher(teacher);
        return courseRepository.findById(course.getId()).get();
    }

    @Override
    public List<Person> getAllUsers(Course course) {
        List<Person> persons = new ArrayList<>(course.getStudents());
        persons.add(course.getTeacher());
        return persons;
    }
}
