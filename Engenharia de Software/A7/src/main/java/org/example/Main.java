package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        Database.criarTabelas();

        try (Connection conn = Database.connect()) {

            int artistaId = inserirArtista(conn);
            int albumId = inserirAlbum(conn, artistaId);

            String[][] faixas = {
                    {"Mycelium Eyes", "3:54"},
                    {"What You Sow", "3:32"},
                    {"Skinny Loser", "3:28"},
                    {"Stay Still", "3:09"},
                    {"Like Love", "2:19"},
                    {"In Sunder", "3:28"},
                    {"Clip thru U", "3:32"},
                    {"Mascara", "2:16"},
                    {"Make Me Forget", "3:05"},
                    {"Fingernails", "3:07"},
                    {"This Void Can Be Satiated", "2:46"},
                    {"Fill this empty mall with water and ivy", "3:10"},
                    {"See Myself", "2:31"},
                    {"When I Can't Make You Mine", "2:47"}
            };

            inserirFaixas(conn, faixas, albumId);
            consultarBanco(conn, albumId);

        } catch (SQLException e) {
            System.err.println("Erro de conexão: " + e.getMessage());
        }
    }

    private static int inserirArtista(Connection conn) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO artista(nome, nacionalidade, genero_musical) VALUES (?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, "Sub Urban");
            stmt.setString(2, "Estadunidense");
            stmt.setString(3, "Dark Indie");
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir artista: " + e.getMessage());
        }
        return -1;
    }

    private static int inserirAlbum(Connection conn, int artistaId) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO album(titulo, ano_lancamento, gravadora, artista_id) VALUES (?, ?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, "If Nevermore");
            stmt.setInt(2, 2025);
            stmt.setString(3, "AWAL");
            stmt.setInt(4, artistaId);
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir álbum: " + e.getMessage());
        }
        return -1;
    }

    private static void inserirFaixas(Connection conn, String[][] faixas, int albumId) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO faixa(titulo, duracao, numero_faixa, album_id) VALUES (?, ?, ?, ?)")) {
            for (int i = 0; i < faixas.length; i++) {
                stmt.setString(1, faixas[i][0]);
                stmt.setString(2, faixas[i][1]);
                stmt.setInt(3, i + 1);
                stmt.setInt(4, albumId);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir faixas: " + e.getMessage());
        }
    }

    private static void consultarBanco(Connection conn, int albumId) {
        String sqlConsulta = "SELECT a.nome AS artista, a.nacionalidade, a.genero_musical, " +
                "al.titulo AS album, al.ano_lancamento, al.gravadora, " +
                "f.numero_faixa, f.titulo AS faixa, f.duracao " +
                "FROM artista a " +
                "JOIN album al ON al.artista_id = a.id " +
                "JOIN faixa f ON f.album_id = al.id " +
                "WHERE al.id = ? " +
                "ORDER BY f.numero_faixa;";

        try (PreparedStatement stmt = conn.prepareStatement(sqlConsulta)) {
            stmt.setInt(1, albumId);
            try (ResultSet rs = stmt.executeQuery()) {
                boolean cabecalhoImpresso = false;
                while (rs.next()) {
                    if (!cabecalhoImpresso) {
                        System.out.println("Artista: " + rs.getString("artista"));
                        System.out.println("Nacionalidade: " + rs.getString("nacionalidade"));
                        System.out.println("Gênero: " + rs.getString("genero_musical"));
                        System.out.println("Álbum: " + rs.getString("album") +
                                " (" + rs.getInt("ano_lancamento") + "), Gravadora: " + rs.getString("gravadora"));
                        System.out.println("Faixas:");
                        cabecalhoImpresso = true;
                    }
                    System.out.println(rs.getInt("numero_faixa") + ". " +
                            rs.getString("faixa") + " (" + rs.getString("duracao") + ")");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao consultar banco: " + e.getMessage());
        }
    }
}