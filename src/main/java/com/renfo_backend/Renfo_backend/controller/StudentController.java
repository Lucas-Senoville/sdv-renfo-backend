package com.renfo_backend.Renfo_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.renfo_backend.Renfo_backend.dto.registration.RegistrationDtoMapper;
import com.renfo_backend.Renfo_backend.dto.registration.RegistrationWithLessonDto;
import com.renfo_backend.Renfo_backend.dto.student.StudentDto;
import com.renfo_backend.Renfo_backend.dto.student.StudentDtoMapper;
import com.renfo_backend.Renfo_backend.dto.student.StudentWithGradeDto;
import com.renfo_backend.Renfo_backend.entity.Student;
import com.renfo_backend.Renfo_backend.exceptions.StudentNotFoundException;
import com.renfo_backend.Renfo_backend.repository.RegistrationRepository;
import com.renfo_backend.Renfo_backend.repository.StudentRepository;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentRepository repository;
    private final RegistrationRepository registrationRepository;

    public StudentController(StudentRepository repository, RegistrationRepository registrationRepository) {
        this.repository = repository;
        this.registrationRepository = registrationRepository;
    }

    @GetMapping
    public List<StudentDto> getStudents() {
        return repository.findAll().stream().map(StudentDtoMapper::toDto).toList();
    }

    @PostMapping
    public StudentDto newStudent(@RequestBody StudentWithGradeDto studentDto) {
        Student student = StudentDtoMapper.toStudent(studentDto);

        Student newStudent = repository.save(student);

        return StudentDtoMapper.toDto(newStudent);
    }

    @GetMapping("/{id}")
    public StudentWithGradeDto getStudent(@PathVariable Long id) {
        return repository.findById(id).map(StudentDtoMapper::toDtoWithGrade)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    @GetMapping("/{id}/registrations")
    public List<RegistrationWithLessonDto> getRegistrations(@PathVariable Long id) {
        return registrationRepository.findByStudentId(id).stream().map(RegistrationDtoMapper::toDtoWithLesson)
                .toList();
    }

    @PutMapping("/{id}")
    StudentDto replaceStudent(@RequestBody StudentDto newStudent, @PathVariable Long id) {
        return StudentDtoMapper.toDto(repository.findById(id)
                .map(student -> {
                    student.setFirstName(newStudent.getFirstName());
                    student.setLastName(newStudent.getLastName());

                    return repository.save(student);
                })
                .orElseGet(() -> repository.save(StudentDtoMapper.toStudent(newStudent))));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
