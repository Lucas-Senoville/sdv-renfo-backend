package com.renfo_backend.Renfo_backend.exceptions;

public class LessonNotFoundException extends RuntimeException {
    public LessonNotFoundException(Long id) {
        super("Could not find lesson with id " + id);
    }
}
