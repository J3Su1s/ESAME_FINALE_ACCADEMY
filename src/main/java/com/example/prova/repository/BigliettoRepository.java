package com.example.prova.repository;

import com.example.prova.data.model.Biglietto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BigliettoRepository extends JpaRepository<Biglietto, Long> {

    Biglietto findBigliettoById(@Param("id") Long id);
    List<Biglietto> findBigliettoByPostoAssegnato(@Param("postoAssegnato") String postoAssegnato);

    @Query(value = "select posti_prenotati from spring_lasttest.sala s " +
                    "where s.sala_id = :id", nativeQuery = true)
    Long findPostiPrenotatiSalaById(@Param("id") Long id);

    @Query(value = "select posti from spring_lasttest.sala s " +
            "where s.sala_id = :id", nativeQuery = true)
    Long findPostiInSalaById(@Param("id") Long id);



}
