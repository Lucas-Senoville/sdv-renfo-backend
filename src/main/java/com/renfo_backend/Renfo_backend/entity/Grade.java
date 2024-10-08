package com.renfo_backend.Renfo_backend.entity;

import java.time.Year;
import java.util.List;
import java.util.Objects;

import com.renfo_backend.converters.YearAttributeConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(columnDefinition = "smallint", nullable = false)
    @Convert(converter = YearAttributeConverter.class)
    private Year year;

    @OneToMany(mappedBy = "grade")
    private List<Lesson> lectures;

    public Grade(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Lesson> getLectures() {
        return this.lectures;
    }

    public void setLectures(List<Lesson> lectures) {
        this.lectures = lectures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Grade)) {
            return false;
        }

        Grade grade = (Grade) o;

        return Objects.equals(this.id, grade.id) && Objects.equals(this.name, grade.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }

    @Override
    public String toString() {
        return "Grade{id=%d, year=%s, name=%s, lectures=%s}".formatted(
                this.id, this.year,
                this.name, this.lectures);
    }
}
