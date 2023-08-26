package com.jalian.maktabfinalproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode//needed!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
@Builder
public class StudentQuizKey implements Serializable {

    @Column(name = "student_id")
    private String studentId;

    @Column(name = "quiz_id")
    private Long quizId;
}
