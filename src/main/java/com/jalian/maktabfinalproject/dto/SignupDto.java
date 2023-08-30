package com.jalian.maktabfinalproject.dto;

import com.jalian.maktabfinalproject.entity.RegistrationStatus;
import com.jalian.maktabfinalproject.entity.Role;
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
    private Date birthDate;
    private Role role;
    private RegistrationStatus status = RegistrationStatus.WAITING;
}
