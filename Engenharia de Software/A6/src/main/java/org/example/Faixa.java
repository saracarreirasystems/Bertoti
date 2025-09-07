package org.example;

public class Faixa {
    private String titulo;
    private String duracao;
    private int numeroFaixa;

    public Faixa(String titulo, String duracao, int numeroFaixa) {
        this.titulo = titulo;
        this.duracao = duracao;
        this.numeroFaixa = numeroFaixa;
    }

    public void tocar() {
        System.out.println("A faixa " + numeroFaixa + " intitulada " + titulo + " possui duração de " + duracao + " e está sendo tocada agora.");
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public int getNumeroFaixa() {
        return numeroFaixa;
    }

    public void setNumeroFaixa(int numeroFaixa) {
        this.numeroFaixa = numeroFaixa;
    }
}


