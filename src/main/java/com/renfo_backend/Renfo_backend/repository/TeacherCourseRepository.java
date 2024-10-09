package com.renfo_backend.Renfo_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renfo_backend.Renfo_backend.entity.Course;

public interface TeacherCourseRepository extends JpaRepository<Course, Long> {

}
