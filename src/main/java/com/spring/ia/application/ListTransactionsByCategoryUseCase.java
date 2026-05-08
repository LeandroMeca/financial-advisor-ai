package com.spring.ia.application;


import com.spring.ia.application.output.TransactionOutput;
import com.spring.ia.domain.Category;
import com.spring.ia.domain.TransactionRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListTransactionsByCategoryUseCase {

    @Autowired
    TransactionRepository transactionRepository;

    @Tool(name = "list-transactions-by-category", description = "Lista transações financeiras por categoria")
    public List<TransactionOutput> execute(@ToolParam(description = "Categoria de uma transação") Category category){
        return transactionRepository.findAllByCategory(category).stream().map(TransactionOutput::from).toList();
    }
}
