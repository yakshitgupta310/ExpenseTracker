package com.project.ExpenseTracker.service;

import com.project.ExpenseTracker.entity.Expense;

import java.util.List;
import java.util.Optional;


public interface ExpenseService {

    public List<Expense> getExpenses();

    public Optional<Expense> getExpensebyId(long id);

    public Expense createExpense(Expense expense);

    public String deleteExpense(long id);

    public Expense editExpense(Expense expense, long id);

    public List<Expense> getExpensebyNoteKeyword(String keyword);
}
