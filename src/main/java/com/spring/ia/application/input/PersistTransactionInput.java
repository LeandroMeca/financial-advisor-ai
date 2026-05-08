package com.spring.ia.application.input;


import com.spring.ia.domain.Category;
import org.springframework.ai.tool.annotation.ToolParam;

public record PersistTransactionInput(@ToolParam(description = "Descrição curta do gasto (ex: Padaria, Gasolina)") String description,
                                      @ToolParam(description = "Valor total em cêntimos. Ex: 10.50€ vira 1050") long amount,
                                      @ToolParam(description = "Categoria obrigatória: GROCERIES, PHARMA ou AUTO") Category category){


}
