package com.project.ExpenseTracker.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.time.LocalDate;


@Entity
@Data
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

//    private String username;

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

    public Expense(){
    }

    public Expense(String Name, String Category, String Note, Double Amount, LocalDate date ){
        this.amount=Amount;
        this.category=Category;
        this.date=date;
        this.name=Name;
        this.note=Note;
    }

    //@PreUpdate
    @PrePersist
    protected void setDefaultDate(){
        if(this.getDate()== null){
            this.setDate(LocalDate.now());
        }
    }

}
