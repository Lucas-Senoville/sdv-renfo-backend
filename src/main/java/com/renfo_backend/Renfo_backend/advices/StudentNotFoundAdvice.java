package com.renfo_backend.Renfo_backend.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.renfo_backend.Renfo_backend.exceptions.StudentNotFoundException;

@RestControllerAdvice
public class StudentNotFoundAdvice {
    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String studentNotFoundHandler(StudentNotFoundException ex) {
        return ex.getMessage();
    }
}
