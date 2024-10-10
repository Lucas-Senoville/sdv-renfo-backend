package com.renfo_backend.Renfo_backend.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renfo_backend.Renfo_backend.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Set<Teacher> findByCoursesGradeId(Long id);
}
