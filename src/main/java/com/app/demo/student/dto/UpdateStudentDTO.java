package com.app.demo.student.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class UpdateStudentDTO {

    @NotNull
    private final Long id;

    @NotBlank
    private final String name;

    @NotNull
    @Email
    private final String email;

}
