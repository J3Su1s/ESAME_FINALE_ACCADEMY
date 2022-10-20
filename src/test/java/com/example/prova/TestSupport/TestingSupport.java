package com.example.prova.TestSupport;

import com.example.prova.data.enums.Genere;
import com.example.prova.data.model.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestingSupport {

    /* cinema */
    public static Cinema createCinema(Long id) {
        return Cinema.builder()
                .cancellato(Boolean.FALSE)
                .id(id).build();
    }
    public static List<Cinema> createCinemaList(){
        return List.of(createCinema(1L), createCinema(2L));
    }

    /* spettatore */
    public static Spettatore createSpettatore(Long id) {
        var Sala = new Sala();
        var biglietto = new Biglietto();

        return Spettatore.builder()
                .cancellato(Boolean.FALSE)
                .nascita(Instant.MIN)
                .nome("")
                .id(id).build();

    }
    public static List<Spettatore> createSpettatoreList(){
        return List.of(createSpettatore(1L), createSpettatore(2L));
    }

    /* biglietto  */
    public static Biglietto createBiglietto(Long id) {

        return Biglietto.builder()
                .postoAssegnato("")
                .cancellato(Boolean.FALSE)
                .prezzo(12.02)
                .id(id).build();

    }
    public static List<Biglietto> createBigliettoList(){
        return List.of(createBiglietto(1L), createBiglietto(2L));
    }

    /* proiezione */
    public static Film createFilm(Long id) {

        Set<Sala> sale = new HashSet<>();
        return Film.builder()
                .id(id)
                .etaMinima(18l)
                .durata(120l)
                .casaProduttrice("")
                .regista("")
                .genere(Genere.FANTASY)
                .titolo("")
                .sale(sale)
                .build();

    }
    public static List<Film> createProiezioneList(){
        return List.of(createFilm(1L), createFilm(2L));
    }

    /* Sala */

    public static Sala createSala(Long id) {

        Set<Film> film = new HashSet<>();
        return Sala.builder()
                .id(id)
                .cinema(createCinema(1l))
                .postiASedere(55l)
                .cancellato(Boolean.FALSE)
                .film(film)
                .build();

    }
    public static List<Sala> createSalaList(){
        return List.of(createSala(1L), createSala(2L));
    }
}
