package com.project.ExpenseTracker.service;

import com.project.ExpenseTracker.controller.ExpenseController;
import com.project.ExpenseTracker.entity.Expense;
import com.project.ExpenseTracker.entity.User;
import com.project.ExpenseTracker.model.ExpenseModel;
import com.project.ExpenseTracker.repository.ExpenseRepository;
import com.project.ExpenseTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ExpenseServiceImpl implements ExpenseService{


    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(ExpenseController.class);

    public ExpenseServiceImpl(ExpenseRepository expenseRepository){
        this.expenseRepository=expenseRepository;
    }
    @Override
    public List<Expense> getExpenses() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        System.out.println(userDetails.getUsername());
        return expenseRepository.findAll();
    }

    @Override
    public Optional<Expense> getExpenseById(long id) {
        return expenseRepository.findById(id);
    }

    @Override
    public ExpenseModel createExpense(ExpenseModel expense) {
        Expense newExpense = new Expense();
        newExpense.setName(expense.getName());
        newExpense.setCategory(expense.getCategory());
        newExpense.setAmount(expense.getAmount());
        newExpense.setDate(expense.getDate());
        newExpense.setNote(expense.getNote());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Optional<User> user = userRepository.findByuserName(userDetails.getUsername());
        newExpense.setUser(user.get());
        expenseRepository.save(newExpense);
        return expense;
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
        Optional dateOptional = Optional.ofNullable(expense.getDate());
        return expenseRepository.findById(id).map(
                expense1 -> {
                    expense1.setCategory(expense.getCategory());
                    if(dateOptional.isPresent()){expense1.setDate(expense.getDate());}
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
