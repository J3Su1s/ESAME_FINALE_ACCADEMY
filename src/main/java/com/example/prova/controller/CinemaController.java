package com.example.prova.controller;

import com.example.prova.data.dto.CinemaDto;
import com.example.prova.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinema")
@ResponseStatus(HttpStatus.CREATED)
public class CinemaController {
    @Autowired
    CinemaService cinemaService;

    @GetMapping("/v1")
    public List<CinemaDto> getAll(){
        return cinemaService.getAll();
    }

    @GetMapping("/v1/{id}")
    public CinemaDto getById(@PathVariable("id") Long id) {
        return cinemaService.get(id);
    }
    @DeleteMapping("/v1/{id}")
    public Boolean delete(@PathVariable("id") Long id) {
        return cinemaService.delete(id);
    }

    @PutMapping
    public CinemaDto update(@RequestBody CinemaDto CinemaDto) {
        return cinemaService.update(CinemaDto);
    }

    @PatchMapping("/v1")
    public CinemaDto patch(@RequestBody CinemaDto CinemaDto) {
        return cinemaService.update(CinemaDto);
    }

    @PostMapping("/v1")
    public CinemaDto insert(@RequestBody CinemaDto CinemaDto) {
        return cinemaService.insert(CinemaDto);
    }
    
}
