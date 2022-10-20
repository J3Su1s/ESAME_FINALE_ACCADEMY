package com.example.prova.data.dto;

import com.example.prova.data.archetype.Dto;
import com.example.prova.data.model.Spettatore;
import com.example.prova.utils.Util;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpettatoreDto implements Dto {

    private String id;

    private String nome;

    private String nascita;

    public String cancellato;

    @JsonIgnore
    private List<BigliettoDto> biglietti;

    @Override
    public Spettatore toModel() {
        return Spettatore.builder()
                .id(Util.toLong(id))
                .nome(nome)
                .nascita(Util.toInstant(nascita))
                .cancellato(Boolean.valueOf(cancellato))
                .build();
    }
}
