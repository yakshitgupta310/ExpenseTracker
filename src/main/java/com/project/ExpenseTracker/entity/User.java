package com.project.ExpenseTracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Users")
public class User {

    private String firstName;

    @Id
    private String userName;

    private String password;

    private String role;

    private boolean isEnabled = true;

}
