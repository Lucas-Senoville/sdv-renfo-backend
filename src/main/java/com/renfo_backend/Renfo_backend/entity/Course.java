package com.renfo_backend.Renfo_backend.entity;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "courses")
public class Course implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Teacher teacher;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Subject subject;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Grade grade;

    @OneToMany(mappedBy = "course")
    private Set<Lesson> lessons;

    public Course() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Set<Lesson> getLessons() {
        return this.lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }

    public void addLecture(Lesson lesson) {
        this.lessons.add(lesson);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Course)) {
            return false;
        }

        Course teacherCourse = (Course) o;

        return Objects.equals(this.id, teacherCourse.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "TeacherCourse{id=%d, teacher=%s, subject=%s, grade=%s, lessons=%s}".formatted(this.id, this.teacher,
                this.subject,
                this.grade, this.lessons);
    }
}
