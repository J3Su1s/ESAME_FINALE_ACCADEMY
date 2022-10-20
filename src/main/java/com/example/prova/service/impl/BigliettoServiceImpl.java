package com.example.prova.service.impl;

import com.example.prova.data.dto.BigliettoDto;
import com.example.prova.data.model.Biglietto;
import com.example.prova.repository.BigliettoRepository;
import com.example.prova.service.BigliettoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BigliettoServiceImpl implements BigliettoService {

    @Autowired
    private BigliettoRepository bigliettoRepository;

    @Override
    public List<BigliettoDto> getAll() {
        return bigliettoRepository.findAll().stream().map(Biglietto::toDto).collect(Collectors.toList());
    }

    @Override
    public BigliettoDto get(Long id) {
        Optional<Biglietto> biglietto = bigliettoRepository.findById(id);
        return biglietto.map(Biglietto::toDto).orElse(null);
    }

    @Override
    public BigliettoDto insert(BigliettoDto bigliettoDto) {
        if(bigliettoDto.getId() != null)
            throw new RuntimeException();
        return bigliettoRepository.save(bigliettoDto.toModel()).toDto();
    }

    @Override
    public BigliettoDto update(BigliettoDto bigliettoDto) {
        if(bigliettoDto.getId() == null)
            throw new RuntimeException();
        return bigliettoRepository.save(bigliettoDto.toModel()).toDto();
    }

    @Override
    public Boolean delete(Long id) {
        bigliettoRepository.deleteById(id);
        return bigliettoRepository.findById(id).isEmpty();
    }
    @Override
    public Boolean deleteAll() {
        bigliettoRepository.deleteAll();
        return Boolean.TRUE;
    }

}
