package com.app.demo.student.controller;

import com.app.demo.exception.BadRequestException;
import com.app.demo.student.dto.AddStudentDTO;
import com.app.demo.student.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @MockBean
    private StudentService studentService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("it should fail when dto is missing")
    @SneakyThrows
    void test1() {
        mockMvc.perform(post("/api/v1/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("it should fail when dto is missing")
    @SneakyThrows
    void test2() {
        mockMvc.perform(put("/api/v1/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("it should handle bad request exception")
    @SneakyThrows
    void test3() {
        var addStudentDTO = AddStudentDTO.builder()
                .name("name")
                .email("email@gmail.com")
                .birthdate(LocalDate.of(1996, 10, 12))
                .build();

        when(studentService.addStudent(addStudentDTO))
                .thenThrow(new BadRequestException(anyString()));

        mockMvc.perform(post("/api/v1/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addStudentDTO)))
                .andExpect(status().isBadRequest());
    }

}