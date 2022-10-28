package com.app.demo.student.dto;

import com.app.demo.student.model.Student;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class StudentResponseDTO {

    private Long id;
    private String name;
    private String email;
    private LocalDate birthdate;
    private Integer age;

    public static StudentResponseDTO of(Student student) {
        return StudentResponseDTO
                .builder()
                .id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .birthdate(student.getBirthdate())
                .age(student.getAge())
                .build();
    }

}
