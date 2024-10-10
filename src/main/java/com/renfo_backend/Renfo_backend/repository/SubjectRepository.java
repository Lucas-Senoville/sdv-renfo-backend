package com.renfo_backend.Renfo_backend.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renfo_backend.Renfo_backend.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Set<Subject> findByCoursesGradeId(Long subjectId);
}
