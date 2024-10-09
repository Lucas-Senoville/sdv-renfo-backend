package com.renfo_backend.Renfo_backend.dto.lesson;

import java.time.LocalDate;
import java.time.LocalTime;

import com.renfo_backend.Renfo_backend.dto.course.CourseDto;
import com.renfo_backend.Renfo_backend.dto.course.CourseDtoMapper;
import com.renfo_backend.Renfo_backend.entity.Course;

public class LessonDto {
    private Long id;
    private LocalDate day;
    private LocalTime startTime;
    private LocalTime endTime;
    private CourseDto course;

    public LessonDto() {
    }

    public LessonDto(Long id, LocalDate day, LocalTime startTime, LocalTime endTime, Course course) {
        this.id = id;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.setCourse(course);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public CourseDto getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = CourseDtoMapper.toDto(course);
    }
}
