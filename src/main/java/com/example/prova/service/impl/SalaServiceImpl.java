package com.example.prova.service.impl;

import com.example.prova.data.dto.SalaDto;
import com.example.prova.data.model.Sala;
import com.example.prova.repository.film.SalaRepository;
import com.example.prova.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SalaServiceImpl implements SalaService {

    @Autowired
    private SalaRepository salaRepository;


    @Override
    public List<SalaDto> getAll() {
        return salaRepository.findAll().stream().map(Sala::toDto).collect(Collectors.toList());
    }

    @Override
    public SalaDto get(Long id) {
        Optional<Sala> Sala = Optional.ofNullable(salaRepository.findSalaById(id));
        return Sala.isPresent() ? Sala.get().toDto() : null;
    }

    @Override
    public SalaDto insert(SalaDto salaDto) {
        if(salaDto.getId() != null)
            throw new RuntimeException();
        return salaRepository.save(salaDto.toModel()).toDto();
    }

    @Override
    public SalaDto update(SalaDto SalaDto) {
        if(SalaDto.getId() == null)
            throw new RuntimeException();
        return salaRepository.save(SalaDto.toModel()).toDto();
    }

    @Override
    public Boolean delete(Long id) {
        salaRepository.deleteById(id);
        return salaRepository.findById(id).isEmpty();
    }

    @Override
    public Double calcolaIncassoSala(Long id) {
        return salaRepository.calcolaIncassoSala(id);
    }

    @Override
    public void SvuotaSala(Long id) {
        salaRepository.svuotaSala(id);
    }

    @Override
    public void aggiungiUnPostoPrenotato(Long id) {
        salaRepository.aggiungiUnPostoPrenotato(id);
    }
}
