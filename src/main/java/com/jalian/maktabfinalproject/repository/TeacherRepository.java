package com.jalian.maktabfinalproject.repository;

import com.jalian.maktabfinalproject.entity.Teacher;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface TeacherRepository extends PersonRepository<Teacher> {

    //bayad test beshe////////////////////////////////////////////////////////////////
    /*@Query("update Teacher t set t.courses = t.courses + #{#course}")
    void addCourse(@Param("course") Course course);*/
}
