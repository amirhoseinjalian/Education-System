package com.jalian.maktabfinalproject.repository;

import com.jalian.maktabfinalproject.entity.Teacher;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface TeacherRepository extends PersonRepository<Teacher> {

}
