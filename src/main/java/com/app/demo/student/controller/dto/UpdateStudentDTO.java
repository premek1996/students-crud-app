package com.app.demo.student.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public class UpdateStudentDTO {

    private final Long id;
    private final String name;
    private final String email;

}
