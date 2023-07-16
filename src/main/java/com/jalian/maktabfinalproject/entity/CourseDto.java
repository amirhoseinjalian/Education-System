package com.jalian.maktabfinalproject.entity;

import lombok.*;

import java.sql.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {

    private String id;

    private String title;

    private Date beigining = new Date(System.currentTimeMillis());

    private Date ending;
}
