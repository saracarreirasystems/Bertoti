package com.sambold.assistant;

import com.sambold.database.DatabaseQueryExecutor;
import com.sambold.database.DatabaseSchemaProvider;
import com.sambold.ollama.OllamaClient;

import java.io.IOException;

public class DatabaseAssistant {
    
    private final OllamaClient ollamaClient;
    private final String databaseSchema;
    
    public DatabaseAssistant() {
        this.ollamaClient = new OllamaClient();
        this.databaseSchema = DatabaseSchemaProvider.getDatabaseSchema();
    }
    
    /**
     * Processa uma pergunta em linguagem natural e retorna a resposta
     */
    public String ask(String question) {
        System.out.println("\n🤔 Pergunta: " + question);
        System.out.println("⏳ Processando...\n");
        
        // Verificar se o Ollama está rodando
        if (!ollamaClient.isOllamaRunning()) {
            return "❌ ERRO: O Ollama não está rodando! Por favor, inicie o Ollama com: ollama serve";
        }
        
        try {
            // Etapa 1: Gerar a query SQL
            String sqlQuery = generateSQLQuery(question);
            
            if (sqlQuery == null || sqlQuery.isEmpty()) {
                return "❌ Não foi possível gerar uma query SQL para esta pergunta.";
            }
            
            System.out.println("📝 Query SQL gerada:");
            System.out.println(sqlQuery);
            System.out.println();
            
            // Etapa 2: Executar a query
            String queryResults = DatabaseQueryExecutor.executeQuery(sqlQuery);
            
            System.out.println("📊 Resultados obtidos do banco de dados\n");
            
            // Etapa 3: Formatar resposta em linguagem natural
            String finalAnswer = generateNaturalLanguageResponse(question, sqlQuery, queryResults);
            
            return finalAnswer;
            
        } catch (IOException e) {
            return "❌ ERRO ao comunicar com o Ollama: " + e.getMessage();
        }
    }
    
    /**
     * Gera uma query SQL baseada na pergunta do usuário
     */
    private String generateSQLQuery(String question) throws IOException {
        String prompt = String.format("""
            Você é um especialista em SQL e banco de dados MySQL.
            
            ESTRUTURA DO BANCO DE DADOS:
            %s
            
            INSTRUÇÕES IMPORTANTES:
            1. Gere APENAS a query SQL, sem explicações
            2. Use apenas comandos SELECT (não use INSERT, UPDATE, DELETE, DROP)
            3. A query deve ser válida para MySQL
            4. Use JOIN quando necessário para relacionar tabelas
            5. Use LIMIT para limitar resultados quando apropriado
            6. Não inclua ponto e vírgula no final
            7. Retorne APENAS o código SQL, nada mais
            
            PERGUNTA DO USUÁRIO:
            %s
            
            SQL:
            """, databaseSchema, question);
        
        String response = ollamaClient.generate(prompt);
        
        // Limpar a resposta (remover markdown, espaços extras, etc)
        response = cleanSQLResponse(response);
        
        return response;
    }
    
    /**
     * Gera uma resposta em linguagem natural baseada nos resultados
     */
    private String generateNaturalLanguageResponse(String question, String sqlQuery, String queryResults) throws IOException {
        String prompt = String.format("""
            Você é um assistente amigável que explica resultados de banco de dados.
            
            PERGUNTA ORIGINAL DO USUÁRIO:
            %s
            
            QUERY SQL EXECUTADA:
            %s
            
            RESULTADOS DA QUERY:
            %s
            
            INSTRUÇÕES:
            1. Responda a pergunta do usuário em linguagem natural e amigável
            2. Use os resultados da query para fundamentar sua resposta
            3. Se houver muitos resultados, faça um resumo
            4. Se não houver resultados, explique isso claramente
            5. Use emojis quando apropriado para deixar a resposta mais amigável
            6. Seja conciso mas informativo
            
            RESPOSTA:
            """, question, sqlQuery, queryResults);
        
        return ollamaClient.generate(prompt);
    }
    
    /**
     * Limpa a resposta SQL removendo markdown e espaços extras
     */
    private String cleanSQLResponse(String response) {
        // Remover blocos de código markdown
        response = response.replaceAll("```sql\\s*", "");
        response = response.replaceAll("```\\s*", "");
        
        // Remover ponto e vírgula no final
        response = response.replaceAll(";\\s*$", "");
        
        // Remover quebras de linha extras e espaços
        response = response.trim();
        
        return response;
    }
    
    /**
     * Mostra informações sobre o banco de dados
     */
    public String showDatabaseInfo() {
        StringBuilder info = new StringBuilder();
        info.append("📊 INFORMAÇÕES DO BANCO DE DADOS SAMBOLDAPI\n\n");
        
        String[] tables = {
            "usuario", "solicitacao", "TG_ALUNO", "arquivo_prof_aluno",
            "arquivo_prof", "chat", "chat_mensagem", "documento",
            "secao_0", "secao_api", "feedback"
        };
        
        for (String table : tables) {
            int count = DatabaseQueryExecutor.countRecords(table);
            info.append(String.format("• %-25s: %d registros\n", table, count));
        }
        
        return info.toString();
    }
}
