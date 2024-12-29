package com.project.ExpenseTracker.service;

import com.project.ExpenseTracker.controller.ExpenseController;
import com.project.ExpenseTracker.entity.Expense;
import com.project.ExpenseTracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ExpenseServiceImpl implements ExpenseService{

    private ExpenseRepository expenseRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(ExpenseController.class);

    public ExpenseServiceImpl(ExpenseRepository expenseRepository){
        this.expenseRepository=expenseRepository;
    }
    @Override
    public List<Expense> getExpenses() {
        return expenseRepository.findAll();
    }

    @Override
    public Optional<Expense> getExpenseById(long id) {
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

    @Override
    public List<Expense> getExpenseByDate(LocalDate date) {
        //LOGGER.info("Requested Date in service :" + date);
        List<Expense> Expenses = expenseRepository.findAll().stream().filter(
                expense -> expense.getDate().equals(date)).toList();

        return Expenses;
    }

    public List<Expense> getExpenseByMonth(int month, Integer year){
        if(year == null){
            year=LocalDate.now().getYear();
        }
        Integer finalYear = year;
        List<Expense> Expenses = expenseRepository.findAll().stream()
                .filter(expense -> expense.getDate().getYear() == finalYear)
                .filter(expense -> expense.getDate().getMonthValue() == month).toList();

        return Expenses;
    }
}
