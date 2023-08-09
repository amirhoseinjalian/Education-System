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
    public StudentQuizServiceImpl(StudentQuizRepository repository) {
        super(repository);
    }

    @Override
    public void joinedAQuiz(Student student, Quiz quiz) {
        getRepository().joinedAQuiz(student.getId(), quiz.getId());
    }

    @Override
    public List<Student> getStudentsOfAQuiz(Quiz quiz) {
        return getRepository().getStudentsOfAQuiz(quiz.getId());
    }

    @Override
    public List<Student> getPassedStudents(Quiz quiz) {
        return getRepository().getPassedStudents(quiz.getId());
    }

    @Override
    public List<Quiz> getQuizzesOfAStudent(Student student) {
        return getRepository().getQuizzesOfAStudent(student.getId());
    }

    @Override
    public void addStudent(Quiz quiz, Student student) {
        StudentQuiz studentQuiz = StudentQuiz.builder()
                .id(new StudentQuizKey(student.getId(), quiz.getId()))
                .student(student)
                .quiz(quiz)
                .build();
        studentQuiz = getRepository().save(studentQuiz);
        if (student.getQuizzes() != null) {
            student.getQuizzes().add(studentQuiz);
        }
        if (quiz.getStudents() != null) {
            quiz.getStudents().add(studentQuiz);
        }
        getRepository().save(studentQuiz);
    }
}
