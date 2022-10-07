package com.app.demo.student.controller;

import com.app.demo.student.controller.dto.AddStudentDTO;
import com.app.demo.student.controller.dto.UpdateStudentDTO;
import com.app.demo.student.model.Student;
import com.app.demo.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public Student addStudent(@RequestBody AddStudentDTO addStudentDTO) {
        return studentService.addStudent(addStudentDTO);
    }

    @PutMapping
    public Student updateStudent(@RequestBody UpdateStudentDTO updateStudentDTO) {
        return studentService.updateStudent(updateStudentDTO);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        studentService.deleteById(id);
    }

}
