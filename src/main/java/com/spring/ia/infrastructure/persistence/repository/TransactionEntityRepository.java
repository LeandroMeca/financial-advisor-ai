package com.spring.ia.infrastructure.persistence.repository;

import com.spring.ia.domain.Category;
import com.spring.ia.infrastructure.persistence.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionEntityRepository extends CrudRepository<TransactionEntity, UUID> {

    List<TransactionEntity> findAllByCategory(Category category);


}
