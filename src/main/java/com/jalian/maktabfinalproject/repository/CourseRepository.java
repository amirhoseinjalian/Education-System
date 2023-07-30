package com.jalian.maktabfinalproject.repository;

import com.jalian.maktabfinalproject.entity.Course;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CourseRepository extends BaseRepository<Course, Long> {

    @Query(value = "insert into student_course (student_id, course_id) values (:sId, :cId);", nativeQuery = true)
    @Modifying
    void addStudentToCourse(@Param("sId") String sId, @Param("cId") Long cId);

    @Query(value = "update course set teacher_username = :tId where id = :cId", nativeQuery = true)
    void addTeacherToCourse(@Param("tId") String teacherId, @Param("cId") Long courseId);
}
