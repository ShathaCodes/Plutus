package com.plutus.transactions.entities;

import com.plutus.transactions.entities.enumerations.AccountTransactionType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * This class represents a transaction done by the account holder ( withdraw / deposit )
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "account_transactions")
public class AccountTransaction extends Transaction{

    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;

    private AccountTransactionType type;
}
