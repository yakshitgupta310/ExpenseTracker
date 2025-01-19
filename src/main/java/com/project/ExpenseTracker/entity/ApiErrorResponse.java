package com.project.ExpenseTracker.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiErrorResponse {

    private LocalDateTime timestamp;
    private List<String> message;
    private String summary;

}
