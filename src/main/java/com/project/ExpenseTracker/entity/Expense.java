package com.project.ExpenseTracker.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;


@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;
    private String name;

    private String category;

    private double amount;

    private String note;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date date;


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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public Expense(){

    }

    public Expense(String Name, String Category, String Note, Double Amount, Date date ){
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
}
