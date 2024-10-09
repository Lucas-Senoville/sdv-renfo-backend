package com.renfo_backend.Renfo_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renfo_backend.Renfo_backend.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByGradeId(Long gradeId);
}
