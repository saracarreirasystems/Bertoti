 package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String URL = "jdbc:sqlite:A7.db";

    public static Connection connect() throws SQLException {
        Connection conn = DriverManager.getConnection(URL);
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("PRAGMA foreign_keys = ON;");
        } catch (SQLException e) {
            System.err.println("Erro ao ativar chaves estrangeiras: " + e.getMessage());
        }
        return conn;
    }

    public static void criarTabelas() {
        String sqlArtista = "CREATE TABLE IF NOT EXISTS artista (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL," +
                "nacionalidade TEXT," +
                "genero_musical TEXT" +
                ");";

        String sqlAlbum = "CREATE TABLE IF NOT EXISTS album (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titulo TEXT NOT NULL," +
                "ano_lancamento INTEGER," +
                "gravadora TEXT," +
                "artista_id INTEGER," +
                "FOREIGN KEY (artista_id) REFERENCES artista(id) ON DELETE CASCADE" +
                ");";

        String sqlFaixa = "CREATE TABLE IF NOT EXISTS faixa (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titulo TEXT NOT NULL," +
                "duracao TEXT," +
                "numero_faixa INTEGER," +
                "album_id INTEGER," +
                "FOREIGN KEY (album_id) REFERENCES album(id) ON DELETE CASCADE" +
                ");";

        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sqlArtista);
            stmt.execute(sqlAlbum);
            stmt.execute(sqlFaixa);
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }
}