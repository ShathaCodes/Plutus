package com.plutus.transactions.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

/**
 * This is a mapped superclass. And thus, not created in the database.
 */
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class Transaction {
    @Id
    @GeneratedValue
    private long id;

    private double amount;

    private Timestamp timestamp;

    public Transaction(double amount, Timestamp timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }
}
