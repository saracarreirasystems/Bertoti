package org.example;

public class Album {
    private String titulo;
    private int anoLancamento;
    private String gravadora;

    public Album(String titulo, int anoLancamento, String gravadora) {
        this.titulo = titulo;
        this.anoLancamento = anoLancamento;
        this.gravadora = gravadora;
    }

    public void listarFaixas() {
        System.out.println("O álbum " + titulo + " lançado em " + anoLancamento + " pela gravadora " + gravadora + " possui as seguintes faixas:");
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getGravadora() {
        return gravadora;
    }

    public void setGravadora(String gravadora) {
        this.gravadora = gravadora;
    }
}