package com.whitebox.command.repository;

import com.whitebox.command.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository  extends JpaRepository<Account, String> {
}
