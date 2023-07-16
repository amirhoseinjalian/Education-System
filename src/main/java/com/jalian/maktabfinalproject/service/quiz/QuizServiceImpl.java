package com.jalian.maktabfinalproject.service.quiz;

import com.jalian.maktabfinalproject.entity.*;
import com.jalian.maktabfinalproject.repository.QuizRepository;
import com.jalian.maktabfinalproject.service.base.BaseServiceImpl;
import com.jalian.maktabfinalproject.service.studentQuiz.StudentQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class QuizServiceImpl extends BaseServiceImpl<Quiz, Long, QuizRepository> implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private StudentQuizService studentQuizService;

    public QuizServiceImpl(QuizRepository repository) {
        super(repository);
    }

    @Override
    public List<Question> questionBank(Long id) {
        return quizRepository.questionBank(id);
    }

    @Override
    public void addQuestion(Quiz quiz, Question question, Double score) {
        QuizQuestionJoinTable quizQuestionJoinTable = new QuizQuestionJoinTable(new QuizQuestionKey(quiz.getId(), question.getId()), quiz, question, score);
        List<QuizQuestionJoinTable> questionJoinTables = new ArrayList<>(quiz.getQuestions());
        questionJoinTables.add(quizQuestionJoinTable);
        quiz.setQuestions(questionJoinTables);
        quizRepository.save(quiz);
    }

    @Override
    public double correctTestQuestion(Student student, Quiz quiz) throws Exception {
        double grade = 0;
        List<Student> students = studentQuizService.getStudents(quiz);
        if(!students.contains(student)) {
            throw new Exception("Student not found");
        }
        List<Answer> answers = student.getAnswers();

        return 0;
    }

    @Override
    public List<Quiz> getAllowedQuizzes(Student student) {
        return quizRepository.getAllowedQuizzes(student.getId());
    }
}
