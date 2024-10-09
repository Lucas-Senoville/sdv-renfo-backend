package com.renfo_backend.Renfo_backend.dto.student;

import com.renfo_backend.Renfo_backend.dto.grade.GradeDto;
import com.renfo_backend.Renfo_backend.dto.grade.GradeDtoMapper;
import com.renfo_backend.Renfo_backend.entity.Grade;

public class StudentWithGradeDto extends StudentDto {
    private GradeDto grade;

    public StudentWithGradeDto(StudentDto studentDto) {
        super(studentDto);
    }

    public StudentWithGradeDto(Long id, String firstName, String lastName, Grade grade) {
        super(id, firstName, lastName);
        this.setGrade(grade);
    }

    public GradeDto getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = GradeDtoMapper.toDto(grade);
    }

}
