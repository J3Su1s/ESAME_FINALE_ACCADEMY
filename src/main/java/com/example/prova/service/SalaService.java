package com.example.prova.service;

import com.example.prova.data.dto.SalaDto;

import java.util.List;

public interface SalaService {

    public List<SalaDto> getAll();
    public SalaDto get(Long id);
    public SalaDto insert(SalaDto salaDto);
    public SalaDto update(SalaDto salaDto);
    public Boolean delete(Long id);

    Double calcolaIncassoSala(Long id);
    void SvuotaSala(Long id);
    void aggiungiUnPostoPrenotato(Long id);

    public Long salaPostiPrenotati(Long id);
    public Long salaPostiDisponibili(Long id);
}
