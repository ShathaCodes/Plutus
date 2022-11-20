package com.plutus.transactions.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * This class represents a transaction between two parts. A sender and a receiver.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "exchange_transactions")
public class ExchangeTransaction extends Transaction{

    @ManyToOne
    @JoinColumn(name="sender_id")
    private Account sender;

    @ManyToOne()
    @JoinColumn(name="receiver_id")
    private Account receiver;

}
