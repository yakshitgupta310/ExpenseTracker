package com.project.ExpenseTracker.controller;


import com.project.ExpenseTracker.entity.Expense;
import com.project.ExpenseTracker.exception.ExpenseNotFoundException;
import com.project.ExpenseTracker.model.ExpenseModel;
import com.project.ExpenseTracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ExpenseController {

    private ExpenseService expenseService;

    private final Logger LOGGER = LoggerFactory.getLogger(ExpenseController.class);
    public ExpenseController(ExpenseService ExpenseService){
        this.expenseService=ExpenseService;
    }

    //GET Method to retrieve all Expenses
    @GetMapping(value="/Expenses/all")
    public ResponseEntity<?> getExpenses(){
        List<Expense> all =  expenseService.getExpenses();
        List<ExpenseModel> output = all.stream().map(expense -> new ExpenseModel(expense.getName(), expense.getCategory(), expense.getAmount(), expense.getNote(), expense.getDate() )).collect(Collectors.toList());
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    //GET Method to retrieve Expense based on Id(Primary Key)
    @GetMapping(value="/Expenses/{id}")
    public ResponseEntity<?> getExpenseById(@PathVariable("id") long id){
        Optional<Expense> expense= Optional.ofNullable(expenseService.getExpenseById(id)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense not found with id " + id)));

        return new ResponseEntity<>(expense, HttpStatus.OK);
    }

    //GET Method to retrieve all Expenses based on Keyword in the Note column
    @GetMapping(value="/Expenses/ByNote/{keyword}")
    public ResponseEntity<?> getExpensebyNoteKeyword(@PathVariable("keyword") String keyword){
        LOGGER.info("Parameter passed as part of Json Body :" + keyword);
        List<Expense> all = expenseService.getExpensebyNoteKeyword(keyword);
        List<ExpenseModel> output = all.stream().map(expense -> new ExpenseModel(expense.getName(), expense.getCategory(), expense.getAmount(), expense.getNote(), expense.getDate() )).collect(Collectors.toList());
        if(output!=null && !output.isEmpty()){
            return new ResponseEntity<>(output, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //GET Method to retrieve all Expenses for a specific date
    @GetMapping(value="/Expenses/Date")
    public ResponseEntity<?> getExpenseByDate(@RequestParam("date") @DateTimeFormat(pattern = "dd/MM/yyyy")LocalDate date){
        List<Expense> all = expenseService.getExpenseByDate(date);
        List<ExpenseModel> output = all.stream().map(expense -> new ExpenseModel(expense.getName(), expense.getCategory(), expense.getAmount(), expense.getNote(), expense.getDate() )).collect(Collectors.toList());
        if(output!=null && !output.isEmpty()){
            return new ResponseEntity<>(output, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //GET Method to retrieve all Expenses for the whole month with optional param of year
    @GetMapping(value="/Expenses/Month")
    public ResponseEntity<?> getExpenseByMonth(@RequestParam("month") int month, @RequestParam(value = "year", required = false) Integer year){
        LOGGER.info("LocalDate month in controller :" + month);
        LOGGER.info("LocalDate year in controller :" + year);
        List<Expense> all = expenseService.getExpenseByMonth(month, year);
        List<ExpenseModel> output = all.stream().map(expense -> new ExpenseModel(expense.getName(), expense.getCategory(), expense.getAmount(), expense.getNote(), expense.getDate() )).collect(Collectors.toList());
        if(output!=null && !output.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //POST Method to create an Expense
    @PostMapping(value="/Expenses")
    public ResponseEntity<?> createExpense(@RequestBody @Valid ExpenseModel expense){
        try{
            expenseService.createExpense(expense);
            return new ResponseEntity<>(expense, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    //Delete Method to delete an Expense
    @DeleteMapping(value="/Expenses/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable("id") long id) {
        try {
            expenseService.deleteExpense(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        }

    //PUT Method to edit an Expense
    @PutMapping(value="/Expenses/{id}")
    public ResponseEntity<?> editExpense(@RequestBody Expense expense, @PathVariable("id") long id){
        try {
            Expense data = expenseService.editExpense(expense, id);
            return new ResponseEntity<>(data ,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
