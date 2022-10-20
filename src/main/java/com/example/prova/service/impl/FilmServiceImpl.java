package com.example.prova.service.impl;

import com.example.prova.data.dto.FilmDto;
import com.example.prova.data.model.Film;
import com.example.prova.repository.FilmRepository;
import com.example.prova.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmRepository filmRepository;

    @Override
    public List<FilmDto> getAll() {
        return filmRepository.findAll().stream().map(Film::toDto).collect(Collectors.toList());
    }

    @Override
    public FilmDto get(Long id) {
        Optional<Film> film = filmRepository.findById(id);
        return film.isPresent() ? film.get().toDto() : null;
    }

    @Override
    public FilmDto insert(FilmDto filmDto) {
        if(filmDto.getId() != null)
            throw new RuntimeException();
        return filmRepository.save(filmDto.toModel()).toDto();
    }

    @Override
    public FilmDto update(FilmDto filmDto) {
        if(filmDto.getId() == null)
            throw new RuntimeException();
        return filmRepository.save(filmDto.toModel()).toDto();
    }

    @Override
    public Boolean delete(Long id) {
        filmRepository.deleteById(id);
        return filmRepository.findById(id).isEmpty();
    }

    @Override
    public String calcolaTitoloConMaggiorBigliettiVenduti() {
        return filmRepository.calcolaTitoloConMaggiorBigliettiVenduti();
    }

    @Override
    public String calcolaTitoloConMinorBigliettiVenduti() {
        return filmRepository.calcolaTitoloConMinorBigliettiVenduti();
    }

    @Override
    public List<Double> calcolaPercentualeAffluenzaPerGenere() {
        return filmRepository.calcolaPercentualeAffluenzaPerGenere();
    }
}
