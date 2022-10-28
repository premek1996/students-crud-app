package com.app.demo.student.service;

import com.app.demo.exception.BadRequestException;
import com.app.demo.student.dto.AddStudentDTO;
import com.app.demo.student.dto.StudentResponseDTO;
import com.app.demo.student.dto.UpdateStudentDTO;
import com.app.demo.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.app.demo.exception.ExceptionMessage.EMAIL_ALREADY_EXISTS;
import static com.app.demo.exception.ExceptionMessage.STUDENT_DOES_NOT_EXIST_WITH_GIVEN_ID;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<StudentResponseDTO> getStudents() {
        return studentRepository.findAll()
                .stream()
                .map(StudentResponseDTO::of)
                .toList();
    }

    public StudentResponseDTO addStudent(AddStudentDTO addStudentDTO) {
        var studentOptional = studentRepository
                .findStudentByEmail(addStudentDTO.getEmail());
        if (studentOptional.isPresent()) {
            throw new BadRequestException(EMAIL_ALREADY_EXISTS);
        }
        var savedStudent = studentRepository.save(addStudentDTO.toStudent());
        return StudentResponseDTO.of(savedStudent);
    }

    @Transactional
    public StudentResponseDTO updateStudent(UpdateStudentDTO updateStudentDTO) {
        var student = studentRepository
                .findById(updateStudentDTO.getId())
                .orElseThrow(() -> new BadRequestException(STUDENT_DOES_NOT_EXIST_WITH_GIVEN_ID));

        var studentWithNewEmailOptional = studentRepository
                .findStudentByEmail(updateStudentDTO.getEmail());

        if (studentWithNewEmailOptional.isPresent()) {
            throw new BadRequestException(EMAIL_ALREADY_EXISTS);
        }

        student.setName(updateStudentDTO.getName());
        student.setEmail(updateStudentDTO.getEmail());

        return StudentResponseDTO.of(student);
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

}
