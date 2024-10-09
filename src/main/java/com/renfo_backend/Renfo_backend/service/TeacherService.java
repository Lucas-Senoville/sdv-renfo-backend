package com.renfo_backend.Renfo_backend.service;

import com.renfo_backend.Renfo_backend.dto.CreateTeacherDto;
import com.renfo_backend.Renfo_backend.entity.Teacher;
import com.renfo_backend.Renfo_backend.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher addTeacher(CreateTeacherDto createTeacherDto) {
        Teacher teacher = Teacher.from(createTeacherDto);
        return teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }
}
