package com.renfo_backend.Renfo_backend.service;

import com.renfo_backend.Renfo_backend.dto.CreateStudentDto;
import com.renfo_backend.Renfo_backend.entity.Student;
import com.renfo_backend.Renfo_backend.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student addStudent(CreateStudentDto createStudentDto) {
        Student student = Student.from(createStudentDto);
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
