package com.example.prova.repository;

import com.example.prova.data.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {

    public Film findFilmById(@Param("id") Long id);
    public List<Film> findFilmByTitolo(@Param("titolo") String titolo);
    public List<Film> findFilmByGenere(@Param("genere") String genere);
    public List<Film> findFilmByCasaProduttrice(@Param("casaProduttrice") Long casaProduttrice);
    public List<Film> findFilmByRegista(@Param("regista") String regista);
    public List<Film> findFilmByEtàMinima(@Param("etàMinima") Long etàMinima);


    @Query(value = "select proiezione.genere, count(*) * 100.0 / sum(count(*)) over() " +
            "from proiezione " +
            "group by proiezione.genere ", nativeQuery = true)
    List<Double> calcolaPercentualeAffluenzaPerGenere();

    @Query(value = "select proiezione.titolo from proiezione " +
            "join biglietto b on proiezione.id = b.proiezione_id " +
            "order by titolo asc " +
            "limit 1 ", nativeQuery = true)
    String calcolaTitoloConMaggiorBigliettiVenduti();

    @Query(value = "select proiezione.titolo from proiezione " +
            "join biglietto b on proiezione.id = b.proiezione_id " +
            "order by titolo desc " +
            "limit 1 ", nativeQuery = true)
    String calcolaTitoloConMinorBigliettiVenduti();
}
