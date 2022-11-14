package com.whitebox.command.repository;

import com.whitebox.command.entity.Account;
import com.whitebox.command.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    List<Transaction> findAllByAccountId(String accountId);
}
