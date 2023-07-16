package com.jalian.maktabfinalproject.service.studentQuiz;

import com.jalian.maktabfinalproject.entity.Answer;
import com.jalian.maktabfinalproject.entity.Quiz;
import com.jalian.maktabfinalproject.entity.Student;
import com.jalian.maktabfinalproject.entity.StudentQuiz;
import com.jalian.maktabfinalproject.service.base.BaseService;

import java.util.List;

public interface StudentQuizService extends BaseService<StudentQuiz, Long> {

    void joinedAQuiz(String studentId, Long quizId);

    List<Student> getStudentsOfAQuiz(Long quizId);

    List<Student> getPassedStudents(Long quizId);

    List<Student> getStudents(Quiz quiz);

    List<Quiz> getQuizzes(Student student);

    List<Answer> getAnswers(Student student, Quiz quiz);
}
