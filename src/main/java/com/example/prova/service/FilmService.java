package com.example.prova.service;

import com.example.prova.data.dto.FilmDto;

import java.util.List;

public interface FilmService {

    public List<FilmDto> getAll();
    public FilmDto get(Long id);
    public FilmDto insert(FilmDto filmDto);
    public FilmDto update(FilmDto filmDto);
    public Boolean delete(Long id);

    String calcolaTitoloConMaggiorBigliettiVenduti();
    String calcolaTitoloConMinorBigliettiVenduti();

    List<Double> calcolaPercentualeAffluenzaPerGenere();

}
