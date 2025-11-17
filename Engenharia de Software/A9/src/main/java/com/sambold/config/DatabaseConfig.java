package com.sambold.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    
    private static final String URL = "jdbc:mysql://localhost:3306/SAMBOLDAPI";
    private static final String USER = "root"; 
    private static final String PASSWORD = ""; 
    
    private static Connection connection = null;
    
    /**
     * Obtém uma conexão com o banco de dados MySQL
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✓ Conexão com banco de dados estabelecida!");
            } catch (ClassNotFoundException e) {
                throw new SQLException("Driver MySQL não encontrado!", e);
            }
        }
        return connection;
    }
    
    /**
     * Fecha a conexão com o banco de dados
     */
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("✓ Conexão com banco de dados fechada!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }
}
