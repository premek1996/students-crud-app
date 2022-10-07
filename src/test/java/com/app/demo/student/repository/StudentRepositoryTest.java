package com.app.demo.student.repository;

import com.app.demo.student.model.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.JANUARY;
import static java.time.Month.MAY;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    @DisplayName("it should find student by email")
    void test1() {

        var email = "email1";

        var student1 = Student.builder()
                .name("student1")
                .email(email)
                .birthdate(LocalDate.of(2000, JANUARY, 5))
                .build();

        var student2 = Student.builder()
                .name("student2")
                .email("email2")
                .birthdate(LocalDate.of(2004, MAY, 15))
                .build();

        studentRepository.saveAll(List.of(student1, student2));

        assertThat(studentRepository.findStudentByEmail(email))
                .get()
                .usingRecursiveComparison()
                .isEqualTo(student1);
    }

}