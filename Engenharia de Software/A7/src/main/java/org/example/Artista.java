package org.example;

public class Artista {
    private String nome;
    private String nacionalidade;
    private String generoMusical;

    public Artista(String nome, String nacionalidade, String generoMusical) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.generoMusical = generoMusical;
    }

    public void listarAlbuns() {
        System.out.println("O artista " + nome + ", de nacionalidade " + nacionalidade + ", que atua no gênero musical " + generoMusical + ", possui os seguintes álbuns:");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }
}