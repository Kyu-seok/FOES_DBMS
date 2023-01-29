package com.kyu.springbackend.services.ktpusr;

import com.kyu.springbackend.model.KtpUsr;
import com.kyu.springbackend.repositories.KtpUsrRepository;
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
class KtpUsrServiceImplTest {

    KtpUsrService service;

    @Mock
    KtpUsrRepository repository;

    @BeforeEach
    void setUp() {
        service = new KtpUsrServiceImpl(repository);
    }

    @Test
    void getAllKtpUsr() {
        KtpUsr ktpUsr1 = new KtpUsr();
        ktpUsr1.setId("1");
        KtpUsr ktpUsr2 = new KtpUsr();
        ktpUsr2.setId("2");
        List<KtpUsr> ktpUsrs = new LinkedList<>();
        ktpUsrs.add(ktpUsr1);
        ktpUsrs.add(ktpUsr2);

        when(repository.findAll()).thenReturn(ktpUsrs);
        List<KtpUsr> returnedKtpUsrs = service.findAll();

        assertEquals(ktpUsrs, returnedKtpUsrs);
        verify(repository, times(1)).findAll();
        verify(repository, never()).findById(anyString());

    }

    @Test
    void getKtpUsrByIdTest() {
        KtpUsr ktpUsr = new KtpUsr();
        ktpUsr.setId("1");
        Optional<KtpUsr> ktpUsrOptional = Optional.of(ktpUsr);

        when(repository.findById(anyString())).thenReturn(ktpUsrOptional);
        KtpUsr ktpUsrReturned = service.findById("1");

        assertEquals(ktpUsrOptional.get(), ktpUsrReturned);
        verify(repository, times(1)).findById(anyString());
        verify(repository, never()).findAll();
    }

    @Test
    void saveKtpUsr() {
        KtpUsr ktpUsr = new KtpUsr();
        ktpUsr.setId("1");

        when(repository.save(any())).thenReturn(ktpUsr);
        KtpUsr savedKtpUsr = service.save(ktpUsr);

        assertEquals(ktpUsr, savedKtpUsr);
        verify(repository, times(1)).save(any());
    }

    @Test
    void updateExistingKtpUsr() {
        KtpUsr ktpUsr = new KtpUsr();
        ktpUsr.setId("1");
        ktpUsr.setProgramName("Program 1");
        Optional<KtpUsr> ktpUsrOptional = Optional.of(ktpUsr);

        KtpUsr newUpdateKtpUsr = new KtpUsr();
        newUpdateKtpUsr.setId("1");
        newUpdateKtpUsr.setProgramName("Program 1");

        when(repository.findById(anyString())).thenReturn(ktpUsrOptional);
        when(repository.save(any())).thenReturn(newUpdateKtpUsr);
        KtpUsr returnedKtpUsr = service.update(ktpUsr.getId(), newUpdateKtpUsr);

        assertEquals(newUpdateKtpUsr, returnedKtpUsr);
        verify(repository, times(1)).findById(anyString());
        verify(repository, times(1)).save(any());
    }

    @Test
    void updateEmptyKtpUsr() {
        KtpUsr newUpdateKtpUsr = new KtpUsr();
        newUpdateKtpUsr.setId("1");
        newUpdateKtpUsr.setProgramName("Program 1");

        when(repository.findById(anyString())).thenReturn(Optional.empty());
        when(repository.save(any())).thenReturn(newUpdateKtpUsr);
        KtpUsr returnedKtpUsr = service.update(newUpdateKtpUsr.getId(), newUpdateKtpUsr);

        assertEquals(newUpdateKtpUsr, returnedKtpUsr);
        verify(repository, times(1)).findById(anyString());
        verify(repository, times(1)).save(any());
    }

    @Test
    void deleteById() {
        KtpUsr ktpUsr = new KtpUsr();
        ktpUsr.setId("1");
        service.deleteById(ktpUsr.getId());

        verify(repository, times(1)).deleteById(anyString());
    }
}