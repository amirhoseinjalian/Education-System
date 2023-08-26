package com.jalian.maktabfinalproject.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
/*@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
        property = "type")
@JsonSubTypes({
        //@JsonSubTypes.Type(value = Teacher.class, name = "teacher"),
        //@JsonSubTypes.Type(value = Student.class, name = "student"),
        @JsonSubTypes.Type(value = Person.class, name = "person"),
        //@JsonSubTypes.Type(value = Course.class, name = "course"),
        //@JsonSubTypes.Type(value = Role.class, name = "role"),
        @JsonSubTypes.Type(value = LongIdEntity.class, name = "id_entity")})*/
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public abstract class BaseEntity<Id> {

    @Transient
    private Id id;
}
