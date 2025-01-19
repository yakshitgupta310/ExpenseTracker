package com.project.ExpenseTracker.controller;

import com.project.ExpenseTracker.entity.ApiErrorResponse;
import com.project.ExpenseTracker.exception.ExpenseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<?> handleExpenseNotFoundException(ExpenseNotFoundException e){
        ApiErrorResponse error;
        List<String> message = new ArrayList<String>();
        message.add(e.getMessage());
        error = new ApiErrorResponse(LocalDateTime.now(), message, "Expense Not Found") ;
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgsInvalidException(MethodArgumentNotValidException e){

        List<String> errors = e.getBindingResult().getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).collect(Collectors.toList());
        ApiErrorResponse response = new ApiErrorResponse(LocalDateTime.now(), errors, "Validation errors");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
