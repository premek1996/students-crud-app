package com.app.demo.student.controller;

import com.app.demo.student.dto.AddStudentDTO;
import com.app.demo.student.dto.StudentResponseDTO;
import com.app.demo.student.dto.UpdateStudentDTO;
import com.app.demo.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<StudentResponseDTO> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponseDTO addStudent(@Valid @RequestBody AddStudentDTO addStudentDTO) {
        return studentService.addStudent(addStudentDTO);
    }

    @PutMapping
    public StudentResponseDTO updateStudent(@Valid @RequestBody UpdateStudentDTO updateStudentDTO) {
        return studentService.updateStudent(updateStudentDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        studentService.deleteById(id);
    }

}
