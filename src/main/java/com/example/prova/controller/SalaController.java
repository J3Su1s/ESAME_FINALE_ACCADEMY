package com.example.prova.controller;

import com.example.prova.data.dto.SalaDto;
import com.example.prova.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sala")
@ResponseStatus(HttpStatus.CREATED)
public class SalaController {
    @Autowired
    SalaService salaService;

    @GetMapping
    public List<SalaDto> getAll(){
        return salaService.getAll();
    }

    @GetMapping("/{id}")
    public SalaDto getById(@PathVariable("id") Long id) {
        return salaService.get(id);
    }

    @GetMapping("/{id}/calcolaIncassoSala")
    public Double calcolaIncassoSala(@PathVariable("id") Long id){
        return salaService.calcolaIncassoSala(id);
    }

    @PutMapping("/{id}/svuotaSala")
    public void svuotaSala(@PathVariable("id") Long id) {salaService.SvuotaSala(id);}

    @PutMapping("/{id}/aumentaDiUnoIPostiPrenotatiInSala")
    public void aumentaDiUnoIPostiPrenotatiInSala(@PathVariable("id") Long id) {salaService.SvuotaSala(id);}

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable("id") Long id) {
        return salaService.delete(id);
    }

    @PutMapping
    public SalaDto update(@RequestBody SalaDto salaDto) {
        return salaService.update(salaDto);
    }

    @PatchMapping
    public SalaDto patch(@RequestBody SalaDto salaDto) {
        return salaService.update(salaDto);
    }

    @PostMapping(path = "/insert", consumes = "application/json", produces = "application/json")
    public SalaDto insert(@RequestBody SalaDto salaDto) {
        return salaService.insert(salaDto);
    }
    
}
