package com.renfo_backend.Renfo_backend.dto.student;

import com.renfo_backend.Renfo_backend.dto.grade.GradeDtoMapper;
import com.renfo_backend.Renfo_backend.entity.Student;

public abstract class StudentDtoMapper {
    public static StudentDto toDto(Student student) {
        if (student == null) {
            return new StudentDto();
        }

        return new StudentDto(student.getId(), student.getFirstName(), student.getLastName());
    }

    public static StudentWithGradeDto toDtoWithGrade(Student student) {
        StudentWithGradeDto studentWithGradeDto = new StudentWithGradeDto(toDto(student));
        studentWithGradeDto.setGrade(student.getGrade());

        return studentWithGradeDto;
    }

    public static Student toStudent(StudentDto studentDto) {
        Student student = new Student(studentDto.getFirstName(), studentDto.getLastName());
        student.setId(studentDto.getId());

        if (studentDto instanceof StudentWithGradeDto) {
            student.setGrade(GradeDtoMapper.toGrade(((StudentWithGradeDto) studentDto).getGrade()));
        }

        return student;
    }
}
