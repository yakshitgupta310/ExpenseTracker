package com.project.ExpenseTracker.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpenseModel {

    @NotEmpty(message = "Expense Name is mandatory")
    String name;

    @NotEmpty(message = "Expense category is mandatory")
    String category;

    @NotNull(message = "Expense amount is mandatory")
    Double amount;

    String note;

    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "IST")
    @Temporal(TemporalType.DATE)
    private LocalDate date;


}
