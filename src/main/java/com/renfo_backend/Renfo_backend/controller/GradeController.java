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

import com.renfo_backend.Renfo_backend.dto.course.CourseDto;
import com.renfo_backend.Renfo_backend.dto.course.CourseDtoMapper;
import com.renfo_backend.Renfo_backend.dto.grade.GradeDto;
import com.renfo_backend.Renfo_backend.dto.grade.GradeDtoMapper;
import com.renfo_backend.Renfo_backend.dto.grade.GradeWithStudentsDto;
import com.renfo_backend.Renfo_backend.dto.student.StudentDto;
import com.renfo_backend.Renfo_backend.dto.student.StudentDtoMapper;
import com.renfo_backend.Renfo_backend.entity.Grade;
import com.renfo_backend.Renfo_backend.exceptions.GradeNotFoundException;
import com.renfo_backend.Renfo_backend.repository.CourseRepository;
import com.renfo_backend.Renfo_backend.repository.GradeRepository;
import com.renfo_backend.Renfo_backend.repository.StudentRepository;

@RestController
@RequestMapping("/api/grades")
public class GradeController {
    private final GradeRepository repository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public GradeController(GradeRepository repository, CourseRepository courseRepository,
            StudentRepository studentRepository) {
        this.repository = repository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public List<GradeDto> getGrades() {
        return repository.findAll().stream().map(GradeDtoMapper::toDto).toList();
    }

    @PostMapping
    public GradeDto newGrade(@RequestBody GradeDto gradeDto) {
        Grade grade = GradeDtoMapper.toGrade(gradeDto);

        Grade newGrade = repository.save(grade);

        return GradeDtoMapper.toDto(newGrade);
    }

    @GetMapping("/{id}")
    public GradeWithStudentsDto getGrade(@PathVariable Long id) {
        return repository.findById(id).map(GradeDtoMapper::toDtoWithStudents)
                .orElseThrow(() -> new GradeNotFoundException(id));
    }

    @GetMapping("/{id}/courses")
    public List<CourseDto> getCourses(@PathVariable Long id) {
        return courseRepository.findByGradeId(id).stream().map(CourseDtoMapper::toDto).toList();
    }

    @GetMapping("/{id}/students")
    public List<StudentDto> getStudents(@PathVariable Long id) {
        return studentRepository.findByGradeId(id).stream().map(StudentDtoMapper::toDto).toList();
    }

    @PutMapping("/{id}")
    GradeDto replaceGrade(@RequestBody GradeDto newGrade, @PathVariable Long id) {
        return GradeDtoMapper.toDto(repository.findById(id)
                .map(grade -> {
                    grade.setName(newGrade.getName());

                    return repository.save(grade);
                })
                .orElseGet(() -> {
                    return repository.save(GradeDtoMapper.toGrade(newGrade));
                }));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteGrade(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
