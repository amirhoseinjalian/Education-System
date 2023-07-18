package com.jalian.maktabfinalproject.repository;

import com.jalian.maktabfinalproject.entity.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;

@NoRepositoryBean
@EnableJpaRepositories(considerNestedRepositories = true)
public interface QuestionRepository<Value extends Question> extends BaseRepository<Value, Long> {

    //bayad test beshe///////////////////////////////////////////////////////////////////////////////
    @Query("select question from #{#entityName} question join question.quizzes q where q.quiz.course.id = :id")
    List<Value> questionBank(@Param("id") Long id);

    @Query("select question from #{#entityName} question join question.quizzes q where q.quiz.id = :id")
    List<Value> getQuestions(@Param("id") Long id);
}
