package com.example.prova.data.model;

import com.example.prova.data.archetype.Model;
import com.example.prova.data.dto.CinemaDto;
import com.example.prova.data.dto.SalaDto;
import com.example.prova.utils.Util;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity

@Table(name = "cinema")
@SQLDelete(sql = "UPDATE cinema c SET c.cancellato = true WHERE c.id=?")
@Where(clause = "cancellato=false")
public class Cinema implements Model {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name="cancellato")
    @Builder.Default
    private Boolean cancellato = false;

    @OneToMany(mappedBy="cinema", fetch=FetchType.EAGER)
    List<Sala> saleCinematografiche;

    public Cinema(String cinemaId) {
        if(cinemaId != null) {
            this.id = Util.toLong(cinemaId);
        }
    }

    @Override
    public CinemaDto toDto() {
        List<SalaDto> saleDto = new ArrayList<>();
        if(saleCinematografiche != null){
            saleDto = saleCinematografiche.stream().map(Sala::toDto).collect(Collectors.toList());
        }
        return CinemaDto.builder()
                .id(String.valueOf(id))
                .name(name)
                .saleCinematografiche(saleDto)
                .build();
    }
}
