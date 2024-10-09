package com.renfo_backend.Renfo_backend.controller;

import com.renfo_backend.Renfo_backend.dto.CreateTeacherDto;
import com.renfo_backend.Renfo_backend.entity.Teacher;
import com.renfo_backend.Renfo_backend.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<Teacher> getTeachers() {
        return teacherService.getTeachers();
    }

    @PostMapping
    public Teacher addTeacher(@RequestBody CreateTeacherDto createTeacherDto) {
        return teacherService.addTeacher(createTeacherDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
    }
}
