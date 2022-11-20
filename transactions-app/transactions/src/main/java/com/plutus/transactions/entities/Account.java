package com.plutus.transactions.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Each client can have more than one account.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    @Column(name = "balance")
    private double balance;

    public Account(Client client, double balance) {
        this.client = client;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", client=" + client +
                ", balance=" + balance +
                '}';
    }
}
