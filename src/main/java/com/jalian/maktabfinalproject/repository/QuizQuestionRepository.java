package com.jalian.maktabfinalproject.repository;

import com.jalian.maktabfinalproject.entity.Question;
import com.jalian.maktabfinalproject.entity.Quiz;
import com.jalian.maktabfinalproject.entity.QuizQuestionJoinTable;
import com.jalian.maktabfinalproject.entity.QuizQuestionKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface QuizQuestionRepository extends BaseRepository<QuizQuestionJoinTable, QuizQuestionKey> {

    //wrong
    //@Query("select s.question from QuizQuestionJoinTable s where s.quiz.course.id = :courseId and FUNCTION('CLASS', question) = :questionType"
    //@Query("select s.question from QuizQuestionJoinTable s where s.quiz.course.id = :courseId and type(s) = :questionType")    , 'DescriptiveQuestion.class'
    @Query("select s.question from QuizQuestionJoinTable s where s.quiz.course.id = :courseId AND type (s.question) = :questionType")
    <Value extends Question> List<Value> getQuestions(@Param("courseId") Long courseId, @Param("questionType") Class<Value> questionType);

    @Query("select s.quiz from QuizQuestionJoinTable s where s.question.id = :questionId")
    List<Quiz> getQuizzes(@Param("questionId") Long questionId);

    @Query("select s.question from QuizQuestionJoinTable s where s.quiz.id = :quizId")
    List<Question> getQuestions(@Param("quizId") Long quizId);

    @Query("select s.score from QuizQuestionJoinTable s where s.quiz.id = :quizId and s.question.id = :questionId")
    Double getScore(@Param("quizId") Long quizId, @Param("questionId") Long questionId);
}
