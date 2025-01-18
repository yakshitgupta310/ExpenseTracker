package com.project.ExpenseTracker.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long Id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "username", unique = true)
    private String userName;

    @Column(name = "password")
    private String password;

    private String role;

    private boolean isEnabled = true;

}
