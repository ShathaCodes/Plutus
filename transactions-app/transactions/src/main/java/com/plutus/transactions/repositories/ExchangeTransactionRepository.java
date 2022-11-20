package com.plutus.transactions.repositories;

import com.plutus.transactions.entities.ExchangeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeTransactionRepository extends JpaRepository<ExchangeTransaction, Long> {
}
