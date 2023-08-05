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
@PrimaryKeyJoinColumn(name = "id")
//@Polymorphism(type = PolymorphismType.EXPLICIT)
@DiscriminatorValue("TestQuestion")
@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
        property = "type")
@JsonTypeName("testQuestion")
public class TestQuestion extends Question {

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Option> options;
}
