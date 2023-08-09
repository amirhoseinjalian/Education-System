package com.jalian.maktabfinalproject.repository;

import com.jalian.maktabfinalproject.entity.Course;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@DataJpaTest
@Transactional
//it has not done yet, native queries fail!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
public class CourseRepositoryTest extends BaseEntityRepositoryTest<Long, Course, CourseRepository> {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    protected CourseRepository repository() {
        return courseRepository;
    }

    @Override
    @BeforeEach
    protected void setup() {
        value = Course.builder()
                .title("java")
                .beginning(new Date(System.currentTimeMillis()))
                .ending(new Date(System.currentTimeMillis()))
                .build();
    }

    @Override
    protected Course newInstance() {
        return Course.builder()
                .title("AI with JAVA")
                .beginning(new Date(System.currentTimeMillis()))
                .ending(new Date(System.currentTimeMillis()))
                .build();
    }
}
