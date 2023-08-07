package com.jalian.maktabfinalproject.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "id")
/*
should be define in the root entity!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
@DiscriminatorColumn(name = "Question_TYPE", discriminatorType = DiscriminatorType.STRING)
*/
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DescriptiveQuestion.class, name = "descriptiveQuestion"),
        @JsonSubTypes.Type(value = TestQuestion.class, name = "testQuestion")})
@JsonTypeName("question")
public abstract class Question extends LongIdEntity {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    List<QuizQuestionJoinTable> quizzes;

    //behtar nist yek paper entity dar nazar begirim va in 2 fild azmun va soal ro dar un bezarim ta code kamtari bezanim??????????????????????
    @Column(nullable = false)
    private String title;

    private String description;

    @OneToOne(mappedBy = "question", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Answer answer;
}
