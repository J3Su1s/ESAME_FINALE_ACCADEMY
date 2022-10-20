package com.example.prova.repository;

import com.example.prova.data.model.Spettatore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpettatoreRepository extends JpaRepository<Spettatore, Long> {

    public Spettatore findSpettatoreById(@Param("id") Long id);
    public List<Spettatore> findSpettatoreByNome(@Param("nome") String genere);
    //public List<Spettatore> findSpettatoreByBigliettoId(@Param("biglietto_id") String bigliettoId);
}
