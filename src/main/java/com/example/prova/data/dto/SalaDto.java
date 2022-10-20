package com.example.prova.data.dto;

import com.example.prova.data.archetype.Dto;
import com.example.prova.data.model.Cinema;
import com.example.prova.data.model.Sala;
import com.example.prova.utils.Util;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SalaDto implements Dto {

    private String id;

    private String nomeSala;

    private String postiASedere;

    private String postiPrenotati;

    private String cinemaId;

    private String cancellato;


    @JsonIgnore
    private List<FilmDto> film;


    @Override
    public Sala toModel() {
        return Sala.builder()
                .id(Util.toLong(id))
                .nomeSala(nomeSala)
                .postiASedere(Util.toLong(postiASedere))
                .postiPrenotati(Util.toLong(postiPrenotati))
                .cancellato(Boolean.valueOf(cancellato))
                .cinema(new Cinema(cinemaId))
                .build();
    }
}
