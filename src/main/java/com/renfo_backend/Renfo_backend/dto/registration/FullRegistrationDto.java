package com.renfo_backend.Renfo_backend.dto.registration;

import java.time.LocalDate;

import com.renfo_backend.Renfo_backend.dto.lesson.LessonDto;
import com.renfo_backend.Renfo_backend.dto.lesson.LessonDtoMapper;
import com.renfo_backend.Renfo_backend.dto.student.StudentDto;
import com.renfo_backend.Renfo_backend.dto.student.StudentDtoMapper;
import com.renfo_backend.Renfo_backend.entity.Lesson;
import com.renfo_backend.Renfo_backend.entity.Student;

public class FullRegistrationDto extends RegistrationDto {
    private LessonDto lesson;
    private StudentDto student;

    public FullRegistrationDto() {
    }

    public FullRegistrationDto(RegistrationDto registrationDto) {
        super(registrationDto);
    }

    public FullRegistrationDto(Long id, LocalDate date, Lesson lesson, Student student) {
        super(id, date);
        this.setLesson(lesson);
        this.setStudent(student);
        ;
    }

    public LessonDto getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = LessonDtoMapper.toDto(lesson);
    }

    public StudentDto getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = StudentDtoMapper.toDto(student);
    }
}
