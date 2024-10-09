package com.renfo_backend.Renfo_backend.exceptions;

public class GradeNotFoundException extends RuntimeException {
    public GradeNotFoundException(Long id) {
        super("Could not find grade with id " + id);
    }
}
