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

import com.renfo_backend.Renfo_backend.dto.course.CourseDtoMapper;
import com.renfo_backend.Renfo_backend.dto.lesson.LessonDto;
import com.renfo_backend.Renfo_backend.dto.lesson.LessonDtoMapper;
import com.renfo_backend.Renfo_backend.dto.registration.RegistrationDtoMapper;
import com.renfo_backend.Renfo_backend.dto.registration.RegistrationWithStudentDto;
import com.renfo_backend.Renfo_backend.entity.Lesson;
import com.renfo_backend.Renfo_backend.exceptions.LessonNotFoundException;
import com.renfo_backend.Renfo_backend.repository.LessonRepository;
import com.renfo_backend.Renfo_backend.repository.RegistrationRepository;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {
    private final LessonRepository repository;
    private final RegistrationRepository registrationRepository;

    public LessonController(LessonRepository repository, RegistrationRepository registrationRepository) {
        this.repository = repository;
        this.registrationRepository = registrationRepository;
    }

    @GetMapping
    public List<LessonDto> getLessons() {
        return repository.findAll().stream().map(LessonDtoMapper::toDto).toList();
    }

    @PostMapping
    public LessonDto newLesson(@RequestBody LessonDto lessonDto) {
        Lesson lesson = LessonDtoMapper.toLesson(lessonDto);

        Lesson newLesson = repository.save(lesson);

        return LessonDtoMapper.toDto(newLesson);
    }

    @GetMapping("/{id}")
    public LessonDto getLesson(@PathVariable Long id) {
        return repository.findById(id).map(LessonDtoMapper::toDto).orElseThrow(() -> new LessonNotFoundException(id));
    }

    @GetMapping("/{id}/registrations")
    public List<RegistrationWithStudentDto> getRegistrations(@PathVariable Long id) {
        return registrationRepository.findByLessonId(id).stream().map(RegistrationDtoMapper::toDtoWithStudent).toList();
    }

    @PutMapping("/{id}")
    LessonDto replaceLesson(@RequestBody LessonDto newLesson, @PathVariable Long id) {
        return LessonDtoMapper.toDto(repository.findById(id)
                .map(lesson -> {
                    lesson.setDay(newLesson.getDay());
                    lesson.setStartTime(newLesson.getStartTime());
                    lesson.setEndTime(newLesson.getEndTime());
                    lesson.setCourse(CourseDtoMapper.toCourse(newLesson.getCourse()));

                    return repository.save(lesson);
                })
                .orElseGet(() -> {
                    return repository.save(LessonDtoMapper.toLesson(newLesson));
                }));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteLesson(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
