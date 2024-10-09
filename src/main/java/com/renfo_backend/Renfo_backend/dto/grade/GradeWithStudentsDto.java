package com.renfo_backend.Renfo_backend.dto.grade;

import java.util.Set;
import java.util.stream.Collectors;

import com.renfo_backend.Renfo_backend.dto.student.StudentDto;
import com.renfo_backend.Renfo_backend.dto.student.StudentDtoMapper;
import com.renfo_backend.Renfo_backend.entity.Student;

public class GradeWithStudentsDto extends GradeDto {
    private Set<StudentDto> students;

    public GradeWithStudentsDto(GradeDto gradeDto) {
        super(gradeDto.getId(), gradeDto.getName(), gradeDto.getYear());
    }

    public GradeWithStudentsDto(Long id, String name, Integer year, Set<Student> students) {
        super(id, name, year);
        this.setStudents(students);
    }

    public Set<StudentDto> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students.stream().map(StudentDtoMapper::toDto).collect(Collectors.toSet());
    }
}
