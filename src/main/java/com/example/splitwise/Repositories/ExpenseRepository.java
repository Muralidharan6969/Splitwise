package com.example.splitwise.Repositories;

import com.example.splitwise.Models.Expense;
import com.example.splitwise.Models.ExpenseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Override
    Optional<Expense> findById(Long aLong);

    List<Expense> findAllByExpenseDescriptionContains (String expenseDescription);
    @Override
    List<Expense> findAllById(Iterable<Long> longs);

    Expense save(Expense expense);

    @Modifying
    @Transactional
    @Query("delete from Expense e where e.expenseDescription = ?1")
    void deleteByExpenseDescription(String expenseDescription);

    @Override
    @Modifying
    @Query("delete from Expense e where e.id = ?1")
    void deleteById(Long expenseId);
}
