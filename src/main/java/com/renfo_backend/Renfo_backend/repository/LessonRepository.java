package com.renfo_backend.Renfo_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renfo_backend.Renfo_backend.entity.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findByCourseId(Long courseId);

    List<Lesson> findByCourseGradeId(Long gradeId);
}
