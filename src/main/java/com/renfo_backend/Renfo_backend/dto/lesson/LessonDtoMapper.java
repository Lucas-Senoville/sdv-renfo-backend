package com.renfo_backend.Renfo_backend.dto.lesson;

import com.renfo_backend.Renfo_backend.dto.course.CourseDtoMapper;
import com.renfo_backend.Renfo_backend.entity.Lesson;

public abstract class LessonDtoMapper {
    public static LessonDto toDto(Lesson lesson) {
        if (lesson == null) {
            return new LessonDto();
        }

        return new LessonDto(lesson.getId(), lesson.getDay(), lesson.getStartTime(), lesson.getEndTime(),
                lesson.getCourse());
    }

    public static Lesson toLesson(LessonDto lessonDto) {
        if (lessonDto == null) {
            return new Lesson();
        }

        Lesson lesson = new Lesson(lessonDto.getDay(), lessonDto.getStartTime(), lessonDto.getEndTime());
        lesson.setId(lessonDto.getId());
        lesson.setCourse(CourseDtoMapper.toCourse(lessonDto.getCourse()));

        return lesson;
    }
}
