package com.example.prova.data.model;

import com.example.prova.data.archetype.Model;
import com.example.prova.data.dto.BigliettoDto;
import com.example.prova.data.dto.SpettatoreDto;
import com.example.prova.utils.Util;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "spettatore")
@SQLDelete(sql = "UPDATE spettatore s SET s.deleted = true WHERE id=? ")
@Where(clause = "deleted = false")
@Entity
public class Spettatore implements Model {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "nascita")
    private Instant nascita;

    @Column(name="deleted")
    @Builder.Default
    private Boolean cancellato = false;

    @OneToMany(mappedBy = "acquirente", fetch = FetchType.EAGER )
    private List<Biglietto> biglietti;

    public Spettatore(String spettatoreId) {
        if(spettatoreId != null) {
            this.id = Util.toLong(spettatoreId);
        }
    }

    // metodi controllo e calcolo età;

    public Boolean calcolaMaggiorenne(SpettatoreDto spettatoreDto){
        LocalDateTime dataDiNascita = LocalDateTime.ofInstant(Instant.parse(spettatoreDto.getNascita()), ZoneOffset.UTC);
        LocalDateTime currentYear = LocalDateTime.now();
        int età = currentYear.getYear() - dataDiNascita.getYear();
        if(età > 18){
            return true;
        }
        else
            return false;
    };


    @Override
    public SpettatoreDto toDto() {

        List<BigliettoDto> bigliettiDto = new ArrayList<>();
        if(biglietti != null){
            bigliettiDto = biglietti.stream().map(Biglietto::toDto).collect(Collectors.toList());
        }

        return SpettatoreDto.builder()
                .id(id.toString())
                .nome(nome)
                .biglietti(bigliettiDto)
                .nascita(Util.fromInstant(nascita))
                .build();
    }
}
