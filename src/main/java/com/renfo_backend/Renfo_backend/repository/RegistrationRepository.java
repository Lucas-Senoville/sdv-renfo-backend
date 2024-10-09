package com.renfo_backend.Renfo_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renfo_backend.Renfo_backend.entity.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByStudentId(Long studentId);

    List<Registration> findByLessonId(Long lessonId);
}
