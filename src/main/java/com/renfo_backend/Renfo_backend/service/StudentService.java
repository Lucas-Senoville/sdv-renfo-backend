package com.renfo_backend.Renfo_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.renfo_backend.Renfo_backend.dto.student.StudentDto;
import com.renfo_backend.Renfo_backend.dto.student.StudentDtoMapper;
import com.renfo_backend.Renfo_backend.entity.Student;
import com.renfo_backend.Renfo_backend.repository.StudentRepository;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student addStudent(StudentDto createStudentDto) {
        Student student = StudentDtoMapper.toStudent(createStudentDto);
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
