package com.jalian.maktabfinalproject.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
@Data
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

    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }
        BaseEntity baseEntity = (BaseEntity) o;
        return id == baseEntity.getId();
    }
}
