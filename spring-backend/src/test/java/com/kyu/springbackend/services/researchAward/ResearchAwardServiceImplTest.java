package com.kyu.springbackend.services.researchAward;

import com.kyu.springbackend.model.ResearchAward;
import com.kyu.springbackend.repositories.ResearchAwardRepository;
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
class ResearchAwardServiceImplTest {

    ResearchAwardServiceImpl service;

    @Mock
    ResearchAwardRepository repository;
    @BeforeEach
    void setUp() {
        service = new ResearchAwardServiceImpl(repository);
    }

    @Test
    void getAllResearchAward() {
        ResearchAward researchAward1 = new ResearchAward();
        researchAward1.setId("1");
        ResearchAward researchAward2 = new ResearchAward();
        researchAward2.setId("2");
        List<ResearchAward> researchAwards = new LinkedList<>();
        researchAwards.add(researchAward1);
        researchAwards.add(researchAward2);

        when(repository.findAll()).thenReturn(researchAwards);
        List<ResearchAward> returnedResearchAwards = service.findAll();

        assertEquals(researchAwards, returnedResearchAwards);
        verify(repository, times(1)).findAll();
        verify(repository, never()).findById(anyString());

    }

    @Test
    void getResearchAwardByIdTest() {
        ResearchAward researchAward = new ResearchAward();
        researchAward.setId("1");
        Optional<ResearchAward> researchAwardOptional = Optional.of(researchAward);

        when(repository.findById(anyString())).thenReturn(researchAwardOptional);
        ResearchAward researchAwardReturned = service.findById("1");

        assertEquals(researchAwardOptional.get(), researchAwardReturned);
        verify(repository, times(1)).findById(anyString());
        verify(repository, never()).findAll();
    }

    @Test
    void saveResearchAward() {
        ResearchAward researchAward = new ResearchAward();
        researchAward.setId("1");

        when(repository.save(any())).thenReturn(researchAward);
        ResearchAward savedResearchAward = service.save(researchAward);

        assertEquals(researchAward, savedResearchAward);
        verify(repository, times(1)).save(any());
    }

    @Test
    void updateExistingResearchAward() {
        ResearchAward researchAward = new ResearchAward();
        researchAward.setId("1");
        researchAward.setProjectTitle("Project 1");
        Optional<ResearchAward> researchAwardOptional = Optional.of(researchAward);

        ResearchAward newUpdateResearchAward = new ResearchAward();
        newUpdateResearchAward.setId("1");
        newUpdateResearchAward.setProjectTitle("Project 1");

        when(repository.findById(anyString())).thenReturn(researchAwardOptional);
        when(repository.save(any())).thenReturn(newUpdateResearchAward);
        ResearchAward returnedResearchAward = service.update(researchAward.getId(), newUpdateResearchAward);

        assertEquals(newUpdateResearchAward, returnedResearchAward);
        verify(repository, times(1)).findById(anyString());
        verify(repository, times(1)).save(any());
    }

    @Test
    void updateEmptyResearchAward() {
        ResearchAward newUpdateResearchAward = new ResearchAward();
        newUpdateResearchAward.setId("1");
        newUpdateResearchAward.setProjectTitle("Project 1");

        when(repository.findById(anyString())).thenReturn(Optional.empty());
        when(repository.save(any())).thenReturn(newUpdateResearchAward);
        ResearchAward returnedResearchAward = service.update(newUpdateResearchAward.getId(), newUpdateResearchAward);

        assertEquals(newUpdateResearchAward, returnedResearchAward);
        verify(repository, times(1)).findById(anyString());
        verify(repository, times(1)).save(any());
    }

    @Test
    void deleteById() {
        ResearchAward researchAward = new ResearchAward();
        researchAward.setId("1");
        service.deleteById(researchAward.getId());

        verify(repository, times(1)).deleteById(anyString());
    }
}