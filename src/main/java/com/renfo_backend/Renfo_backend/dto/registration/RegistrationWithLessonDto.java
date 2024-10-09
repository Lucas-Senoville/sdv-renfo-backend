package com.renfo_backend.Renfo_backend.dto.registration;

import java.time.LocalDate;

import com.renfo_backend.Renfo_backend.dto.lesson.LessonDto;
import com.renfo_backend.Renfo_backend.dto.lesson.LessonDtoMapper;
import com.renfo_backend.Renfo_backend.entity.Lesson;

public class RegistrationWithLessonDto extends RegistrationDto {
    private LessonDto lesson;

    public RegistrationWithLessonDto() {
        super();
    }

    public RegistrationWithLessonDto(FullRegistrationDto fullRegistrationDto) {
        super(fullRegistrationDto);
        this.lesson = fullRegistrationDto.getLesson();
    }

    public RegistrationWithLessonDto(RegistrationDto registrationDto) {
        super(registrationDto);
    }

    public RegistrationWithLessonDto(Long id, LocalDate date, Lesson lesson) {
        super(id, date);
        this.setLesson(lesson);
    }

    public LessonDto getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = LessonDtoMapper.toDto(lesson);
    }
}
