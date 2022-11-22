package com.plutus.transactions.repositories;

import com.plutus.transactions.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByClient_Id(long clientId);
}
