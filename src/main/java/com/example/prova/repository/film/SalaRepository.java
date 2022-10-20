package com.example.prova.repository.film;


import com.example.prova.data.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface SalaRepository extends JpaRepository<Sala, Long> {

    @Transactional
    Sala findSalaById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = " UPDATE sala \n" +
            "    SET posti_prenotati = sala.posti_prenotati + 1 \n" +
            "    WHERE Sala.sala_id = :id",
            nativeQuery = true)
    void aggiungiUnPostoPrenotato(@Param("id") Long id);


    @Modifying
    @Transactional
    @Query(value = "UPDATE Sala " +
            "SET posti_prenotati = '0' " +
            "WHERE sala_id = :id" , nativeQuery = true)
    void svuotaSala(@Param("id") Long id);

    @Query(value = "SELECT SUM(prezzo) AS IncassoTotaleSala FROM biglietto b " +
            "join sala s on b.sala_id = s.sala_id " +
            "where s.sala_id = :id", nativeQuery = true)
    Double calcolaIncassoSala(@Param("id") Long id);

}
