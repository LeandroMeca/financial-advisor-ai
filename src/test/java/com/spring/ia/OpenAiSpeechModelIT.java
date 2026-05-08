package com.spring.ia;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
public class OpenAiSpeechModelIT {


    @Autowired
    OpenAiAudioSpeechModel openAiAudioSpeechModel;

    @Test
    public void shouldProduceAudioWhenTextIsProvided() throws IOException {
        var response = openAiAudioSpeechModel.call("o valor do serviço ficou 80 reais. Posso confirmar o pagamento");

        assertThat(response).hasSizeGreaterThan(2048);

        var tempFile = File.createTempFile("AUDIO_",".mp3");
        Files.write(tempFile.toPath(), response);
        System.out.println(tempFile.toPath().toAbsolutePath());
    }



}
