package com.renfo_backend.Renfo_backend.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.renfo_backend.Renfo_backend.exceptions.LessonNotFoundException;

@RestControllerAdvice
public class LessonNotFoundAdvice {
    @ExceptionHandler(LessonNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String studentNotFoundHandler(LessonNotFoundException ex) {
        return ex.getMessage();
    }
}
