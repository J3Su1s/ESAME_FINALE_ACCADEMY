package com.example.prova.controller;

import com.example.prova.data.dto.BigliettoDto;
import com.example.prova.data.model.Biglietto;
import com.example.prova.data.model.Film;
import com.example.prova.data.model.Spettatore;
import com.example.prova.exception.FilmVietatoAiMinori;
import com.example.prova.exception.SalaAlCompleto;
import com.example.prova.repository.FilmRepository;
import com.example.prova.repository.SpettatoreRepository;
import com.example.prova.repository.film.SalaRepository;
import com.example.prova.service.BigliettoService;
import com.example.prova.service.FilmService;
import com.example.prova.service.SalaService;
import com.example.prova.service.SpettatoreService;
import com.example.prova.utils.AgeCalculator;
import com.example.prova.utils.TicketValidator;
import com.example.prova.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/biglietto")
public class BigliettoController {

    @Autowired
    BigliettoService bigliettoService;

    @Autowired
    SpettatoreService spettatoreService;

    @Autowired
    FilmService filmService;

    @Autowired
    SalaService salaService;


    @GetMapping
    public ResponseEntity<List<BigliettoDto>> getAll() {
        List<BigliettoDto> bigliettoToReturn = new ArrayList<>(bigliettoService.getAll());
        return new ResponseEntity<>(bigliettoToReturn, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BigliettoDto> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(bigliettoService.get(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(bigliettoService.delete(id), HttpStatus.OK);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Boolean> deleteAll() {
        return new ResponseEntity<>(bigliettoService.deleteAll(), HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<BigliettoDto> update(@RequestBody BigliettoDto bigliettoDto) {
        return new ResponseEntity<>(bigliettoService.update(bigliettoDto), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<BigliettoDto> patch(@RequestBody BigliettoDto bigliettoDto) {
        return new ResponseEntity<>(bigliettoService.update(bigliettoDto), HttpStatus.OK);
    }

    @PostMapping(path = "/insert", consumes = "application/json", produces = "application/json")
    public ResponseEntity<BigliettoDto> insert(@RequestBody BigliettoDto bigliettoDto) {
        Spettatore acquirente = spettatoreService.get(Util.toLong(bigliettoDto.getIntestatarioId())).toModel();
        Film film = filmService.get(Util.toLong(bigliettoDto.getFilmId())).toModel();
        BigliettoDto bigliettoValido = TicketValidator.validaBiglietto(bigliettoDto, acquirente, film);

        Long salaId = Util.toLong(bigliettoValido.getSalaId());
        Long postiPrenotati = salaService.salaPostiPrenotati(salaId);
        Long postiASedere = salaService.salaPostiDisponibili(salaId);

        if(postiPrenotati < postiASedere)
        {
            salaService.aggiungiUnPostoPrenotato(salaId);
            return new ResponseEntity<>(bigliettoService.insert(bigliettoValido), HttpStatus.OK);
        }
        else {
            throw  new SalaAlCompleto("Spiacenti, i posti sono esauriti per questa proiezione");
        }
    }
}
