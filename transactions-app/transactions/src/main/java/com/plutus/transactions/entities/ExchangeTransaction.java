package com.plutus.transactions.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * This class represents a transaction between two parts. A sender and a receiver.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "exchange_transactions")
public class ExchangeTransaction extends Transaction{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sender_id")
    @JsonIgnore
    private Account sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="receiver_id")
    @JsonIgnore
    private Account receiver;

    @Column(name = "sender_id", insertable = false, updatable = false)
    private long senderId;

    @Column(name = "receiver_id", insertable = false, updatable = false)
    private long receiverId;
}
