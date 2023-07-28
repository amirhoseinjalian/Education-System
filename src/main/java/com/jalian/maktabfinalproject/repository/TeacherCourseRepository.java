package com.jalian.maktabfinalproject.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherCourseRepository {

    @Query(value = "update course set teacher_username = :tId where id = :cId", nativeQuery = true)
    void addTeacherToCourse(@Param("tId") String teacherId, @Param("cId") Long courseId);
}
