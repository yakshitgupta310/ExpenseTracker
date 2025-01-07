package com.project.ExpenseTracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExpenseNotFoundException extends RuntimeException{

    public ExpenseNotFoundException(String errormessage){
        super(errormessage);
    }
}
