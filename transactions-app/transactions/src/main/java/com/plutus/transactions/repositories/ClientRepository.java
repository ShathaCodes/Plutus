package com.plutus.transactions.repositories;

import com.plutus.transactions.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
