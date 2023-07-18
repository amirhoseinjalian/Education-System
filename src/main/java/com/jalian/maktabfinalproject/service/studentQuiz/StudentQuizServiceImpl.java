package com.jalian.maktabfinalproject.service.studentQuiz;

import com.jalian.maktabfinalproject.entity.Answer;
import com.jalian.maktabfinalproject.entity.Quiz;
import com.jalian.maktabfinalproject.entity.Student;
import com.jalian.maktabfinalproject.entity.StudentQuiz;
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
public class StudentQuizServiceImpl extends BaseServiceImpl<StudentQuiz, Long, StudentQuizRepository> implements StudentQuizService {

    @Autowired
    private StudentQuizRepository studentQuizRepository;

    @Autowired
    public StudentQuizServiceImpl(StudentQuizRepository repository) {
        super(repository);
        studentQuizRepository = repository;
    }

    @Override
    public void joinedAQuiz(String studentId, Long quizId) {
        studentQuizRepository.joinedAQuiz(studentId, quizId);
    }

    @Override
    public List<Student> getStudentsOfAQuiz(Long quizId) {
        return studentQuizRepository.getStudentsOfAQuiz(quizId);
    }

    @Override
    public List<Student> getPassedStudents(Long quizId) {
        return studentQuizRepository.getPassedStudents(quizId);
    }

    @Override
    public List<Student> getStudents(Quiz quiz) {
        return studentQuizRepository.getStudents(quiz.getId());
    }

    @Override
    public List<Quiz> getQuizzes(Student student) {
        return studentQuizRepository.getQuizzes(student.getId());
    }
}
