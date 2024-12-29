package com.project.ExpenseTracker.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @NotEmpty(message = "Expense Name is mandatory")
    private String name;

    @NotEmpty(message = "Expense category is mandatory")
    private String category;

    @NotNull(message = "Expense amount is mandatory")
    private Double amount;

    private String note;


    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "IST")
    @Temporal(TemporalType.DATE)
    private LocalDate date;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Expense(){

    }

    public Expense(String Name, String Category, String Note, Double Amount, LocalDate date ){
        this.amount=Amount;
        this.category=Category;
        this.date=date;
        this.name=Name;
        this.note=Note;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "Id=" + Id +
                ", Name='" + name + '\'' +
                ", Category='" + category + '\'' +
                ", Amount=" + amount +
                ", Note='" + note + '\'' +
                ", Date=" + date +
                '}';
    }

    @PrePersist
    protected void setDefaultDate(){
        if(this.getDate()== null){
            this.setDate(LocalDate.now());
        }
    }
}
