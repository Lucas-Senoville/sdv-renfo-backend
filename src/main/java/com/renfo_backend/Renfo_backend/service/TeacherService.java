package com.renfo_backend.Renfo_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.renfo_backend.Renfo_backend.dto.teacher.TeacherDto;
import com.renfo_backend.Renfo_backend.dto.teacher.TeacherDtoMapper;
import com.renfo_backend.Renfo_backend.entity.Teacher;
import com.renfo_backend.Renfo_backend.repository.TeacherRepository;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher addTeacher(TeacherDto createTeacherDto) {
        Teacher teacher = TeacherDtoMapper.toTeacher(createTeacherDto);
        return teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }
}
