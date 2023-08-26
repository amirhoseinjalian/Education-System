package com.jalian.maktabfinalproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Setter
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuizQuestionKey implements Serializable {

    @Column(name = "quiz_id")
    private Long quizId;

    @Column(name = "question_id")
    private Long questionId;
}
