package com.renfo_backend.Renfo_backend.dto.grade;

import java.time.Year;

import com.renfo_backend.Renfo_backend.entity.Grade;

public abstract class GradeDtoMapper {
    public static GradeDto toDto(Grade grade) {
        if (grade == null) {
            return new GradeDto();
        }

        return new GradeDto(grade.getId(), grade.getName(), grade.getYear().getValue());
    }

    public static GradeWithStudentsDto toDtoWithStudents(Grade grade) {
        GradeWithStudentsDto gradeWithStudentsDto = new GradeWithStudentsDto(toDto(grade));
        gradeWithStudentsDto.setStudents(grade.getStudents());

        return gradeWithStudentsDto;
    }

    public static Grade toGrade(GradeDto gradeDto) {
        if (gradeDto == null) {
            return new Grade();
        }

        Grade grade = new Grade(gradeDto.getName(), Year.of(gradeDto.getYear()));
        grade.setId(gradeDto.getId());

        return grade;
    }
}
