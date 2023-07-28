package com.jalian.maktabfinalproject.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentTeacherRepository {

    @Query(value = "insert into student_teacher (student_id, teacher_id) values (:sId, :tId);", nativeQuery = true)
    @Modifying
    void addStudentToTeacher(@Param("sId") String sId, @Param("tId") String tId);
}
