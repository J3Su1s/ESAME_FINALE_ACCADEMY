package com.example.prova.ServiceImpl;

import com.example.prova.TestSupport.TestingSupport;
import com.example.prova.data.dto.SalaDto;
import com.example.prova.data.model.Sala;
import com.example.prova.repository.film.SalaRepository;
import com.example.prova.service.SalaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SalaServiceImplTest {

    @Autowired
    private SalaService salaService;

    @MockBean
    private SalaRepository salaRepository;

    @Test
    @DisplayName("Compare Id")
    void compareId() {

        Long id = 1L;

        Sala sala = TestingSupport.createSala(id);

        Mockito.when(salaRepository.findById(id)).thenReturn(Optional.of(sala));

        SalaDto salaDto = salaService.get(id);

        assertEquals(sala.getId().toString(), salaDto.getId());
    }

    @Test
    @DisplayName("List Comparison")
    void compareList() {
        //arrange
        List<Sala> mockedSalasList = TestingSupport.createSalaList();
        when(salaRepository.findAll()).thenReturn(mockedSalasList);

        //act
        List<SalaDto> SalasList = salaService.getAll();

        //assert
        assertEquals(SalasList.size(), mockedSalasList.size());
        for(int i = 0; i < mockedSalasList.size(); i++)
            assertEquals(mockedSalasList.get(i).toDto(), SalasList.get(i));
    }

    @Test
    @DisplayName("Save Sala Test")
    void saveTest() {

        SalaDto salaToSave = new SalaDto();
        Sala salaToReturn = TestingSupport.createSala(1L);
        when(salaRepository.save(any())).thenReturn(salaToReturn);

        SalaDto testedSala = salaService.insert(salaToSave);

        assertEquals( "1", testedSala.getId() );
    }

    @Test
    @DisplayName("Delete Sala Test")
    void delete() {
        Long id = 1L;
        doNothing().when(salaRepository).deleteById(id);
        when(salaRepository.findById(id)).thenReturn(Optional.empty());

        assertTrue(salaService.delete(id));
        Mockito.verify(salaRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Put test")
    void put() {
        Long id = 1L;
        Sala salaToSave = TestingSupport.createSala(1L);
        Sala salaToReturn = TestingSupport.createSala(id);
        when(salaRepository.save(any())).thenReturn(salaToReturn);

        SalaDto testedSala = salaService.update(salaToSave.toDto());

        assertEquals(id.toString(), testedSala.getId());
    }
}
