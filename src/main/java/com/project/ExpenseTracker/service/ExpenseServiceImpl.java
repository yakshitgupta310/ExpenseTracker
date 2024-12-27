package com.project.ExpenseTracker.service;

import com.project.ExpenseTracker.entity.Expense;
import com.project.ExpenseTracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService{

    private ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository){
        this.expenseRepository=expenseRepository;
    }
    @Override
    public List<Expense> getExpenses() {
        return expenseRepository.findAll();
    }

    @Override
    public Optional<Expense> getExpensebyId(long id) {
        return expenseRepository.findById(id);
    }

    @Override
    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public String deleteExpense(long id){
         Optional<Expense> expense = expenseRepository.findById(id);
         if(expense.isPresent()){
         expenseRepository.deleteById(id);
             return "Expense deleted successfully";}
         else{
             return "Expense with requested Id doesn't exist";
         }
    }

    @Override
    public Expense editExpense(Expense expense, long id){
        return expenseRepository.findById(id).map(
                expense1 -> {expense1.setCategory(expense.getCategory());
                             expense1.setDate(expense.getDate());
                             expense1.setAmount(expense.getAmount());
                             expense1.setName(expense.getName());
                             expense1.setNote(expense.getNote());

                             return expenseRepository.save(expense1);
                }
        ).orElseGet(() -> {return expenseRepository.save(expense);});
    }

    @Override
    public List<Expense> getExpensebyNoteKeyword(String keyword){
        List<Expense> Expenses = expenseRepository.findAll().stream().filter(
                expense -> expense.getNote().contains(keyword)).toList();

        return Expenses;
    }
}
