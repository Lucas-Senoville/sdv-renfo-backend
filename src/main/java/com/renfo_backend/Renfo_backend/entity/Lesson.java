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

@Entity
public class Lesson implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private LocalDate day;
    @Column(nullable = false)
    private LocalTime starTime;
    @Column(nullable = false)
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "grade_id", nullable = false)
    private Grade grade;

    public Lesson(LocalDate day, LocalTime starTime, LocalTime endTime) {
        this.day = day;
        this.starTime = starTime;
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

    public LocalTime getStarTime() {
        return starTime;
    }

    public void setStarTime(LocalTime starTime) {
        this.starTime = starTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endDate) {
        this.endTime = endDate;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject course) {
        this.subject = course;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
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
                Objects.equals(this.starTime, lecture.starTime) &&
                Objects.equals(this.endTime, lecture.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.day, this.starTime, this.endTime);
    }

    @Override
    public String toString() {
        return "Lecture{id=%d, day=%s, startTime=%s, endTime=%s, subject=%s, grade=%s}".formatted(this.id, this.day,
                this.starTime.truncatedTo(ChronoUnit.SECONDS), this.endTime.truncatedTo(ChronoUnit.SECONDS),
                this.subject, this.grade);
    }
}
