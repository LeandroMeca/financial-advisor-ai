package com.spring.ia.application;


import com.spring.ia.application.input.PersistTransactionInput;
import com.spring.ia.application.output.TransactionOutput;
import com.spring.ia.domain.Transaction;
import com.spring.ia.domain.TransactionRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersistTransactionUseCase {

    @Autowired
    TransactionRepository transactionRepository;

    @Tool(name = "persist-transaction", description = "Persiste uma nova transação financeira")
    public TransactionOutput execute(PersistTransactionInput input){
        var transaction = transactionRepository.save(new Transaction(input.description(), input.amount(), input.category()));

        return TransactionOutput.from(transaction);
    }

}
