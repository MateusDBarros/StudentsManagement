package com.example.demo.student;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;


@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository)  {
        return args -> {
            Student mateus = new Student(
                    "Mateus",
                    "mateomb@gmail.com",
                    LocalDate.of(2002, Month.JULY, 13)
                    );

            Student alex = new Student(
                    "Alex",
                    "aleximus@gmail.com",
                    LocalDate.of(2001, Month.JULY, 24)
            );

            repository.saveAll(
                    List.of(mateus, alex)
            );
        };
    }
}
