package com.spring.ia.domain;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository {
    Transaction save(Transaction transaction);
    List<Transaction> findAllByCategory(Category category);
}
