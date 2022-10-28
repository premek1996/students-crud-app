package com.app.demo.student.dto;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class AddStudentDTOTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    @DisplayName("it should not fail when dto is correct")
    void test1() {
        var addStudentDTO = AddStudentDTO.builder()
                .name("name")
                .email("email@gmail.com")
                .birthdate(LocalDate.of(1996, 10, 12))
                .build();
        assertThat(validator.validate(addStudentDTO)).isEmpty();
    }

    @Test
    @DisplayName("it should not fail when name is missing")
    void test2() {
        var addStudentDTO = AddStudentDTO.builder()
                .email("email@gmail.com")
                .birthdate(LocalDate.of(1996, 10, 12))
                .build();
        assertThat(validator.validate(addStudentDTO)).isNotEmpty();
    }

    @Test
    @DisplayName("it should not fail when name is blank")
    void test3() {
        var addStudentDTO = AddStudentDTO.builder()
                .name("              ")
                .email("email@gmail.com")
                .birthdate(LocalDate.of(1996, 10, 12))
                .build();
        assertThat(validator.validate(addStudentDTO)).isNotEmpty();
    }

    @Test
    @DisplayName("it should not fail when email is missing")
    void test4() {
        var addStudentDTO = AddStudentDTO.builder()
                .name("name")
                .birthdate(LocalDate.of(1996, 10, 12))
                .build();
        assertThat(validator.validate(addStudentDTO)).isNotEmpty();
    }

    @Test
    @DisplayName("it should not fail when email is not correct")
    void test5() {
        var addStudentDTO = AddStudentDTO.builder()
                .name("name")
                .email("2434234")
                .birthdate(LocalDate.of(1996, 10, 12))
                .build();
        assertThat(validator.validate(addStudentDTO)).isNotEmpty();
    }

    @Test
    @DisplayName("it should not fail when birthdate is missing")
    void test6() {
        var addStudentDTO = AddStudentDTO.builder()
                .name("name")
                .email("email@gmail.com")
                .build();
        assertThat(validator.validate(addStudentDTO)).isNotEmpty();
    }

}