package com.jalian.maktabfinalproject.dto;

import com.jalian.maktabfinalproject.entity.Role;
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
