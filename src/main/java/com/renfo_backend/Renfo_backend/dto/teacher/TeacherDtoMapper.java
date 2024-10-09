package com.renfo_backend.Renfo_backend.dto.teacher;

import com.renfo_backend.Renfo_backend.entity.Teacher;

public abstract class TeacherDtoMapper {
    public static TeacherDto toDto(Teacher teacher) {
        if (teacher == null) {
            return new TeacherDto();
        }

        return new TeacherDto(teacher.getId(), teacher.getFirstName(), teacher.getLastName());
    }

    public static Teacher toTeacher(TeacherDto teacherDto) {
        if (teacherDto == null) {
            return new Teacher();
        }

        Teacher teacher = new Teacher(teacherDto.getFirstName(), teacherDto.getLastName());
        teacher.setId(teacherDto.getId());

        return teacher;
    }
}
