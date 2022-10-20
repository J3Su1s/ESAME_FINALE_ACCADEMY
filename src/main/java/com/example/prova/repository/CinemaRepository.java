package com.example.prova.repository;

import com.example.prova.data.model.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {

    public Cinema findCinemaById(@Param("id") Long id);

    // Funzione che calcola l'incasso delle sale
}
