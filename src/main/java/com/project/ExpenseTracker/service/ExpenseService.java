package com.project.ExpenseTracker.service;

import com.project.ExpenseTracker.entity.Expense;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface ExpenseService {

    public List<Expense> getExpenses();

    public Optional<Expense> getExpenseById(long id);

    public Expense createExpense(Expense expense);

    public String deleteExpense(long id);

    public Expense editExpense(Expense expense, long id);

    public List<Expense> getExpensebyNoteKeyword(String keyword);

    public List<Expense> getExpenseByDate(LocalDate date);

    public List<Expense> getExpenseByMonth(int month, Integer year);
}
