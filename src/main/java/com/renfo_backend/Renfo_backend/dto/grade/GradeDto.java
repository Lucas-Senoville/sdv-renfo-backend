package com.renfo_backend.Renfo_backend.dto.grade;

import java.util.Set;

import com.renfo_backend.Renfo_backend.dto.student.StudentDto;

public class GradeDto {
    private Long id;
    private String name;
    private Integer year;

    public GradeDto() {
    }

    public GradeDto(Long id, String name, Integer year) {
        this.id = id;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Set<StudentDto> getStudents() {
        return Set.of();
    }
}
