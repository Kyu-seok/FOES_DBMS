package com.kyu.springbackend.services.mobility;

import com.kyu.springbackend.model.Mobility;
import com.kyu.springbackend.repositories.MobilityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class MobilityServiceImplTest {

    MobilityServiceImpl service;

    @Mock
    MobilityRepository repository;

    @BeforeEach
    void setUp() {
        service = new MobilityServiceImpl(repository);
    }

    @Test
    void getAllMobility() {
        Mobility mobility1 = new Mobility();
        mobility1.setId("1");
        Mobility mobility2 = new Mobility();
        mobility2.setId("2");
        List<Mobility> mobilities = new LinkedList<>();
        mobilities.add(mobility1);
        mobilities.add(mobility2);

        when(repository.findAll()).thenReturn(mobilities);
        List<Mobility> returnedMobilities = service.findAll();

        assertEquals(mobilities, returnedMobilities);
        verify(repository, times(1)).findAll();
        verify(repository, never()).findById(anyString());

    }

    @Test
    void getMobilityByIdTest() {
        Mobility mobility = new Mobility();
        mobility.setId("1");
        Optional<Mobility> mobilityOptional = Optional.of(mobility);

        when(repository.findById(anyString())).thenReturn(mobilityOptional);
        Mobility mobilityReturned = service.findById("1");

        assertEquals(mobilityOptional.get(), mobilityReturned);
        verify(repository, times(1)).findById(anyString());
        verify(repository, never()).findAll();
    }

    @Test
    void saveMobility() {
        Mobility mobility = new Mobility();
        mobility.setId("1");

        when(repository.save(any())).thenReturn(mobility);
        Mobility savedMobility = service.save(mobility);

        assertEquals(mobility, savedMobility);
        verify(repository, times(1)).save(any());
    }

    @Test
    void updateExistingMobility() {
        Mobility mobility = new Mobility();
        mobility.setId("1");
        mobility.setProgram("Program 1");
        Optional<Mobility> mobilityOptional = Optional.of(mobility);

        Mobility newUpdateMobility = new Mobility();
        newUpdateMobility.setId("1");
        newUpdateMobility.setProgram("Program 1");

        when(repository.findById(anyString())).thenReturn(mobilityOptional);
        when(repository.save(any())).thenReturn(newUpdateMobility);
        Mobility returnedMobility = service.update(mobility.getId(), newUpdateMobility);

        assertEquals(newUpdateMobility, returnedMobility);
        verify(repository, times(1)).findById(anyString());
        verify(repository, times(1)).save(any());
    }

    @Test
    void updateEmptyMobility() {
        Mobility newUpdateMobility = new Mobility();
        newUpdateMobility.setId("1");
        newUpdateMobility.setProgram("Program 1");

        when(repository.findById(anyString())).thenReturn(Optional.empty());
        when(repository.save(any())).thenReturn(newUpdateMobility);
        Mobility returnedMobility = service.update(newUpdateMobility.getId(), newUpdateMobility);

        assertEquals(newUpdateMobility, returnedMobility);
        verify(repository, times(1)).findById(anyString());
        verify(repository, times(1)).save(any());
    }

    @Test
    void deleteById() {
        Mobility mobility = new Mobility();
        mobility.setId("1");
        service.deleteById(mobility.getId());

        verify(repository, times(1)).deleteById(anyString());
    }

}