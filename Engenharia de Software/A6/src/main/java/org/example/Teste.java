package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class Teste {

    @Test
    public void testCriacaoArtista() {
        Artista artista = new Artista("Sub Urban", "Estadunidense", "Dark Indie");

        assertEquals("Sub Urban", artista.getNome());
        assertEquals("Estadunidense", artista.getNacionalidade());
        assertEquals("Dark Indie", artista.getGeneroMusical());
    }

    @Test
    public void testCriacaoAlbum() {
        Album album = new Album("If Nevermore", 2025, "AWAL");

        assertEquals("If Nevermore", album.getTitulo());
        assertEquals(2025, album.getAnoLancamento());
        assertEquals("AWAL", album.getGravadora());
    }

    @Test
    public void testCriacaoFaixa() {
        Faixa faixa = new Faixa("Mycelium Eyes", "3:54", 1);

        assertEquals("Mycelium Eyes", faixa.getTitulo());
        assertEquals("3:54", faixa.getDuracao());
        assertEquals(1, faixa.getNumeroFaixa());
    }

    @Test
    public void testRelacionamentoAlbumFaixas() {
        Album album = new Album("If Nevermore", 2025, "AWAL");
        ArrayList<Faixa> faixas = new ArrayList<>();

        Faixa faixa1 = new Faixa("Mycelium Eyes", "3:54", 1);
        Faixa faixa2 = new Faixa("What You Sow", "3:32", 2);

        faixas.add(faixa1);
        faixas.add(faixa2);

        assertEquals(2, faixas.size());

        assertEquals("Mycelium Eyes", faixas.get(0).getTitulo());

        assertEquals("3:32", faixas.get(1).getDuracao());
    }
}