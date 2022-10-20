package com.example.prova.data.model;

import com.example.prova.data.archetype.Model;
import com.example.prova.data.dto.SalaDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "sala")
@SQLDelete(sql = "UPDATE sala SET cancellato = true WHERE id=? ")
@Where(clause = "cancellato = false")
@Entity
@Transactional

public class Sala implements Model {

    // proprietà
    @Id
    @Column(name = "sala_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomeSala")
    private String nomeSala;

    @Column(name = "posti")
    private Long postiASedere;

    @Column(name = "postiPrenotati")
    private Long postiPrenotati;

    @Column(name="cancellato")
    @Builder.Default
    private Boolean cancellato = false;

    @OneToMany (mappedBy = "salaId", fetch = FetchType.EAGER)
    private Set<Biglietto> biglietti = new HashSet<>();

    @ManyToMany(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
            @JoinTable(name = "film_sala"
                    ,joinColumns = @JoinColumn(name = "s_id", referencedColumnName = "sala_id")
                    ,inverseJoinColumns = @JoinColumn(name = "p_id", referencedColumnName = "id"))
    private Set<Film> film = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "cinema_id", nullable = false)
    private Cinema cinema;

    public Sala(String salaId) {
        this.id = Long.valueOf(salaId);
    }

    @Override
    public SalaDto toDto() {
        return SalaDto.builder()
                .id(String.valueOf(id))
                .nomeSala(nomeSala)
                .postiASedere(String.valueOf(postiASedere))
                .postiPrenotati(String.valueOf(postiPrenotati))
                .cancellato(String.valueOf(cancellato))
                .cinemaId(cinema.getId().toString())
                .build();
    }
}
