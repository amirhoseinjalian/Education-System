package com.jalian.maktabfinalproject.entity;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {

    private String id;

    private Role role;
}
