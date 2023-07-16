package com.jalian.maktabfinalproject.repository;

import com.jalian.maktabfinalproject.entity.TestQuestion;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TestQuestionRepository extends QuestionRepository<TestQuestion> {
}
