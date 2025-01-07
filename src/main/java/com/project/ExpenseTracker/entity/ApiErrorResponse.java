package com.project.ExpenseTracker.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiErrorResponse {

    private LocalDateTime timestamp;
    private String message;
    private String summary;

}
