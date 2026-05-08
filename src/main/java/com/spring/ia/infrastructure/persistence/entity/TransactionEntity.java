package com.spring.ia.infrastructure.persistence.entity;

import com.spring.ia.domain.Category;
import com.spring.ia.domain.Transaction;
import com.spring.ia.domain.TransactionId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {

    @Id
    private UUID id;
    private String description;
    private long amount;

    private Category category;



    public static TransactionEntity from(Transaction transaction){
        return new TransactionEntity(transaction.getTransactionId().uuid(), transaction.getDescription(),
                transaction.getAmount(), transaction.getCategory());
    }


    public Transaction toDomain() {
        return new Transaction(
                new TransactionId(this.id),
                this.description,
                this.amount,
                this.category
        );
    }


}
