package com.jalian.maktabfinalproject.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@PrimaryKeyJoinColumn(name = "username")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
        property = "type")
@JsonTypeName("student")
public class Student extends Person {

    @ManyToMany/*(cascade = CascadeType.ALL)*///{CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    List<Course> courses;

    @ManyToMany(mappedBy = "students")
    private List<Teacher> teachers;

    @OneToMany(mappedBy = "student")
    private List<StudentQuiz> quizzes;

    @OneToMany(mappedBy = "student")
    private List<Answer> answers;

    @Override
    public String toString() {
        return "Student{" +
                "id='" + getId() + '\'' +
                ", password='" + getPassword() + '\'' +
                '}';
    }
}