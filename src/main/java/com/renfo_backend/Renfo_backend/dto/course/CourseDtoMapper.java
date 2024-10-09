package com.renfo_backend.Renfo_backend.dto.course;

import com.renfo_backend.Renfo_backend.dto.grade.GradeDtoMapper;
import com.renfo_backend.Renfo_backend.dto.subject.SubjectDtoMapper;
import com.renfo_backend.Renfo_backend.dto.teacher.TeacherDtoMapper;
import com.renfo_backend.Renfo_backend.entity.Course;

public abstract class CourseDtoMapper {
    public static CourseDto toDto(Course course) {
        if (course == null) {
            return new CourseDto();
        }

        return new CourseDto(course.getId(), course.getTeacher(), course.getSubject(), course.getGrade());
    }

    public static Course toCourse(CourseDto courseDto) {
        if (courseDto == null) {
            return new Course();
        }

        Course course = new Course();
        course.setId(courseDto.getId());
        course.setTeacher(TeacherDtoMapper.toTeacher(courseDto.getTeacher()));
        course.setSubject(SubjectDtoMapper.toSubject(courseDto.getSubject()));
        course.setGrade(GradeDtoMapper.toGrade(courseDto.getGrade()));

        return course;
    }
}
