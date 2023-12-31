package com.jalian.maktabfinalproject.repository;

import com.jalian.maktabfinalproject.entity.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StudentRepository extends PersonRepository<Student> {

    @Query(value = "insert into student_course (student_id, course_id) values (:sId, :cId);", nativeQuery = true)
    @Modifying
    void addStudentToCourse(@Param("sId") String sId, @Param("cId") Long cId);

    @Query(value = "insert into student_teacher (student_id, teacher_id) values (:sId, :tId);", nativeQuery = true)
    @Modifying
    void addStudentToTeacher(@Param("sId") String sId, @Param("tId") String tId);
}
