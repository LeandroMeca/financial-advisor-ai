package com.spring.ia.application;

import com.spring.ia.domain.Category;
import com.spring.ia.domain.TransactionRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AnalyzeSpendingUseCase {

    @Autowired
    private TransactionRepository transactionRepository;

    @Tool(name = "analyze-spending", description = "Fornece um histórico de gastos de uma categoria para análise e aconselhamento financeiro")
    public String execute(@ToolParam(description = "A categoria dos gastos a ser analisada") Category category) {
        var transactions = transactionRepository.findAllByCategory(category);

        if (transactions.isEmpty()) {
            return "O usuário ainda não possui gastos registrados na categoria " + category.name() + ".";
        }

        // Soma total convertendo de centavos para reais
        double totalGasto = transactions.stream()
                .mapToLong(t -> t.getAmount())
                .sum() / 100.0;

        // Monta uma lista em texto com as descrições e valores
        String detalhes = transactions.stream()
                .map(t -> String.format("- %s: R$ %.2f", t.getDescription(), t.getAmount() / 100.0))
                .collect(Collectors.joining("\n"));

        // O retorno desta Tool será lido pela OpenAI como "Contexto" para ela formular a resposta final
        return String.format(
                "O usuário gastou um total de R$ %.2f na categoria %s.\nAqui estão os detalhes:\n%s\nCom base nisso, atue como um consultor financeiro: faça uma breve análise do perfil de consumo e dê uma dica construtiva.",
                totalGasto, category.name(), detalhes
        );
    }
}