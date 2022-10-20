package com.example.prova.data.model;

import com.example.prova.data.archetype.Model;
import com.example.prova.data.dto.FilmDto;
import com.example.prova.data.enums.Genere;
import com.example.prova.utils.Util;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "proiezione")
@SQLDelete(sql = "UPDATE proiezione p SET p.cancellato = true WHERE p.id=?")
@Where(clause = "cancellato=false")
public class Film implements Model {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titolo")
    private String titolo;

    @Column(name = "regista")
    private String regista;

    @Column(name = "produzione")
    private String casaProduttrice;

    @Column(name = "genere")
    private Genere genere;

    @Column(name = "durata")
    private Long durata;

    @Column(name = "età")
    private Long etàMinima;

    //Inserisco proprietà salaID nei film, così quando viene venduto il biglietto posso controllare se la sala
    //è piena e lanciare un eccezione posti esauriti.

    @Column(name = "salaId")
    private Long salaId;

    @Column(name="cancellato")
    @Builder.Default
    private Boolean cancellato = false;

    @OneToOne(mappedBy = "film")
    private Biglietto biglietto;

    // relazione con film
    @ManyToMany(mappedBy = "film", fetch = FetchType.EAGER)
    private Set<Sala> sale = new HashSet<>();

    public Film(String filmId) {
        this.id = Util.toLong(filmId);
    }

    @Override
    public FilmDto toDto() {
        return FilmDto.builder()
                .id(String.valueOf(id))
                .titolo(titolo)
                .regista(regista)
                .genere(Util.getGenere(genere))
                .casaProduttrice(casaProduttrice)
                .durata(String.valueOf(durata))
                .etàMinima(String.valueOf(etàMinima))
                .salaId(String.valueOf(salaId))
                .build();
    }
}
