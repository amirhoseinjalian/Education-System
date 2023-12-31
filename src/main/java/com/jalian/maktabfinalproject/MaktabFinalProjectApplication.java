package com.jalian.maktabfinalproject;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EnableWebSecurity
public class MaktabFinalProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaktabFinalProjectApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
