package com.jalian.maktabfinalproject.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@Setter
@Getter
@Builder@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
        property = "type")
@JsonTypeName("role")
public class Role extends LongIdEntity {

    @Enumerated(EnumType.STRING)
    @Column(length = 20, columnDefinition = "enum('ADMIN','TEACHER','STUDENT') DEFAULT 'STUDENT'")
    @JsonProperty
    private RoleNames name;

    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private List<Person> users;
}

