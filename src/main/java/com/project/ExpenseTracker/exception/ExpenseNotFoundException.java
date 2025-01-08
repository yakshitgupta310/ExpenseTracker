package com.project.ExpenseTracker.exception;


public class ExpenseNotFoundException extends RuntimeException{

    public ExpenseNotFoundException(String errormessage){
        super(errormessage);
    }
}
