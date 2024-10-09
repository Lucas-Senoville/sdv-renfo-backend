package com.renfo_backend.Renfo_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renfo_backend.Renfo_backend.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByGradeId(Long gardeId);

    List<Course> findByTeacherId(Long teacherId);

    List<Course> findBySubjectId(Long subjectId);
}
