package com.jalian.maktabfinalproject.repository.util;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCourseRepository {

    @Query(value = "insert into student_course (student_id, course_id) values (:sId, :cId);", nativeQuery = true)
    @Modifying
    void addStudentToCourse(@Param("sId") String sId, @Param("cId") Long cId);
}
