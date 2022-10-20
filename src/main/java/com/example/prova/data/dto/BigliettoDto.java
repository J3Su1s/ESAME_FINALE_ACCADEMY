package com.example.prova.data.dto;

import com.example.prova.data.archetype.Dto;
import com.example.prova.data.model.Biglietto;
import com.example.prova.data.model.Film;
import com.example.prova.data.model.Sala;
import com.example.prova.data.model.Spettatore;
import com.example.prova.utils.Util;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BigliettoDto implements Dto {

    private String id;

    private String postoAssegnato;

    private String prezzo;

    private String cancellato;

    private String intestatarioId;

    private String filmId;

   // private String bigliettoId;

    private String salaId;


    @JsonIgnore
    private List<SpettatoreDto> acquirenti;

    @Override
    public Biglietto toModel() {

        return Biglietto.builder()
                .id(Util.toLong(id))
                .cancellato(Boolean.valueOf(cancellato))
                .prezzo(Double.valueOf(prezzo))
                .intestatarioId(Util.toLong(intestatarioId))
                .postoAssegnato(postoAssegnato)
                .acquirente(new Spettatore(intestatarioId))
                .filmId(Util.toLong(filmId))
                .film(new Film(filmId))
                .sala(new Sala((salaId)))
                .build();
    }
}
