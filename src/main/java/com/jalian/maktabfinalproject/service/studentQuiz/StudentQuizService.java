package com.jalian.maktabfinalproject.service.studentQuiz;

import com.jalian.maktabfinalproject.entity.Quiz;
import com.jalian.maktabfinalproject.entity.Student;
import com.jalian.maktabfinalproject.entity.StudentQuiz;
import com.jalian.maktabfinalproject.entity.StudentQuizKey;
import com.jalian.maktabfinalproject.service.base.BaseService;

import java.util.List;

public interface StudentQuizService extends BaseService<StudentQuiz, StudentQuizKey> {

    void joinedAQuiz(String studentId, Long quizId);

    List<Student> getStudentsOfAQuiz(Long quizId);

    List<Student> getPassedStudents(Long quizId);

    List<Quiz> getQuizzesOfAStudent(Student student);
}
