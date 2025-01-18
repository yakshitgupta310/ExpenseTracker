package com.project.ExpenseTracker.repository;

import com.project.ExpenseTracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    //@Query(nativeQuery = true, value = "Select u from users u where u.username=:userName")
    public Optional<User> findByuserName(String userName);
}
