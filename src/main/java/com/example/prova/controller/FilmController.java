package com.example.prova.controller;

import com.example.prova.data.dto.FilmDto;
import com.example.prova.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/film")
@ResponseStatus(HttpStatus.CREATED)
public class FilmController {
    @Autowired
    FilmService filmService;

    @GetMapping("/v1")
    public List<FilmDto> getAll(){
        return filmService.getAll();
    }

    @GetMapping("/v1/{id}")
    public FilmDto getById(@PathVariable("id") Long id) {
        return filmService.get(id);
    }

    @GetMapping("/v1/calcolaPercentualeAffluenzaPerGenere")
    public List<Double> calcolaPercentualeAffluenzaPerGenere(){
        return filmService.calcolaPercentualeAffluenzaPerGenere();
    };

    @GetMapping("/v1/calcolaTitoloConMaggiorBigliettiVenduti")
    public String calcolaTitoloConMaggiorBigliettiVenduti(){
        return filmService.calcolaTitoloConMaggiorBigliettiVenduti();
    }

    @GetMapping("/v1/calcolaTitoloConMinorBigliettiVenduti")
    public String calcolaTitoloConMinorBigliettiVenduti(){
        return filmService.calcolaTitoloConMinorBigliettiVenduti();
    }

    @DeleteMapping("/v1/{id}")
    public Boolean delete(@PathVariable("id") Long id) {
        return filmService.delete(id);
    }

    @PutMapping
    public FilmDto update(@RequestBody FilmDto filmDto) {
        return filmService.update(filmDto);
    }

    @PatchMapping("/v1")
    public FilmDto patch(@RequestBody FilmDto filmDto) {
        return filmService.update(filmDto);
    }

    @PostMapping("/v1")
    public FilmDto insert(@RequestBody FilmDto filmDto) {
        return filmService.insert(filmDto);
    }
}
