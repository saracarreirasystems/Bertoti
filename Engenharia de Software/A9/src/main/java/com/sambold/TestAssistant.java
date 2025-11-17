package com.sambold;

import com.sambold.assistant.DatabaseAssistant;
import com.sambold.config.DatabaseConfig;

/**
 * Classe para testar o assistente com perguntas pré-definidas
 * Útil para demonstrações e testes rápidos
 */
public class TestAssistant {
    
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║        TESTE DO ASSISTENTE DE BANCO DE DADOS             ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println();
        
        try {
            DatabaseConfig.getConnection();
            System.out.println("✓ Conexão com banco estabelecida!\n");
        } catch (Exception e) {
            System.err.println("❌ Erro ao conectar ao banco: " + e.getMessage());
            return;
        }
        
        DatabaseAssistant assistant = new DatabaseAssistant();
        
        // Perguntas de teste
        String[] testQuestions = {
            "Quantos usuários existem no banco de dados?",
            "Liste o nome de todos os usuários",
            "Quantos alunos estão cadastrados?",
            "Mostre os últimos 5 documentos enviados",
            "Quantas mensagens de chat existem?",
            "Liste as seções de API cadastradas",
            "Quantos feedbacks foram dados?",
            "Mostre os usuários do curso de ADS"
        };
        
        System.out.println("🧪 Iniciando bateria de testes...\n");
        
        for (int i = 0; i < testQuestions.length; i++) {
            System.out.println("═".repeat(70));
            System.out.println("TESTE " + (i + 1) + " de " + testQuestions.length);
            System.out.println("═".repeat(70));
            
            String answer = assistant.ask(testQuestions[i]);
            System.out.println("🤖 Resposta: " + answer);
            System.out.println();
            
            // Aguardar um pouco entre as perguntas
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        System.out.println("═".repeat(70));
        System.out.println("✓ Testes concluídos!");
        System.out.println("═".repeat(70));
        
        // Mostrar informações do banco
        System.out.println("\n" + assistant.showDatabaseInfo());
        
        DatabaseConfig.closeConnection();
    }
}
