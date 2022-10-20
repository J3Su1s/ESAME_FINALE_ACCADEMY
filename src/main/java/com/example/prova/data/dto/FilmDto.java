package com.example.prova.data.dto;

import com.example.prova.data.archetype.Dto;
import com.example.prova.data.model.Film;
import com.example.prova.data.model.Sala;
import com.example.prova.utils.Util;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Set;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmDto implements Dto {

    private String id;

    private String titolo;

    private String regista;

    private String casaProduttrice;

    private String genere;

    private String durata;

    private String etaMinima;

    private String cancellato;

    private String salaId;

    @JsonIgnore
    private Set<Sala> sale;

    @Override
    public Film toModel() {

        return Film.builder()
                .id(Util.toLong(id))
                .titolo(titolo)
                .genere(Util.getGenere(genere))
                .regista(regista)
                .casaProduttrice(casaProduttrice)
                .durata(Util.toLong(durata))
                .etaMinima(Util.toLong(etaMinima))
                .salaId(Util.toLong(salaId))
                .cancellato(Boolean.valueOf(cancellato))
                .build();
    }
}
