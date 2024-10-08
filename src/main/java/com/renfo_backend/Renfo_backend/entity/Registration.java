package com.renfo_backend.Renfo_backend.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student studentId;

    @OneToOne
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lessonId;

    public Registration(LocalDate date, Student studentId, Lesson lessonId) {
        this.date = date;
        this.studentId = studentId;
        this.lessonId = lessonId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    public Lesson getLessonId() {
        return lessonId;
    }

    public void setLessonId(Lesson lessonId) {
        this.lessonId = lessonId;
    }
}
