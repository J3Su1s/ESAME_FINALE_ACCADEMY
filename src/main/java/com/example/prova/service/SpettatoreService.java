package com.example.prova.service;

import com.example.prova.data.dto.SpettatoreDto;


import java.util.List;

public interface SpettatoreService {

    public List<SpettatoreDto> getAll();
    public SpettatoreDto get(Long id);
    public SpettatoreDto insert(SpettatoreDto spettatoreDto);
    public SpettatoreDto update(SpettatoreDto spettatoreDto);
    public Boolean delete(Long id);
}
