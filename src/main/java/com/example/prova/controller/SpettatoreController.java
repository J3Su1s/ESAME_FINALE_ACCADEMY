package com.example.prova.controller;

import com.example.prova.data.dto.SpettatoreDto;
import com.example.prova.service.SpettatoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spettatore")
@ResponseStatus(HttpStatus.CREATED)
public class SpettatoreController {
    @Autowired
    SpettatoreService spettatoreService;

    @GetMapping("/v1")
    public List<SpettatoreDto> getAll(){
        return spettatoreService.getAll();
    }

    @GetMapping("/v1/{id}")
    public SpettatoreDto getById(@PathVariable("id") Long id) {
        return spettatoreService.get(id);
    }
    @DeleteMapping("/v1/{id}")
    public Boolean delete(@PathVariable("id") Long id) {
        return spettatoreService.delete(id);
    }

    @PutMapping
    public SpettatoreDto update(@RequestBody SpettatoreDto SpettatoreDto) {
        return spettatoreService.update(SpettatoreDto);
    }

    @PatchMapping("/v1")
    public SpettatoreDto patch(@RequestBody SpettatoreDto spettatoreDto) {
        return spettatoreService.update(spettatoreDto);
    }

    @PostMapping("/v1")
    public SpettatoreDto insert(@RequestBody SpettatoreDto spettatoreDto) {
        return spettatoreService.insert(spettatoreDto);
    }
    
}
