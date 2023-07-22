package com.jalian.maktabfinalproject.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Role.class, name = "role"),
        @JsonSubTypes.Type(value = Course.class, name = "course"),
        @JsonSubTypes.Type(value = Quiz.class, name = "quiz"),
        @JsonSubTypes.Type(value = Question.class, name = "question"),
        @JsonSubTypes.Type(value = TestQuestion.class, name = "testQuestion"),
        @JsonSubTypes.Type(value = DescriptiveQuestion.class, name = "descriptiveQuestion"),
        @JsonSubTypes.Type(value = Option.class, name = "option"),
        @JsonSubTypes.Type(value = Answer.class, name = "answer"),
        @JsonSubTypes.Type(value = StudentQuiz.class, name = "studentQuiz"),
        @JsonSubTypes.Type(value = TestAnswer.class, name = "testAnswer"),
        @JsonSubTypes.Type(value = DescriptiveAnswer.class, name = "descriptiveAnswer")})
@JsonTypeName("id_entity")
public abstract class LongIdEntity extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LongIdEntity)) return false;
        /*if (!super.equals(o)) return false;*/
        LongIdEntity that = (LongIdEntity) o;
        return getId() != null ? getId().equals(that.getId()) : that.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
