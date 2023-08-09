package com.jalian.maktabfinalproject.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
        property = "type")
@JsonTypeName("studentQuiz")
public class StudentQuiz extends BaseEntity<StudentQuizKey> {

    @EmbeddedId
    private StudentQuizKey id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @MapsId("quizId")
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @Builder.Default
    private Boolean isJoined = false;

    @Builder.Default
    private Double grade = 0.0;

    @OneToMany(mappedBy = "studentQuiz")
    private List<Answer> answers;
}
