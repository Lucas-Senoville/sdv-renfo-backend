package com.renfo_backend.Renfo_backend.exceptions;

public class TeacherNotFoundException extends RuntimeException {
    public TeacherNotFoundException(Long id) {
        super("Could not find teacher with id " + id);
    }
}
