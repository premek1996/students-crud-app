package com.app.demo.student.config;

import com.app.demo.student.model.Student;
import com.app.demo.student.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.JANUARY;
import static java.time.Month.MAY;

@Configuration
public class StudentConfig {

    @Bean
    public CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            var student1 = Student.builder()
                    .name("student1")
                    .email("email1")
                    .birthdate(LocalDate.of(2000, JANUARY, 5))
                    .build();
            var student2 = Student.builder()
                    .name("student2")
                    .email("email2")
                    .birthdate(LocalDate.of(2004, MAY, 15))
                    .build();
            studentRepository.saveAll(List.of(student1, student2));
        };
    }

}
