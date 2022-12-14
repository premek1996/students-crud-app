package com.app.demo.student.service;

import com.app.demo.exception.BadRequestException;
import com.app.demo.student.dto.AddStudentDTO;
import com.app.demo.student.dto.UpdateStudentDTO;
import com.app.demo.student.model.Student;
import com.app.demo.student.repository.StudentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static com.app.demo.exception.ExceptionMessage.EMAIL_ALREADY_EXISTS;
import static com.app.demo.exception.ExceptionMessage.STUDENT_DOES_NOT_EXIST_WITH_GIVEN_ID;
import static java.time.Month.JANUARY;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;


    @Test
    @DisplayName("it should throw exception when email already exists")
    void test1() {
        var email = "email1";

        var addStudentDTO = AddStudentDTO.builder()
                .name("student1")
                .email(email)
                .birthdate(LocalDate.of(2000, JANUARY, 5))
                .build();

        when(studentRepository.findStudentByEmail(email))
                .thenReturn(Optional.of(mock(Student.class)));

        assertThatThrownBy(() -> studentService.addStudent(addStudentDTO))
                .isInstanceOf(BadRequestException.class)
                .hasMessage(EMAIL_ALREADY_EXISTS);

        verify(studentRepository, times(1))
                .findStudentByEmail(email);

        verifyNoMoreInteractions(studentRepository);
    }

    @Test
    @DisplayName("it should add student")
    void test2() {
        var email = "email1";

        var addStudentDTO = AddStudentDTO.builder()
                .name("student1")
                .email(email)
                .birthdate(LocalDate.of(2000, JANUARY, 5))
                .build();

        var student = Student.builder()
                .id(1L)
                .name("student1")
                .email(email)
                .birthdate(LocalDate.of(2000, JANUARY, 5))
                .build();

        when(studentRepository.findStudentByEmail(email))
                .thenReturn(Optional.empty());

        when(studentRepository.save(addStudentDTO.toStudent()))
                .thenReturn(student);

        assertThatNoException()
                .isThrownBy(() -> studentService.addStudent(addStudentDTO));

        verify(studentRepository, times(1))
                .findStudentByEmail(email);

        verify(studentRepository, times(1))
                .save(any(Student.class));

        verifyNoMoreInteractions(studentRepository);
    }

    @Test
    @DisplayName("it should throw exception when student does not exist with given id")
    void test3() {
        var updateStudentDTO = UpdateStudentDTO.builder()
                .id(1L)
                .name("name")
                .email("email")
                .build();

        when(studentRepository.findById(updateStudentDTO.getId()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> studentService.updateStudent(updateStudentDTO))
                .isInstanceOf(BadRequestException.class)
                .hasMessage(STUDENT_DOES_NOT_EXIST_WITH_GIVEN_ID);

        verify(studentRepository, times(1))
                .findById(updateStudentDTO.getId());

        verifyNoMoreInteractions(studentRepository);
    }

    @Test
    @DisplayName("it should throw exception when email already exists")
    void test4() {
        var updateStudentDTO = UpdateStudentDTO.builder()
                .id(1L)
                .name("name")
                .email("email")
                .build();

        var student = Student.builder()
                .id(1L)
                .name("old name")
                .email("old email")
                .birthdate(LocalDate.of(2000, JANUARY, 5))
                .build();

        when(studentRepository.findById(updateStudentDTO.getId()))
                .thenReturn(Optional.of(student));

        when(studentRepository.findStudentByEmail(updateStudentDTO.getEmail()))
                .thenReturn(Optional.of(mock(Student.class)));

        assertThatThrownBy(() -> studentService.updateStudent(updateStudentDTO))
                .isInstanceOf(BadRequestException.class)
                .hasMessage(EMAIL_ALREADY_EXISTS);

        verify(studentRepository, times(1))
                .findById(updateStudentDTO.getId());

        verify(studentRepository, times(1))
                .findStudentByEmail(updateStudentDTO.getEmail());

        verifyNoMoreInteractions(studentRepository);
    }

    @Test
    @DisplayName("it should update student")
    void test5() {
        var id = 1L;
        var updateStudentDTO = UpdateStudentDTO.builder()
                .id(id)
                .name("name")
                .email("email")
                .build();

        var student = Student.builder()
                .id(id)
                .name("old name")
                .email("old email")
                .birthdate(LocalDate.of(2000, JANUARY, 5))
                .build();

        when(studentRepository.findById(updateStudentDTO.getId()))
                .thenReturn(Optional.of(student));

        when(studentRepository.findStudentByEmail(updateStudentDTO.getEmail()))
                .thenReturn(Optional.empty());

        var updatedStudent = Student.builder()
                .id(id)
                .name(updateStudentDTO.getName())
                .email(updateStudentDTO.getEmail())
                .birthdate(student.getBirthdate())
                .build();

        assertThat(studentService.updateStudent(updateStudentDTO))
                .usingRecursiveComparison()
                .isEqualTo(updatedStudent);

        verify(studentRepository, times(1))
                .findById(updateStudentDTO.getId());

        verify(studentRepository, times(1))
                .findStudentByEmail(updateStudentDTO.getEmail());

        verifyNoMoreInteractions(studentRepository);
    }

}