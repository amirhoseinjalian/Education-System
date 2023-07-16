package com.jalian.maktabfinalproject.repository;

import com.jalian.maktabfinalproject.entity.Course;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CourseRepository extends BaseRepository<Course, Long> {

}
