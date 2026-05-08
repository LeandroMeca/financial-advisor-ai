package com.spring.ia;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
public class ToolCallingIT {

    @Autowired
    OpenAiChatModel openAiChatModel;

    static class MathTools{
        @Tool(description = "soma os dois numeros inteiros, a e b")
        public int sum(int a, int b){
            return a + b;
        }
        @Tool(description = "subtrai os dois numeros inteiros, a e b")
        public int subtract(int a, int b){
            return  a - b;
        }
    }

    @Test
    void should_execute_sum_when_prompted(){
        var chatClient = ChatClient.builder(openAiChatModel).defaultSystem("você é um matematico")
                .defaultTools(new MathTools())
                .build();

       var response =  chatClient.prompt("quanto é 1 + 2")
               .call().content();

       assertThat(response).contains("3");
        System.out.println(response);

    }




}
