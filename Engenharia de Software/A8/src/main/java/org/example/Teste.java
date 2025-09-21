package org.example;

import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.exceptions.OllamaBaseException;
import io.github.ollama4j.models.response.OllamaResult;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class Teste {

    @Test
    public void testPerguntaSimples() throws OllamaBaseException, IOException, InterruptedException {
        String host = "http://127.0.0.1:11434/";
        String modelName = "qwen3:4b";

        OllamaAPI ollamaAPI = new OllamaAPI(host);
        ollamaAPI.setRequestTimeoutSeconds(180);

        String pergunta = "Qual é a capital do Brasil?";
        OllamaResult result = ollamaAPI.generate(modelName, pergunta, null);

        String resposta = result.getResponse();

        assertNotNull(resposta);
        assertFalse(resposta.isEmpty());
    }
}