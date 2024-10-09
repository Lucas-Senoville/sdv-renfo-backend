package com.renfo_backend.Renfo_backend.entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student extends Person {
    @ManyToOne
    @JoinColumn(name = "grade_id", nullable = false)
    private Grade grade;

    @OneToMany(mappedBy = "student")
    private Set<Registration> registrations;

    public Student() {
        super();
    }

    public Student(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Set<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(Set<Registration> registrations) {
        this.registrations = registrations;
    }

    public void addRegistration(Registration registration) {
        this.registrations.add(registration);
    }

    @Override
    public String toString() {
        return "Student{id=%s, firstName=%s, lastName=%s, grade=%s, registrations=%s}".formatted(this.id,
                this.firstName, this.lastName,
                this.grade, this.registrations);
    }
}
