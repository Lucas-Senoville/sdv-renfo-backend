package com.renfo_backend.Renfo_backend.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.renfo_backend.Renfo_backend.exceptions.TeacherNotFoundException;

@RestControllerAdvice
public class TeacherNotFoundAdvice {
    @ExceptionHandler(TeacherNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String studentNotFoundHandler(TeacherNotFoundException ex) {
        return ex.getMessage();
    }
}
