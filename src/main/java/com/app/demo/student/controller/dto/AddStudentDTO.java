package com.app.demo.student.controller.dto;

import com.app.demo.student.model.Student;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@Builder
public class AddStudentDTO {

    private final String name;
    private final String email;
    private final LocalDate birthdate;

    public Student toStudent() {
        return Student.builder()
                .name(name)
                .email(email)
                .birthdate(birthdate)
                .build();
    }

}
