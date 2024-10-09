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
import com.renfo_backend.Renfo_backend.dto.grade.GradeDtoMapper;
import com.renfo_backend.Renfo_backend.dto.lesson.LessonDto;
import com.renfo_backend.Renfo_backend.dto.lesson.LessonDtoMapper;
import com.renfo_backend.Renfo_backend.dto.subject.SubjectDtoMapper;
import com.renfo_backend.Renfo_backend.dto.teacher.TeacherDtoMapper;
import com.renfo_backend.Renfo_backend.entity.Course;
import com.renfo_backend.Renfo_backend.exceptions.CourseNotFoundException;
import com.renfo_backend.Renfo_backend.repository.CourseRepository;
import com.renfo_backend.Renfo_backend.repository.LessonRepository;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseRepository repository;
    private final LessonRepository lessonRepository;

    public CourseController(CourseRepository repository, LessonRepository lessonRepository) {
        this.repository = repository;
        this.lessonRepository = lessonRepository;
    }

    @GetMapping
    public List<CourseDto> getCourses() {
        return repository.findAll().stream().map(CourseDtoMapper::toDto).toList();
    }

    @PostMapping
    public CourseDto newCourse(@RequestBody CourseDto courseDto) {
        Course course = CourseDtoMapper.toCourse(courseDto);

        Course newCourse = repository.save(course);

        return CourseDtoMapper.toDto(newCourse);
    }

    @GetMapping("/{id}")
    public CourseDto getCourse(@PathVariable Long id) {
        return repository.findById(id).map(CourseDtoMapper::toDto)
                .orElseThrow(() -> new CourseNotFoundException(id));
    }

    @GetMapping("/{id}/lectures")
    public List<LessonDto> getLectures(@PathVariable Long id) {
        return lessonRepository.findByCourseId(id).stream().map(LessonDtoMapper::toDto).toList();
    }

    @PutMapping("/{id}")
    CourseDto replaceCourse(@RequestBody CourseDto newCourse, @PathVariable Long id) {
        return CourseDtoMapper.toDto(repository.findById(id)
                .map(course -> {
                    course.setTeacher(TeacherDtoMapper.toTeacher(newCourse.getTeacher()));
                    course.setSubject(SubjectDtoMapper.toSubject(newCourse.getSubject()));
                    course.setGrade(GradeDtoMapper.toGrade(newCourse.getGrade()));

                    return repository.save(course);
                })
                .orElseGet(() -> repository.save(CourseDtoMapper.toCourse(newCourse))));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
