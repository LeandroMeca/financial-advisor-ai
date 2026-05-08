package com.spring.ia.infrastructure.http.request;


import com.spring.ia.application.input.PersistTransactionInput;
import com.spring.ia.domain.Category;

public record TransactionRequest(String description, Category category, long amount) {

    public PersistTransactionInput toInput() {
        return new PersistTransactionInput(description, amount, category);
    }
}
