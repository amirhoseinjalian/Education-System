package com.jalian.maktabfinalproject.repository;

import com.jalian.maktabfinalproject.entity.Quiz;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface QuizRepository extends BaseRepository<Quiz, Long> {

    @Query("select quiz from Quiz quiz join quiz.students s where s.student.id = :id and s.isJoined = false")
    List<Quiz> getAllowedQuizzes(@Param("id") String studentId);
}
