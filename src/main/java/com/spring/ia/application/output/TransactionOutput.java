package com.spring.ia.application.output;


import com.spring.ia.domain.Transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record TransactionOutput(String id, String description, String category, double value) {

    public static TransactionOutput from(Transaction transaction){
        // Divide por 100 para converter de centavos para moeda decimal
        double valorDecimal = BigDecimal.valueOf(transaction.getAmount())
                .divide(new BigDecimal(100))
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();

        return new TransactionOutput(
                transaction.getTransactionId().uuid().toString(),
                transaction.getDescription(),
                transaction.getCategory().name(),
                valorDecimal
        );
    }

}
