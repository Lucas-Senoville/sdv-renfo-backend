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
import com.renfo_backend.Renfo_backend.dto.subject.SubjectDto;
import com.renfo_backend.Renfo_backend.dto.subject.SubjectDtoMapper;
import com.renfo_backend.Renfo_backend.dto.teacher.TeacherDto;
import com.renfo_backend.Renfo_backend.dto.teacher.TeacherDtoMapper;
import com.renfo_backend.Renfo_backend.entity.Subject;
import com.renfo_backend.Renfo_backend.exceptions.SubjectNotFoundException;
import com.renfo_backend.Renfo_backend.repository.CourseRepository;
import com.renfo_backend.Renfo_backend.repository.SubjectRepository;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {
    private final SubjectRepository repository;
    private final CourseRepository courseRepository;

    public SubjectController(SubjectRepository repository, CourseRepository courseRepository) {
        this.repository = repository;
        this.courseRepository = courseRepository;
    }

    @GetMapping
    public List<SubjectDto> getSubjects() {
        return repository.findAll().stream().map(SubjectDtoMapper::toDto).toList();
    }

    @PostMapping
    public SubjectDto newSubject(@RequestBody SubjectDto subjectdDto) {
        Subject subject = SubjectDtoMapper.toSubject(subjectdDto);

        Subject newSubject = repository.save(subject);

        return SubjectDtoMapper.toDto(newSubject);
    }

    @GetMapping("/{id}")
    public SubjectDto getSubject(@PathVariable Long id) {
        return repository.findById(id).map(SubjectDtoMapper::toDto).orElseThrow(() -> new SubjectNotFoundException(id));
    }

    @GetMapping("/{id}/teachers")
    public Set<TeacherDto> getTeachers(@PathVariable Long id) {
        return courseRepository.findBySubjectId(id).stream().map(course -> TeacherDtoMapper.toDto(course.getTeacher()))
                .collect(Collectors.toSet());
    }

    @GetMapping("/{id}/courses")
    public Set<CourseDto> getCourses(@PathVariable Long id) {
        return courseRepository.findBySubjectId(id).stream().map(CourseDtoMapper::toDto)
                .collect(Collectors.toSet());
    }

    @PutMapping("/{id}")
    SubjectDto replaceSubject(@RequestBody SubjectDto newSubject, @PathVariable Long id) {
        return SubjectDtoMapper.toDto(repository.findById(id)
                .map(subject -> {
                    subject.setName(newSubject.getName());

                    return repository.save(subject);
                })
                .orElseGet(() -> {
                    return repository.save(SubjectDtoMapper.toSubject(newSubject));
                }));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteSubject(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
