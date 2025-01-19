package com.project.ExpenseTracker.repository;

import com.project.ExpenseTracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query(value = "Select * from expense e where e.userid=?1", nativeQuery = true)
    public List<Expense> getExpensesByUserId(Long userid);





}
