package com.jalian.maktabfinalproject.service.quiz;

import com.jalian.maktabfinalproject.entity.*;
import com.jalian.maktabfinalproject.repository.QuizRepository;
import com.jalian.maktabfinalproject.service.answer.testAnswer.TestAnswerService;
import com.jalian.maktabfinalproject.service.base.BaseServiceImpl;
import com.jalian.maktabfinalproject.service.question.testQuestion.TestQuestionService;
import com.jalian.maktabfinalproject.service.quizQuestion.QuizQuestionService;
import com.jalian.maktabfinalproject.service.studentQuiz.StudentQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class QuizServiceImpl extends BaseServiceImpl<Quiz, Long, QuizRepository> implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private StudentQuizService studentQuizService;

    //eshkali nadare service ha beham vabaste and??????????????????????????????????????????????????
    @Autowired
    private TestQuestionService testQuestionService;

    @Autowired
    private TestAnswerService testAnswerService;

    @Autowired
    private QuizQuestionService quizQuestionService;

    public QuizServiceImpl(QuizRepository repository) {
        super(repository);
    }

    /*@Override
    public List<Question> questionBank(Long id) {
        return quizRepository.questionBank(id);
    }*/

    @Override
    public void addQuestion(Quiz quiz, Question question, Double score) {
        QuizQuestionJoinTable quizQuestionJoinTable = new QuizQuestionJoinTable(new QuizQuestionKey(quiz.getId(), question.getId()), quiz, question, score);
        List<QuizQuestionJoinTable> questionJoinTables = new ArrayList<>(quiz.getQuestions());
        questionJoinTables.add(quizQuestionJoinTable);
        quiz.setQuestions(questionJoinTables);
        quizRepository.save(quiz);
    }

    @Override
    public Map<String, Double> correctTestQuestion(Student student, Quiz quiz) throws Exception {
        final Double[] grade = {0.0};
        StudentQuiz studentQuiz = studentQuizService.findById(new StudentQuizKey(student.getId(), quiz.getId()))
                .orElseThrow(() -> new NullPointerException("student or quiz not found"));
        List<TestAnswer> answers = testAnswerService.getAnswers(student, quiz);
        List<TestQuestion> questions = quizQuestionService.<TestQuestion>getQuestions(quiz, "TestQuestion");
        List<TestAnswer> correctAnswers = new ArrayList<>();
        questions.forEach(question -> {
                correctAnswers.add((TestAnswer) question.getAnswer());
        });
        correctAnswers.forEach(testAnswer -> {
            answers.forEach(testAnswer1 -> {
                if (testAnswer.equals(testAnswer1)) {
                    grade[0] += quizQuestionService.findById(new QuizQuestionKey(quiz.getId(), testAnswer.getQuestion().getId())).get().getScore();
                }
            });
        });
        studentQuiz.setGrade(grade[0]);
        studentQuizService.save(studentQuiz);
        Map<String, Double> map = new LinkedHashMap<>();
        map.put(student.getId(), grade[0]);
        return map;
    }

    @Override
    public List<Quiz> getAllowedQuizzes(Student student) {
        return quizRepository.getAllowedQuizzes(student.getId());
    }
}
