package com.whitebox.command.repository;

import com.whitebox.command.entity.Account;
import com.whitebox.command.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
