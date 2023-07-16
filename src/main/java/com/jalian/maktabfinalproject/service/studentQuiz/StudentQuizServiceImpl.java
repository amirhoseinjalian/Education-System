package com.jalian.maktabfinalproject.service.studentQuiz;

import com.jalian.maktabfinalproject.entity.*;
import com.jalian.maktabfinalproject.repository.StudentQuizRepository;
import com.jalian.maktabfinalproject.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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
    public Student getStudent(String studentId) {
        return studentQuizRepository.getStudent(studentId);
    }

    @Override
    public Quiz getQuiz(Long quizId) {
        return studentQuizRepository.getQuiz(quizId);
    }

    public AtomicReference<Double> getTestQuestionGrade(Student student, Quiz quiz) {
        AtomicReference<Double> grade = new AtomicReference<>(0.0);
        List<Answer> answers = student.getAnswers();
        List<TestAnswer> testAnswers = new ArrayList<>();
        answers.forEach(answer -> {if(answer instanceof TestAnswer) {testAnswers.add((TestAnswer) answer);}});
        List<QuizQuestionJoinTable> questions = quiz.getQuestions();
        List<TestQuestion> testQuestions = new ArrayList<>();
        questions.forEach(quizQuestionJoinTable -> {if(quizQuestionJoinTable.getQuestion() instanceof TestQuestion) {testQuestions.add((TestQuestion) quizQuestionJoinTable.getQuestion());};});
        testQuestions.forEach(testQuestion -> {testAnswers.forEach(testAnswer -> {if(testQuestion.getAnswer().equals(testAnswer)) {testQuestion.getQuizzes().forEach(quizQuestionJoinTable -> {if(quizQuestionJoinTable.getQuestion().equals(testQuestion)) {
            grade.updateAndGet(v -> v + quizQuestionJoinTable.getScore());
        }});}});});
        return grade;
    }

}
