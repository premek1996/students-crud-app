package com.app.demo.student.dto;

import com.app.demo.student.model.Student;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class AddStudentDTO {

    @NotBlank
    private final String name;

    @NotNull
    @Email
    private final String email;

    @NotNull
    private final LocalDate birthdate;

    public Student toStudent() {
        return Student.builder()
                .name(name)
                .email(email)
                .birthdate(birthdate)
                .build();
    }

}
