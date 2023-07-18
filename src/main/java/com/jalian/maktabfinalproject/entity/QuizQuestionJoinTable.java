package com.jalian.maktabfinalproject.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "quiz_question")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizQuestionJoinTable extends BaseEntity<QuizQuestionKey> {

    @EmbeddedId
    private QuizQuestionKey quizQuestionKey;

    @ManyToOne
    @MapsId("quizId")
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @ManyToOne
    @MapsId("questionId")
    @JoinColumn(name = "question_id")
    private Question question;

    private Double score;
}
