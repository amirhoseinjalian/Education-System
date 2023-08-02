package com.jalian.maktabfinalproject.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;


@Entity
@Table
@PrimaryKeyJoinColumn(name = "id")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TestAnswer.class, name = "testAnswer"),
        @JsonSubTypes.Type(value = DescriptiveAnswer.class, name = "descriptiveAnswer")})
@JsonTypeName("answer")
public abstract class Answer extends LongIdEntity {

    @ManyToOne(/*cascade = {CascadeType.MERGE, CascadeType.PERSIST}*/)
    @JoinColumns({
            @JoinColumn(name = "student_id"),
            @JoinColumn(name = "quiz_id")
    })
    private StudentQuiz studentQuiz;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "question_id")
    private Question question;
}
