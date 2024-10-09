package com.renfo_backend.Renfo_backend.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

import com.renfo_backend.Renfo_backend.dto.course.CourseDto;
import com.renfo_backend.Renfo_backend.dto.course.CourseDtoMapper;
import com.renfo_backend.Renfo_backend.dto.grade.GradeDto;
import com.renfo_backend.Renfo_backend.dto.grade.GradeDtoMapper;
import com.renfo_backend.Renfo_backend.dto.subject.SubjectDto;
import com.renfo_backend.Renfo_backend.dto.subject.SubjectDtoMapper;
import com.renfo_backend.Renfo_backend.dto.teacher.TeacherDto;
import com.renfo_backend.Renfo_backend.dto.teacher.TeacherDtoMapper;
import com.renfo_backend.Renfo_backend.entity.Teacher;
import com.renfo_backend.Renfo_backend.exceptions.TeacherNotFoundException;
import com.renfo_backend.Renfo_backend.repository.CourseRepository;
import com.renfo_backend.Renfo_backend.repository.TeacherRepository;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
    private final TeacherRepository repository;
    private final CourseRepository courseRepository;

    public TeacherController(TeacherRepository repository, CourseRepository courseRepository) {
        this.repository = repository;
        this.courseRepository = courseRepository;
    }

    @GetMapping
    public List<TeacherDto> getTeachers() {
        return repository.findAll().stream().map(TeacherDtoMapper::toDto).toList();
    }

    @PostMapping
    public TeacherDto newTeacher(@RequestBody TeacherDto teacherDto) {
        Teacher teacher = TeacherDtoMapper.toTeacher(teacherDto);

        Teacher newTeacher = repository.save(teacher);

        return TeacherDtoMapper.toDto(newTeacher);
    }

    @GetMapping("/{id}")
    public TeacherDto getTeacher(@PathVariable Long id) {
        return repository.findById(id).map(TeacherDtoMapper::toDto).orElseThrow(() -> new TeacherNotFoundException(id));
    }

    @GetMapping("/{id}/courses")
    public List<CourseDto> getCourses(@PathVariable Long id) {
        return courseRepository.findByTeacherId(id).stream().map(CourseDtoMapper::toDto).toList();
    }

    @GetMapping("/{id}/subjects")
    public Set<SubjectDto> getSubjects(@PathVariable Long id) {
        return courseRepository.findByTeacherId(id).stream().map(course -> SubjectDtoMapper.toDto(course.getSubject()))
                .collect(Collectors.toSet());
    }

    @GetMapping("/{id}/grades")
    public Set<GradeDto> getGrades(@PathVariable Long id) {
        return courseRepository.findByTeacherId(id).stream().map(course -> GradeDtoMapper.toDto(course.getGrade()))
                .collect(Collectors.toSet());
    }

    @PutMapping("/{id}")
    public TeacherDto replaceTeacher(@RequestBody TeacherDto newTeacher, @PathVariable Long id) {
        return TeacherDtoMapper.toDto(repository.findById(id)
                .map(teacher -> {
                    teacher.setFirstName(newTeacher.getFirstName());
                    teacher.setLastName(newTeacher.getLastName());

                    return repository.save(teacher);
                })
                .orElseGet(() -> {
                    return repository.save(TeacherDtoMapper.toTeacher(newTeacher));
                }));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteTeacher(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
