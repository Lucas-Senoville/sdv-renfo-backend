package com.renfo_backend.Renfo_backend.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "lessons")
public class Lesson implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private LocalDate day;
    @Column(nullable = false)
    private LocalTime startTime;
    @Column(nullable = false)
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Course course;

    public Lesson() {
    }

    public Lesson(LocalDate day, LocalTime startTime, LocalTime endTime) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public void setEndTime(LocalTime endDate) {
        this.endTime = endDate;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Lesson)) {
            return false;
        }

        Lesson lecture = (Lesson) o;

        return Objects.equals(this.id, lecture.id) &&
                Objects.equals(this.day, lecture.day) &&
                Objects.equals(this.startTime, lecture.startTime) &&
                Objects.equals(this.endTime, lecture.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.day, this.startTime, this.endTime);
    }

    @Override
    public String toString() {
        return "Lecture{id=%d, day=%s, startTime=%s, endTime=%s, course=%s}".formatted(this.id, this.day,
                this.startTime.truncatedTo(ChronoUnit.SECONDS), this.endTime.truncatedTo(ChronoUnit.SECONDS),
                this.course);
    }
}
