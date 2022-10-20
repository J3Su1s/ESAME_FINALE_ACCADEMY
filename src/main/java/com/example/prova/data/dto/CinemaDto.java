package com.example.prova.data.dto;

import com.example.prova.data.archetype.Dto;
import com.example.prova.data.model.Cinema;
import com.example.prova.utils.Util;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;


@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CinemaDto implements Dto {

    private String id;

    private String name;

    private String cancellato;

    @JsonIgnore
    List<SalaDto> saleCinematografiche;


    @Override
    public Cinema toModel() {
        return Cinema.builder()
                .id(Util.toLong(id))
                .name(name)
                .cancellato(Boolean.valueOf(cancellato))
                .build();
    }
}
