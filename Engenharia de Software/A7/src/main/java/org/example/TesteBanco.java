package org.example;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteBanco {

    @Test
    public void testConexaoEBasico() throws SQLException {
        Database.criarTabelas();

        try (Connection conn = Database.connect()) {

            int artistaId = -1;

            try (PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO artista(nome, nacionalidade, genero_musical) VALUES (?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, "Teste Artista");
                stmt.setString(2, "Brasil");
                stmt.setString(3, "Teste Genero");
                stmt.executeUpdate();

                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        artistaId = rs.getInt(1);
                    }
                }
            }

            try (PreparedStatement stmt = conn.prepareStatement(
                    "SELECT nome, nacionalidade, genero_musical FROM artista WHERE id = ?")) {
                stmt.setInt(1, artistaId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        assertEquals("Teste Artista", rs.getString("nome"));
                        assertEquals("Brasil", rs.getString("nacionalidade"));
                        assertEquals("Teste Genero", rs.getString("genero_musical"));
                    }
                }
            }
        }
    }
}