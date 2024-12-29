package com.project.ExpenseTracker.controller;


import com.project.ExpenseTracker.entity.Expense;
import com.project.ExpenseTracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class ExpenseController {

    private ExpenseService expenseService;

    private final Logger LOGGER = LoggerFactory.getLogger(ExpenseController.class);
    public ExpenseController(ExpenseService ExpenseService){
        this.expenseService=ExpenseService;
    }

    //GET Method to retrieve all Expenses
    @GetMapping(value="/Expenses/all")
    public List<Expense> getExpenses(){

        return expenseService.getExpenses();
    }

    //GET Method to retrieve Expense based on Id(Primary Key)
    @GetMapping(value="/Expenses/{id}")
    public Optional<Expense> getExpenseById(@PathVariable("id") long id){

        return expenseService.getExpenseById(id);
    }

    //GET Method to retrieve all Expenses based on Keyword in the Note column
    @GetMapping(value="/Expenses/ByNote/{keyword}")
    public List<Expense> getExpensebyNoteKeyword(@PathVariable("keyword") String keyword){
        LOGGER.info("Parameter passed as part of Json Body :" + keyword);
        return expenseService.getExpensebyNoteKeyword(keyword);
    }

    //GET Method to retrieve all Expenses for a specific date
    @GetMapping(value="/Expenses/Date")
    public List<Expense> getExpenseByDate(@RequestParam("date") @DateTimeFormat(pattern = "dd/MM/yyyy")LocalDate date){
        //LOGGER.info("SQL Date in controller :" + date);
        return expenseService.getExpenseByDate(date);

    }

    //GET Method to retrieve all Expenses for the whole month with optional param of year
    @GetMapping(value="/Expenses/Month")
    public List<Expense> getExpenseByMonth(@RequestParam("month") int month, @RequestParam(value = "year", required = false) Integer year){
        //java.sql.Date newdate = new java.sql.Date(date.getTime());
        LOGGER.info("LocalDate month in controller :" + month);
        LOGGER.info("LocalDate year in controller :" + year);
        return expenseService.getExpenseByMonth(month, year);
    }

    //POST Method to create an Expense
    @PostMapping(value="/Expenses")
    public Expense createExpense(@RequestBody @Valid Expense expense){
        return expenseService.createExpense(expense);
    }

    //Delete Method to delete an Expense
    @DeleteMapping(value="/Expenses/{id}")
    public String deleteExpense(@PathVariable("id") long id){
        return expenseService.deleteExpense(id);
    }

    //PUT Method to edit an Expense
    @PutMapping(value="/Expenses/{id}")
    public Expense editExpense(@RequestBody Expense expense, @PathVariable("id") long id){
        return expenseService.editExpense(expense, id);
    }
}
