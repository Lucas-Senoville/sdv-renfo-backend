package com.renfo_backend.Renfo_backend.repository;

import com.renfo_backend.Renfo_backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
