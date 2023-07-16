package com.jalian.maktabfinalproject.repository;

import com.jalian.maktabfinalproject.entity.Answer;
import com.jalian.maktabfinalproject.entity.Quiz;
import com.jalian.maktabfinalproject.entity.Student;
import com.jalian.maktabfinalproject.entity.StudentQuiz;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface StudentQuizRepository extends BaseRepository<StudentQuiz, Long> {

    @Query("update StudentQuiz s set s.isJoined = true where s.student.id = :studentId and s.quiz.id = :quizId")
    void joinedAQuiz(@Param("studentId") String studentId, @Param("quizId") Long quizId);

    @Query("select s.student from StudentQuiz s where s.quiz.id = :quizId")
    List<Student> getStudentsOfAQuiz(@Param("quizId") Long quizId);

    @Query("select s.student from StudentQuiz s where s.score >= 10")
    List<Student> getPassedStudents(@Param("quizId") Long quizId);

    @Query("select s.student from StudentQuiz s where s.quiz.id = :quizId")
    List<Student> getStudents(@Param("quizId") Long quizId);

    @Query("select s.quiz from StudentQuiz s where s.student.id = :studentId")
    List<Quiz> getQuizzes(@Param("studentId") String studentId);

    @Query("select s.answers from StudentQuiz s where s.student.id = :studentId and s.quiz.id = :quizId")
    List<Answer> getAnswers(@Param("studentId") String studentId, @Param("quizId") Long quizId);
}
