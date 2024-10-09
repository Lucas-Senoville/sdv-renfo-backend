package com.renfo_backend.Renfo_backend.exceptions;

public class SubjectNotFoundException extends RuntimeException {
    public SubjectNotFoundException(Long id) {
        super("Could not find subject with id " + id);
    }
}
