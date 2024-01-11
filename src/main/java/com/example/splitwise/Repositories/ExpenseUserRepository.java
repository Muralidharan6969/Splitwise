package com.example.splitwise.Repositories;

import com.example.splitwise.Models.ExpenseUser;
import com.example.splitwise.Models.GroupSplitwise;
import com.example.splitwise.Models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseUserRepository extends JpaRepository<ExpenseUser, Long> {
    List<ExpenseUser> findAllByUserProfile(UserProfile userProfile);
    ExpenseUser save(ExpenseUser expenseUser);
}
