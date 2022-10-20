package com.example.prova.service;

import com.example.prova.data.dto.BigliettoDto;


import java.util.List;

public interface BigliettoService {

    public List<BigliettoDto> getAll();
    public BigliettoDto get(Long id);
    public BigliettoDto insert(BigliettoDto bigliettoDto);
    public BigliettoDto update(BigliettoDto bigliettoDto);
    public Boolean delete(Long id);

    public Long salaPostiPrenotati(Long id);
    public Long salaPostiDisponibili(Long id);
}
