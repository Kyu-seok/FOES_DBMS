package com.kyu.springbackend.services.moumoa;

import com.kyu.springbackend.model.MouMoa;
import com.kyu.springbackend.repositories.MouMoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MouMoaServiceImplTest {

    MouMoaServiceImpl service;

    @Mock
    MouMoaRepository repository;

    @BeforeEach
    void setUp() {
        service = new MouMoaServiceImpl(repository);
    }

    @Test
    void getAllMouMoa() {
        MouMoa mouMoa1 = new MouMoa();
        mouMoa1.setId("1");
        MouMoa mouMoa2 = new MouMoa();
        mouMoa2.setId("2");
        List<MouMoa> mouMoas = new LinkedList<>();
        mouMoas.add(mouMoa1);
        mouMoas.add(mouMoa2);

        when(repository.findAll()).thenReturn(mouMoas);
        List<MouMoa> returnedMouMoas = service.findAll();

        assertEquals(mouMoas, returnedMouMoas);
        verify(repository, times(1)).findAll();
        verify(repository, never()).findById(anyString());

    }

    @Test
    void getMouMoaByIdTest() {
        MouMoa mouMoa = new MouMoa();
        mouMoa.setId("1");
        Optional<MouMoa> mouMoaOptional = Optional.of(mouMoa);

        when(repository.findById(anyString())).thenReturn(mouMoaOptional);
        MouMoa mouMoaReturned = service.findById("1");

        assertEquals(mouMoaOptional.get(), mouMoaReturned);
        verify(repository, times(1)).findById(anyString());
        verify(repository, never()).findAll();
    }

    @Test
    void saveMouMoa() {
        MouMoa mouMoa = new MouMoa();
        mouMoa.setId("1");

        when(repository.save(any())).thenReturn(mouMoa);
        MouMoa savedMouMoa = service.save(mouMoa);

        assertEquals(mouMoa, savedMouMoa);
        verify(repository, times(1)).save(any());
    }

    @Test
    void updateExistingMouMoa() {
        MouMoa mouMoa = new MouMoa();
        mouMoa.setId("1");
        mouMoa.setTypeOfAgreement("Agreement 1");
        Optional<MouMoa> mouMoaOptional = Optional.of(mouMoa);

        MouMoa newUpdateMouMoa = new MouMoa();
        newUpdateMouMoa.setId("1");
        newUpdateMouMoa.setTypeOfAgreement("Agreement 1");

        when(repository.findById(anyString())).thenReturn(mouMoaOptional);
        when(repository.save(any())).thenReturn(newUpdateMouMoa);
        MouMoa returnedMouMoa = service.update(mouMoa.getId(), newUpdateMouMoa);

        assertEquals(newUpdateMouMoa, returnedMouMoa);
        verify(repository, times(1)).findById(anyString());
        verify(repository, times(1)).save(any());
    }

    @Test
    void updateEmptyMouMoa() {
        MouMoa newUpdateMouMoa = new MouMoa();
        newUpdateMouMoa.setId("1");
        newUpdateMouMoa.setTypeOfAgreement("Agreement 1");

        when(repository.findById(anyString())).thenReturn(Optional.empty());
        when(repository.save(any())).thenReturn(newUpdateMouMoa);
        MouMoa returnedMouMoa = service.update(newUpdateMouMoa.getId(), newUpdateMouMoa);

        assertEquals(newUpdateMouMoa, returnedMouMoa);
        verify(repository, times(1)).findById(anyString());
        verify(repository, times(1)).save(any());
    }

    @Test
    void deleteById() {
        MouMoa mouMoa = new MouMoa();
        mouMoa.setId("1");
        service.deleteById(mouMoa.getId());

        verify(repository, times(1)).deleteById(anyString());
    }

}