package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Faixa faixa1 = new Faixa("Mycelium Eyes", "3:54", 1);
        Faixa faixa2 = new Faixa("What You Sow", "3:32", 2);
        Faixa faixa3 = new Faixa("Skinny Loser", "3:28", 3);
        Faixa faixa4 = new Faixa("Stay Still", "3:09", 4);
        Faixa faixa5 = new Faixa("Like Love", "2:19", 5);
        Faixa faixa6 = new Faixa("In Sunder", "3:28", 6);
        Faixa faixa7 = new Faixa("Clip thru U", "3:32", 7);
        Faixa faixa8 = new Faixa("Mascara", "2:16", 8);
        Faixa faixa9 = new Faixa("Make Me Forget", "3:05", 9);
        Faixa faixa10 = new Faixa("Fingernails", "3:07", 10);
        Faixa faixa11 = new Faixa("This Void Can Be Satiated", "2:46", 11);
        Faixa faixa12 = new Faixa("Fill this empty mall with water and ivy", "3:10", 12);
        Faixa faixa13 = new Faixa("See Myself", "2:31", 13);
        Faixa faixa14 = new Faixa("When I Can't Make You Mine", "2:47", 14);

        ArrayList<Faixa> faixasAlbum = new ArrayList<>();
        faixasAlbum.add(faixa1);
        faixasAlbum.add(faixa2);
        faixasAlbum.add(faixa3);
        faixasAlbum.add(faixa4);
        faixasAlbum.add(faixa5);
        faixasAlbum.add(faixa6);
        faixasAlbum.add(faixa7);
        faixasAlbum.add(faixa8);
        faixasAlbum.add(faixa9);
        faixasAlbum.add(faixa10);
        faixasAlbum.add(faixa11);
        faixasAlbum.add(faixa12);
        faixasAlbum.add(faixa13);
        faixasAlbum.add(faixa14);

        Album album = new Album("If Nevermore", 2025, "AWAL");

        Artista artista = new Artista("Sub Urban", "Estadunidense", "Dark Indie");

        System.out.println("O artista " + artista.getNome() + ", de nacionalidade " + artista.getNacionalidade() + ", que atua no gênero musical " + artista.getGeneroMusical() + ", lançou em " + album.getAnoLancamento() + " o álbum \"" + album.getTitulo() + "\", pela gravadora " + album.getGravadora() + ".");
        System.out.println("O álbum inclui as seguintes faixas:");
        for (Faixa faixa : faixasAlbum) {
            System.out.println(faixa.getNumeroFaixa() + ". " + faixa.getTitulo() + " (" + faixa.getDuracao() + ")");
        }
    }
}