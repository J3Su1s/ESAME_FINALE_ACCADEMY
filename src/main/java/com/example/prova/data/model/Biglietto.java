package com.example.prova.data.model;

import com.example.prova.data.archetype.Model;
import com.example.prova.data.dto.BigliettoDto;
import com.example.prova.utils.Util;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.transaction.Transactional;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity

@Table(name = "biglietto")
@SQLDelete(sql = "UPDATE biglietto b SET b.cancellato = true WHERE b.id=?")
@Where(clause = "cancellato=false")
@Transactional
public class Biglietto implements Model {

    @Id
    @Column(name = "id", insertable = false, updatable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="postoAssegnato")
    private String postoAssegnato;

    @Column(name = "prezzo")
    private Double prezzo;

    @Column(name="cancellato")
    @Builder.Default
    private Boolean cancellato = false;

    @Column(name="intestatarioId")
    private Long intestatarioId;

    @Column(name = "filmPrenotatoId")
    private Long filmId;

    @Column(name = "sala_id", insertable = false, updatable = false)
    private Long salaId;

    // relazione con film
    @OneToOne
    @JoinColumn(name = "filmId")
    private Film film;

    // nome sala

    @ManyToOne
    @JoinColumn(name = "spettatore_id", nullable = false)
    private Spettatore acquirente;

    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = false)
    private Sala sala;


    public Biglietto(String bigliettoId) {
        if(bigliettoId != null) {
            this.id = Util.toLong(bigliettoId);
        }
    }

    @Override
    public BigliettoDto toDto() {

        return BigliettoDto.builder()
                .id(id.toString())
                .cancellato(cancellato.toString())
                .postoAssegnato(postoAssegnato)
                .prezzo(prezzo.toString())
                .intestatarioId(String.valueOf(intestatarioId))
                .filmId(String.valueOf(filmId))
                .build();
    }
}
