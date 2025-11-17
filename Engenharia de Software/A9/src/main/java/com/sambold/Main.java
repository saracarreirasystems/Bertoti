package com.sambold;

import com.sambold.assistant.DatabaseAssistant;
import com.sambold.config.DatabaseConfig;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║   SAMBOLD API - Assistente de Banco de Dados com IA     ║");
        System.out.println("║          Powered by Ollama (Gemma2:12b)                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println();
        
        // Testar conexão com banco de dados
        try {
            DatabaseConfig.getConnection();
        } catch (Exception e) {
            System.err.println("❌ ERRO: Não foi possível conectar ao banco de dados!");
            System.err.println("Verifique se o MySQL está rodando e as credenciais em DatabaseConfig.java");
            System.err.println("Erro: " + e.getMessage());
            return;
        }
        
        // Inicializar o assistente
        DatabaseAssistant assistant = new DatabaseAssistant();
        
        // Mostrar menu
        showMenu();
        
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            System.out.print("\n💬 Você: ");
            String input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                continue;
            }
            
            switch (input.toLowerCase()) {
                case "sair", "exit", "quit" -> {
                    System.out.println("\n👋 Até logo!");
                    running = false;
                }
                case "info" -> System.out.println("\n" + assistant.showDatabaseInfo());
                case "ajuda", "help" -> showMenu();
                case "limpar", "clear" -> {
                    // Limpar tela (funciona em alguns terminais)
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    showMenu();
                }
                default -> {
                    String response = assistant.ask(input);
                    System.out.println("\n🤖 Assistente: " + response);
                }
            }
        }
        
        // Fechar conexão
        DatabaseConfig.closeConnection();
        scanner.close();
    }
    
    private static void showMenu() {
        System.out.println("""
            
            📋 COMANDOS DISPONÍVEIS:
            • Digite qualquer pergunta sobre o banco de dados
            • 'info'   - Mostra estatísticas do banco
            • 'ajuda'  - Mostra este menu
            • 'limpar' - Limpa a tela
            • 'sair'   - Encerra o programa
            
            💡 EXEMPLOS DE PERGUNTAS:
            • "Quantos usuários existem no banco?"
            • "Mostre todos os alunos do curso de ADS"
            • "Quais são os professores cadastrados?"
            • "Liste as 5 últimas mensagens do chat"
            • "Quantas seções de API existem?"
            • "Mostre feedbacks aprovados"
            • "Quais documentos foram enviados hoje?"
            
            ⚠️  IMPORTANTE:
            • Certifique-se de que o Ollama está rodando (ollama serve)
            • Verifique se o modelo gemma2:12b está instalado
            • A primeira pergunta pode demorar mais (carregamento do modelo)
            """);
    }
    
    /**
     * Método para testar o sistema com perguntas pré-definidas
     */
    public static void runTests() {
        DatabaseAssistant assistant = new DatabaseAssistant();
        
        String[] testQuestions = {
            "Quantos usuários existem no banco?",
            "Liste todos os nomes dos usuários",
            "Quantas mensagens de chat foram enviadas?",
            "Mostre as tabelas do banco de dados"
        };
        
        System.out.println("\n🧪 EXECUTANDO TESTES...\n");
        
        for (String question : testQuestions) {
            System.out.println("═".repeat(60));
            String answer = assistant.ask(question);
            System.out.println("🤖 Resposta: " + answer);
            System.out.println();
        }
    }
}
