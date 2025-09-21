package org.example;

import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.exceptions.OllamaBaseException;
import io.github.ollama4j.models.response.OllamaResult;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String host = "http://127.0.0.1:11434/";
        String modelName = "qwen3:4b";

        OllamaAPI ollamaAPI = new OllamaAPI(host);
        ollamaAPI.setRequestTimeoutSeconds(120);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao chat A8.");
        System.out.println("Digite sua pergunta e pressione Enter para receber uma resposta.");
        System.out.println("Para encerrar o programa, digite 'Sair'.");

        while (true) {
            System.out.print("Pergunta: ");
            String userInput = scanner.nextLine().trim();

            if (userInput.equalsIgnoreCase("Sair")) {
                System.out.println("Encerrando o chat. Obrigado por utilizar o A8.");
                break;
            }

            if (userInput.isEmpty()) {
                System.out.println("Nenhuma entrada detectada. Por favor, digite sua pergunta.");
                continue;
            }

            try {
                OllamaResult result = ollamaAPI.generate(modelName, userInput, null);
                System.out.println("Resposta: " + result.getResponse());
            } catch (OllamaBaseException | IOException | InterruptedException e) {
                System.err.println("Ocorreu um erro ao gerar a resposta: " + e.getMessage());
            }
        }

        scanner.close();
    }
}