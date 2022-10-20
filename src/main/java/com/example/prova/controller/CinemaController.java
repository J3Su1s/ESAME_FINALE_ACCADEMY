package com.example.prova.controller;

import com.example.prova.data.dto.BigliettoDto;
import com.example.prova.data.dto.CinemaDto;
import com.example.prova.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cinema")

public class CinemaController {
    @Autowired
    CinemaService cinemaService;

    @GetMapping
    public ResponseEntity<List<CinemaDto>> getAll(){
        List<CinemaDto> cinemaToReturn = new ArrayList<>(cinemaService.getAll());
        return new ResponseEntity<>(cinemaToReturn, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CinemaDto> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(cinemaService.get(id), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(cinemaService.delete(id), HttpStatus.ACCEPTED);
    }

    @PutMapping
    public ResponseEntity<CinemaDto> update(@RequestBody CinemaDto CinemaDto) {
        return new ResponseEntity<>(cinemaService.update(CinemaDto), HttpStatus.OK);
    }

    @PatchMapping
    public  ResponseEntity<CinemaDto> patch(@RequestBody CinemaDto CinemaDto) {
        return new ResponseEntity<>(cinemaService.update(CinemaDto), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CinemaDto>  insert(@RequestBody CinemaDto CinemaDto) {
        return new ResponseEntity<>(cinemaService.insert(CinemaDto), HttpStatus.ACCEPTED);
    }
    
}
