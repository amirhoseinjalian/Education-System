package com.jalian.maktabfinalproject.repository;

import com.jalian.maktabfinalproject.entity.DescriptiveQuestion;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DescriptiveQuestionRepository extends QuestionRepository<DescriptiveQuestion> {
}
