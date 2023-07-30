package com.jalian.maktabfinalproject.repository;

import com.jalian.maktabfinalproject.entity.Teacher;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface TeacherRepository extends PersonRepository<Teacher> {

    @Query(value = "insert into student_teacher (student_id, teacher_id) values (:sId, :tId);", nativeQuery = true)
    @Modifying
    void addStudentToTeacher(@Param("sId") String sId, @Param("tId") String tId);

    @Query(value = "update course set teacher_username = :tId where id = :cId", nativeQuery = true)
    @Modifying
    void addTeacherToCourse(@Param("tId") String teacherId, @Param("cId") Long courseId);
}
