package com.jalian.maktabfinalproject.repository;

import com.jalian.maktabfinalproject.entity.Answer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
@EnableJpaRepositories(considerNestedRepositories = true)
public interface AnswerRepository<Value extends Answer> extends BaseRepository<Value, Long> {
}
