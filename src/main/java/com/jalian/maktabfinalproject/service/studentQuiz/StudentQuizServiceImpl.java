package com.jalian.maktabfinalproject.service.studentQuiz;

import com.jalian.maktabfinalproject.entity.Quiz;
import com.jalian.maktabfinalproject.entity.Student;
import com.jalian.maktabfinalproject.entity.StudentQuiz;
import com.jalian.maktabfinalproject.entity.StudentQuizKey;
import com.jalian.maktabfinalproject.repository.StudentQuizRepository;
import com.jalian.maktabfinalproject.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class StudentQuizServiceImpl extends BaseServiceImpl<StudentQuiz, StudentQuizKey, StudentQuizRepository> implements StudentQuizService {

    @Autowired
    private StudentQuizRepository studentQuizRepository;

    @Autowired
    public StudentQuizServiceImpl(StudentQuizRepository repository) {
        super(repository);
        studentQuizRepository = repository;
    }

    @Override
    public void joinedAQuiz(Student student, Quiz quiz) {
        studentQuizRepository.joinedAQuiz(student.getId(), quiz.getId());
    }

    @Override
    public List<Student> getStudentsOfAQuiz(Quiz quiz) {
        return studentQuizRepository.getStudentsOfAQuiz(quiz.getId());
    }

    @Override
    public List<Student> getPassedStudents(Quiz quiz) {
        return studentQuizRepository.getPassedStudents(quiz.getId());
    }

    @Override
    public List<Quiz> getQuizzesOfAStudent(Student student) {
        return studentQuizRepository.getQuizzesOfAStudent(student.getId());
    }
}
