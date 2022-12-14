package com.plutus.transactions.repositories;
import com.plutus.transactions.entities.ExchangeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExchangeTransactionRepository extends JpaRepository<ExchangeTransaction, Long> {

    List<ExchangeTransaction> findBySenderIdOrReceiverId(long senderId, long receiverId);

}
