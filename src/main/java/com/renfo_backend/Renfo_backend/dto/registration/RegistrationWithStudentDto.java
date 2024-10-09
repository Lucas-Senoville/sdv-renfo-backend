package com.renfo_backend.Renfo_backend.dto.registration;

import java.time.LocalDate;

import com.renfo_backend.Renfo_backend.dto.student.StudentDto;
import com.renfo_backend.Renfo_backend.dto.student.StudentDtoMapper;
import com.renfo_backend.Renfo_backend.entity.Student;

public class RegistrationWithStudentDto extends RegistrationDto {
    private StudentDto student;

    public RegistrationWithStudentDto() {
        super();
    }

    public RegistrationWithStudentDto(FullRegistrationDto fullRegistrationDto) {
        super(fullRegistrationDto);
        this.student = fullRegistrationDto.getStudent();
    }

    public RegistrationWithStudentDto(RegistrationDto registrationDto) {
        super(registrationDto);
    }

    public RegistrationWithStudentDto(Long id, LocalDate date, Student student) {
        super(id, date);
        this.setStudent(student);
    }

    public StudentDto getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = StudentDtoMapper.toDto(student);
    }
}
