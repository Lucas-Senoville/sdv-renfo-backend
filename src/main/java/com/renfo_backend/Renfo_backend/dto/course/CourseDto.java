package com.renfo_backend.Renfo_backend.dto.course;

import com.renfo_backend.Renfo_backend.dto.grade.GradeDto;
import com.renfo_backend.Renfo_backend.dto.grade.GradeDtoMapper;
import com.renfo_backend.Renfo_backend.dto.subject.SubjectDto;
import com.renfo_backend.Renfo_backend.dto.subject.SubjectDtoMapper;
import com.renfo_backend.Renfo_backend.dto.teacher.TeacherDto;
import com.renfo_backend.Renfo_backend.dto.teacher.TeacherDtoMapper;
import com.renfo_backend.Renfo_backend.entity.Grade;
import com.renfo_backend.Renfo_backend.entity.Subject;
import com.renfo_backend.Renfo_backend.entity.Teacher;

public class CourseDto {
    private Long id;
    private TeacherDto teacher;
    private SubjectDto subject;
    private GradeDto grade;

    public CourseDto() {
    }

    public CourseDto(Long id, Teacher teacher, Subject subject, Grade grade) {
        this.id = id;
        this.setTeacher(teacher);
        this.setSubject(subject);
        this.setGrade(grade);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TeacherDto getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = TeacherDtoMapper.toDto(teacher);
    }

    public SubjectDto getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = SubjectDtoMapper.toDto(subject);
    }

    public GradeDto getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = GradeDtoMapper.toDto(grade);
    }
}
