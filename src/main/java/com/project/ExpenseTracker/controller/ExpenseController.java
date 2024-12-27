package com.project.ExpenseTracker.controller;


import com.project.ExpenseTracker.entity.Expense;
import com.project.ExpenseTracker.service.ExpenseService;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@RestController
public class ExpenseController {

    private ExpenseService expenseService;

    private final Logger LOGGER = LoggerFactory.getLogger(ExpenseController.class);
    public ExpenseController(ExpenseService ExpenseService){
        this.expenseService=ExpenseService;
    }
    @GetMapping(value="/Expenses/all")
    public List<Expense> getExpenses(){

        return expenseService.getExpenses();
    }

    @GetMapping(value="/Expenses/{id}")
    public Optional<Expense> getExpensebyId(@PathVariable("id") long id){

        return expenseService.getExpensebyId(id);
    }

    @GetMapping(value="/Expenses/ByNote/{keyword}")
    public List<Expense> getExpensebyNoteKeyword(@PathVariable("keyword") String keyword){
        LOGGER.info("Parameter passed as part of Json Body :" + keyword);
        return expenseService.getExpensebyNoteKeyword(keyword);
    }

    @PostMapping(value="/Expenses")
    public Expense createExpense(@RequestBody Expense expense){
        return expenseService.createExpense(expense);
    }

    @DeleteMapping(value="/Expenses/{id}")
    public String deleteExpense(@PathVariable("id") long id){
        return expenseService.deleteExpense(id);
    }

    @PutMapping(value="/Expenses/{id}")
    public Expense editExpense(@RequestBody Expense expense, @PathVariable("id") long id){
        return expenseService.editExpense(expense, id);
    }
}
