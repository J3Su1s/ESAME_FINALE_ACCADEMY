package com.example.prova.ServiceImpl;

import com.example.prova.TestSupport.TestingSupport;
import com.example.prova.data.dto.CinemaDto;
import com.example.prova.data.model.Cinema;
import com.example.prova.repository.CinemaRepository;
import com.example.prova.service.CinemaService;
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
public class CinemaServiceImplTest {
    @Autowired
    private CinemaService cinemaService;

    @MockBean
    private CinemaRepository cinemaRepository;

    @Test
    @DisplayName("Compare Id")
    void compareId() {

        Long id = 1L;

        Cinema Cinema = TestingSupport.createCinema(id);

        Mockito.when(cinemaRepository.findById(id)).thenReturn(Optional.of(Cinema));

        CinemaDto CinemaDto = cinemaService.get(id);

        assertEquals(Cinema.getId().toString(), CinemaDto.getId());
    }

    @Test
    @DisplayName("Get all comparison")
    void compareList() {
        //arrange
        List<Cinema> mockedCinemasList = TestingSupport.createCinemaList();
        when(cinemaRepository.findAll()).thenReturn(mockedCinemasList);

        //act
        List<CinemaDto> CinemasList = cinemaService.getAll();

        //assert
        assertEquals(CinemasList.size(), mockedCinemasList.size());
        for(int i = 0; i < mockedCinemasList.size(); i++)
            assertEquals(mockedCinemasList.get(i).toDto(), CinemasList.get(i));
    }

    @Test
    @DisplayName("Insert Test")
    void insert_Test() {

        CinemaDto CinemaToSave = new CinemaDto();
        Cinema CinemaToReturn = TestingSupport.createCinema(1L);
        when(cinemaRepository.save(any())).thenReturn(CinemaToReturn);

        CinemaDto testedCinema = cinemaService.insert(CinemaToSave);

        assertEquals( "1", testedCinema.getId() );
    }

    @Test
    @DisplayName("Delete Test")
    void delete() {
        Long id = 1L;
        doNothing().when(cinemaRepository).deleteById(id);
        when(cinemaRepository.findById(id)).thenReturn(Optional.empty());

        assertTrue(cinemaService.delete(id));
        Mockito.verify(cinemaRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Put test")
    void put() {
        Long id = 1L;
        Cinema CinemaToSave = TestingSupport.createCinema(1L);
        Cinema CinemaToReturn = TestingSupport.createCinema(id);
        when(cinemaRepository.save(any())).thenReturn(CinemaToReturn);

        CinemaDto testedCinema = cinemaService.update(CinemaToSave.toDto());

        assertEquals(id.toString(), testedCinema.getId());
    }
}
