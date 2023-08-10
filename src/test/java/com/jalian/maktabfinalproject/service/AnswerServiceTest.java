package com.jalian.maktabfinalproject.service;

import com.jalian.maktabfinalproject.entity.*;
import com.jalian.maktabfinalproject.repository.AnswerRepository;
import com.jalian.maktabfinalproject.service.answer.AnswerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public abstract class AnswerServiceTest<Value extends Answer, Repository extends AnswerRepository<Value>, Service extends AnswerService<Value>>
        extends BaseEntityServiceTest<Long, Value, Repository, Service> {

    @Test
    void getAnswersTest() {
        Student student = Student.builder().
                firstName("amirhosein").
                lastName("jalian").
                password("1382").
                id("aj").
                courses(new ArrayList<>()).
                birthDate(new Date(System.currentTimeMillis())).
                role(new Role(RoleNames.STUDENT))
                .build();
        Quiz quiz = Quiz.builder().
                id(2L).
                description("this is quiz 1").
                date(new Date(System.currentTimeMillis())).
                time(2).
                title("quiz 1").
                build();
        List<Value> answers = List.of(value, newInstance());
        given(repository.getAnswers(student.getId(), quiz.getId())).willReturn(answers);
        List<Value> values = service.getAnswers(student, quiz);
        assertThat(values).isEqualTo(answers);
    }
}
