package com.app.demo.student.service;

import com.app.demo.student.controller.dto.AddStudentDTO;
import com.app.demo.student.controller.dto.UpdateStudentDTO;
import com.app.demo.student.model.Student;
import com.app.demo.student.repository.StudentRepository;
import com.app.demo.student.service.exception.StudentServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.app.demo.utils.ExceptionMessage.EMAIL_ALREADY_EXISTS;
import static com.app.demo.utils.ExceptionMessage.STUDENT_DOES_NOT_EXIST_WITH_GIVEN_ID;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student addStudent(AddStudentDTO addStudentDTO) {
        var studentOptional = studentRepository
                .findStudentByEmail(addStudentDTO.getEmail());
        if (studentOptional.isPresent()) {
            throw new StudentServiceException(EMAIL_ALREADY_EXISTS);
        }
        return studentRepository.save(addStudentDTO.toStudent());
    }

    @Transactional
    public Student updateStudent(UpdateStudentDTO updateStudentDTO) {
        var student = studentRepository
                .findById(updateStudentDTO.getId())
                .orElseThrow(() -> new StudentServiceException(STUDENT_DOES_NOT_EXIST_WITH_GIVEN_ID));

        var studentWithNewEmailOptional = studentRepository
                .findStudentByEmail(updateStudentDTO.getEmail());

        if (studentWithNewEmailOptional.isPresent()) {
            throw new StudentServiceException(EMAIL_ALREADY_EXISTS);
        }

        student.setName(updateStudentDTO.getName());
        student.setEmail(updateStudentDTO.getEmail());

        return student;
    }


    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

}
