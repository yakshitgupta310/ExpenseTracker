package com.project.ExpenseTracker.controller;

import com.project.ExpenseTracker.entity.ApiErrorResponse;
import com.project.ExpenseTracker.exception.ExpenseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<?> handleExpenseNotFoundException(ExpenseNotFoundException e){
        ApiErrorResponse error;
        error = new ApiErrorResponse(LocalDateTime.now(), e.getMessage(), "Expense Not Found") ;
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
