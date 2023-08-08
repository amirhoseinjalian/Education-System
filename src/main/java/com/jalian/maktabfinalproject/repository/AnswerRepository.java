package com.jalian.maktabfinalproject.repository;

import com.jalian.maktabfinalproject.entity.Answer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;

@NoRepositoryBean
@EnableJpaRepositories(considerNestedRepositories = true)
public interface AnswerRepository<Value extends Answer> extends BaseRepository<Value, Long> {

    @Query("select a from #{#entityName} a where a.studentQuiz.student.id = :studentId and a.studentQuiz.quiz.id = :quizId")
    List<Value> getAnswers(@Param("studentId") String studentId, @Param("quizId") Long quizId);

    @Query("select a from Answer a where a.studentQuiz.student.id = :studentId and a.studentQuiz.quiz.id = :quizId")
    List<Answer> getAllAnswers(@Param("studentId") String studentId, @Param("quizId") Long quizId);
}
