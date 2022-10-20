package com.example.prova.utils;

import com.example.prova.data.dto.BigliettoDto;
import com.example.prova.data.model.Film;
import com.example.prova.data.model.Sala;
import com.example.prova.data.model.Spettatore;
import com.example.prova.exception.FilmVietatoAiMinori;
import com.example.prova.repository.FilmRepository;
import com.example.prova.repository.SpettatoreRepository;
import com.example.prova.repository.film.SalaRepository;
import com.example.prova.service.BigliettoService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TicketValidator {

    @Autowired
    BigliettoService bigliettoService;

    @Autowired
    SpettatoreRepository spettatoreRepository;

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    SalaRepository salaRepository;


    public static BigliettoDto validaBiglietto(@NonNull BigliettoDto bigliettoDto, Spettatore spettatore, Film film) {

        LocalDateTime dataDiNascita = LocalDateTime.ofInstant(spettatore.getNascita(), ZoneOffset.UTC);
        LocalDateTime currentYear = LocalDateTime.now();
        int eta =  AgeCalculator.calculateAge(dataDiNascita, currentYear);
        if(eta > 70){
            Double prezzo = Double.parseDouble(bigliettoDto.getPrezzo());
            Double sconto = (prezzo * 10) / 100;
            Double prezzoBigliettoScontato = prezzo - sconto;
            bigliettoDto.setPrezzo(String.valueOf(prezzoBigliettoScontato));
        }
        if(eta < 5){
            Double prezzo = Double.parseDouble(bigliettoDto.getPrezzo());
            Double sconto = (prezzo * 50) / 100;
            Double prezzoBigliettoScontato = prezzo - sconto;
            bigliettoDto.setPrezzo(String.valueOf(prezzoBigliettoScontato));
        }

        if(film.getEtaMinima() == 18 && eta < 18){

            throw new FilmVietatoAiMinori("il film è vietato ai minori di 18 anni");
        }
     return null;
    } ;
}
