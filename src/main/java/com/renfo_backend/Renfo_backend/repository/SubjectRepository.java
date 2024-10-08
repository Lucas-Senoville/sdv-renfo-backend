package com.renfo_backend.Renfo_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renfo_backend.Renfo_backend.entity.Lesson;

public interface SubjectRepository extends JpaRepository<Lesson, Long> {

}
