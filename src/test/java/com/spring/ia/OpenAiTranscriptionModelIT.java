package com.spring.ia;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
public class OpenAiTranscriptionModelIT {


    @Autowired
    OpenAiAudioTranscriptionModel openAiAudioTranscriptionModel;

    @ParameterizedTest
    @CsvSource({
            "Gravando.m4a, apresentação"
    })
    public void shouldContainExpectedKeywordsWhenAudioFilesAreProcessed(String filename, String expectedkeyWords){
        var recording = new ClassPathResource("audio/"+filename);
        var response = openAiAudioTranscriptionModel.call(recording);

        assertThat(response).isNotEmpty();
        assertThat(response).contains(expectedkeyWords);
        System.out.println(response);
    }



}
