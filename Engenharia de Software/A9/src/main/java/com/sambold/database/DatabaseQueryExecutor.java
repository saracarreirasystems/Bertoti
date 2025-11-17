package com.sambold.database;

import com.sambold.config.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DatabaseQueryExecutor {
    
    /**
     * Executa uma query SELECT e retorna os resultados formatados
     */
    public static String executeQuery(String sql) {
        StringBuilder result = new StringBuilder();
        
        // Validação básica de segurança
        if (!isSelectQuery(sql)) {
            return "❌ ERRO: Apenas queries SELECT são permitidas por segurança!";
        }
        
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            // Cabeçalhos das colunas
            result.append("RESULTADOS DA QUERY:\n");
            result.append("SQL: ").append(sql).append("\n\n");
            
            List<String> columnNames = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(metaData.getColumnName(i));
            }
            
            // Linha de cabeçalho
            result.append(String.join(" | ", columnNames)).append("\n");
            result.append("-".repeat(100)).append("\n");
            
            // Linhas de dados
            int rowCount = 0;
            while (rs.next()) {
                List<String> rowData = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    Object value = rs.getObject(i);
                    // Tratamento especial para BLOBs
                    if (value instanceof byte[]) {
                        rowData.add("[BLOB - " + ((byte[]) value).length + " bytes]");
                    } else {
                        rowData.add(value != null ? value.toString() : "NULL");
                    }
                }
                result.append(String.join(" | ", rowData)).append("\n");
                rowCount++;
                
                // Limitar a 50 linhas para não sobrecarregar
                if (rowCount >= 50) {
                    result.append("... (mostrando apenas 50 primeiras linhas)\n");
                    break;
                }
            }
            
            if (rowCount == 0) {
                result.append("(Nenhum registro encontrado)\n");
            } else {
                result.append("\nTotal de registros retornados: ").append(rowCount).append("\n");
            }
            
        } catch (SQLException e) {
            result.append("❌ ERRO ao executar query: ").append(e.getMessage()).append("\n");
        }
        
        return result.toString();
    }
    
    /**
     * Executa uma query e retorna os resultados em formato de lista de mapas
     */
    public static List<Map<String, Object>> executeQueryToList(String sql) {
        List<Map<String, Object>> results = new ArrayList<>();
        
        if (!isSelectQuery(sql)) {
            return results;
        }
        
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            while (rs.next()) {
                Map<String, Object> row = new LinkedHashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = rs.getObject(i);
                    
                    // Tratamento especial para BLOBs
                    if (value instanceof byte[]) {
                        row.put(columnName, "[BLOB - " + ((byte[]) value).length + " bytes]");
                    } else {
                        row.put(columnName, value);
                    }
                }
                results.add(row);
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao executar query: " + e.getMessage());
        }
        
        return results;
    }
    
    /**
     * Valida se a query é um SELECT (segurança básica)
     */
    private static boolean isSelectQuery(String sql) {
        String trimmedSql = sql.trim().toUpperCase();
        return trimmedSql.startsWith("SELECT");
    }
    
    /**
     * Conta registros de uma tabela
     */
    public static int countRecords(String tableName) {
        String sql = "SELECT COUNT(*) as total FROM " + tableName;
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao contar registros: " + e.getMessage());
        }
        return 0;
    }
}
