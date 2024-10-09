package com.renfo_backend.Renfo_backend.controller;

import com.renfo_backend.Renfo_backend.dto.CreateStudentDto;
import com.renfo_backend.Renfo_backend.entity.Student;
import com.renfo_backend.Renfo_backend.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public Student addStudent(@RequestBody CreateStudentDto createStudentDto) {
        return studentService.addStudent(createStudentDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}
