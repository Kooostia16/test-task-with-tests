package com.example.taskoftests.controllers;

import com.example.taskoftests.exceptions.OnNegativeValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TestControllerAdvice {

    @ExceptionHandler(value = OnNegativeValueException.class)
    public ResponseEntity<String> onNegativeValue() {
        return new ResponseEntity<>("n parameter's value cannot be less than 0!", HttpStatus.BAD_REQUEST);
    }
}
