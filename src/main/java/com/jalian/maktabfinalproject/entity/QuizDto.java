package com.jalian.maktabfinalproject.entity;

import lombok.*;

import java.sql.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizDto {

    private Long id;

    private String title;

    private String description;

    int time;

    private Date date;
}
