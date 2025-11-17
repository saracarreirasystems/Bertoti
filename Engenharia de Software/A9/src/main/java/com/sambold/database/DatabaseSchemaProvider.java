package com.sambold.database;

import com.sambold.config.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseSchemaProvider {
    
    /**
     * Obtém o schema completo do banco de dados em formato textual
     * para enviar ao Ollama como contexto
     */
    public static String getDatabaseSchema() {
        StringBuilder schema = new StringBuilder();
        schema.append("BANCO DE DADOS: SAMBOLDAPI\n\n");
        schema.append("ESTRUTURA DAS TABELAS:\n\n");
        
        try (Connection conn = DatabaseConfig.getConnection()) {
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet tables = metaData.getTables("SAMBOLDAPI", null, "%", new String[]{"TABLE"});
            
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                schema.append("Tabela: ").append(tableName).append("\n");
                schema.append("Colunas:\n");
                
                // Obter colunas da tabela
                ResultSet columns = metaData.getColumns("SAMBOLDAPI", null, tableName, null);
                while (columns.next()) {
                    String columnName = columns.getString("COLUMN_NAME");
                    String columnType = columns.getString("TYPE_NAME");
                    int columnSize = columns.getInt("COLUMN_SIZE");
                    String isNullable = columns.getString("IS_NULLABLE");
                    
                    schema.append("  - ").append(columnName)
                          .append(" (").append(columnType);
                    
                    if (columnSize > 0 && !columnType.equals("TEXT") && !columnType.equals("LONGBLOB")) {
                        schema.append("(").append(columnSize).append(")");
                    }
                    
                    schema.append(", ")
                          .append(isNullable.equals("NO") ? "NOT NULL" : "NULL")
                          .append(")\n");
                }
                
                // Obter chaves primárias
                ResultSet primaryKeys = metaData.getPrimaryKeys("SAMBOLDAPI", null, tableName);
                List<String> pkColumns = new ArrayList<>();
                while (primaryKeys.next()) {
                    pkColumns.add(primaryKeys.getString("COLUMN_NAME"));
                }
                if (!pkColumns.isEmpty()) {
                    schema.append("  PRIMARY KEY: ").append(String.join(", ", pkColumns)).append("\n");
                }
                
                schema.append("\n");
            }
            
        } catch (SQLException e) {
            schema.append("Erro ao obter schema: ").append(e.getMessage());
        }
        
        return schema.toString();
    }
    
    /**
     * Obtém uma descrição resumida do schema para o prompt
     */
    public static String getSchemaDescription() {
        return """
            O banco de dados SAMBOLDAPI contém as seguintes tabelas principais:
            
            1. usuario: Armazena usuários (alunos e professores) com email, nome, curso, senha e perfil
            2. solicitacao: Gerencia solicitações entre alunos e professores
            3. TG_ALUNO: Armazena trabalhos de graduação dos alunos
            4. arquivo_prof_aluno: Arquivos compartilhados entre professor e aluno
            5. arquivo_prof: Arquivos do professor
            6. chat: Informações sobre chats entre aluno e professor
            7. chat_mensagem: Mensagens dos chats
            8. documento: Documentos enviados entre aluno e professor
            9. secao_0: Dados pessoais e profissionais dos alunos
            10. secao_api: Seções de projetos API (1 a 6) dos alunos
            11. feedback: Feedbacks dos professores para seções dos alunos
            
            Relacionamentos principais:
            - Usuários se relacionam através de email (chave primária)
            - Alunos e professores têm relacionamento N:N através de várias tabelas
            - Cada aluno pode ter até 6 seções de API
            - Feedbacks são dados por seção (0 a 6)
            """;
    }
}
