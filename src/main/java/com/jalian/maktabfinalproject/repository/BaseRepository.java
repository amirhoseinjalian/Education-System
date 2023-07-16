package com.jalian.maktabfinalproject.repository;

import com.jalian.maktabfinalproject.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
@EnableJpaRepositories(considerNestedRepositories = true)
public interface BaseRepository<Value extends BaseEntity<Key>, Key> extends JpaRepository<Value, Key> {
    //basic crud operation
}
