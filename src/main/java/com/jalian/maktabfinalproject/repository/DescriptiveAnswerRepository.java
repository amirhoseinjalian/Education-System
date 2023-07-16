package com.jalian.maktabfinalproject.repository;

import com.jalian.maktabfinalproject.entity.DescriptiveAnswer;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DescriptiveAnswerRepository extends AnswerRepository<DescriptiveAnswer> {
}