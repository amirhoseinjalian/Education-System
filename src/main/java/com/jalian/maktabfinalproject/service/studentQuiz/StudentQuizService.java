package com.jalian.maktabfinalproject.service.studentQuiz;

import com.jalian.maktabfinalproject.entity.Quiz;
import com.jalian.maktabfinalproject.entity.Student;
import com.jalian.maktabfinalproject.entity.StudentQuiz;
import com.jalian.maktabfinalproject.service.base.BaseService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentQuizService extends BaseService<StudentQuiz, Long> {

    void joinedAQuiz(String studentId, Long quizId);

    List<Student> getStudentsOfAQuiz(Long quizId);

    List<Student> getPassedStudents(Long quizId);

    Student getStudent(String studentId);

    Quiz getQuiz(Long quizId);
}
