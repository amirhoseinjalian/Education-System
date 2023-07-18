package com.jalian.maktabfinalproject.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentQuizKey {

    @Column(name = "student_id")
    private String studentId;

    @Column(name = "quiz_id")
    private Long quizId;
}
