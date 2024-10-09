package com.renfo_backend.Renfo_backend.exceptions;

public class RegistrationNotFoundException extends RuntimeException {
    public RegistrationNotFoundException(Long id) {
        super("Could not find registration with id " + id);
    }
}
