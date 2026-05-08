package com.spring.ia.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Transaction {

    private TransactionId transactionId;
    private String description;
    private long amount;
    private Category category;


    public Transaction(String description, long amount, Category category) {
        this.transactionId = new TransactionId();
        this.description = description;
        this.amount = amount;
        this.category = category;
    }



}
