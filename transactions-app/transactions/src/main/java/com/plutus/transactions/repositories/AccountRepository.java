package com.plutus.transactions.repositories;

import com.plutus.transactions.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
