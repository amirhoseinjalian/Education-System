package com.jalian.maktabfinalproject.entity;

import lombok.*;

import java.sql.Date;

@Setter
@Getter
@Builder@AllArgsConstructor
@NoArgsConstructor
public class SignupDto {
    String type;
    private String firstName;
    private String lastName;
    private String id;
    private String password;
    private Date birthdate;
    private Role role;
    private RegistrationStatus status = RegistrationStatus.WAITING;
}
