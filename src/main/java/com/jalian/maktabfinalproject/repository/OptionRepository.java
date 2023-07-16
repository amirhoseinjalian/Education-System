package com.jalian.maktabfinalproject.repository;

import com.jalian.maktabfinalproject.entity.Option;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OptionRepository extends BaseRepository<Option, Long> {
}
