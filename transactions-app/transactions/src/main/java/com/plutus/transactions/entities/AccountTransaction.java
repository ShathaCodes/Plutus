package com.plutus.transactions.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_id")
    @JsonIgnore
    private Account account;

    @Column(name = "account_id", insertable = false, updatable = false)
    private Long accountId;

    @Column(name = "type")
    private AccountTransactionType type;
}
