package com.jalian.maktabfinalproject.repository;

import com.jalian.maktabfinalproject.entity.TestAnswer;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TestAnswerRepository extends AnswerRepository<TestAnswer> {
}
