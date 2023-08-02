package com.jalian.maktabfinalproject.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
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
