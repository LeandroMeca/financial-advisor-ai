package com.spring.ia.infrastructure.persistence.repository;

import com.spring.ia.domain.Category;
import com.spring.ia.domain.Transaction;
import com.spring.ia.domain.TransactionRepository;
import com.spring.ia.infrastructure.persistence.entity.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaTransactionRepository implements TransactionRepository {

    @Autowired
    TransactionEntityRepository transactionEntityRepository;

    @Override
    public Transaction save(Transaction transaction) {
        var entity = TransactionEntity.from(transaction);
        return transactionEntityRepository.save(entity).toDomain();
    }

    @Override
    public List<Transaction> findAllByCategory(Category category) {
        return transactionEntityRepository.findAllByCategory(category)
                .stream().map(TransactionEntity::toDomain).toList();

    }
}
