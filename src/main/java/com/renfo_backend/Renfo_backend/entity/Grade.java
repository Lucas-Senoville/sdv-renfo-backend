package com.renfo_backend.Renfo_backend.entity;

import java.time.Year;
import java.util.Objects;
import java.util.Set;

import com.renfo_backend.converters.YearAttributeConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "grades")
public class Grade implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(columnDefinition = "smallint", nullable = false)
    @Convert(converter = YearAttributeConverter.class)
    private Year year;

    @OneToMany(mappedBy = "grade")
    private Set<Course> courses;

    @OneToMany(mappedBy = "grade")
    private Set<Student> students;

    public Grade() {
    }

    public Grade(String name, Year year) {
        this.name = name;
        this.year = year;
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

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Set<Course> getCourses() {
        return this.courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
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
        return "Grade{id=%d, year=%s, name=%s, courses=%s}".formatted(
                this.id, this.year,
                this.name, this.courses);
    }
}
