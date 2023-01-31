package com.kyu.springbackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyu.springbackend.config.SecurityConfiguration;
import com.kyu.springbackend.model.MouMoa;
import com.kyu.springbackend.services.moumoa.MouMoaService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WithMockUser
@WebMvcTest(value = MouMoaController.class, excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfiguration.class)})
@ExtendWith(MockitoExtension.class)
@EnableWebMvc
class MouMoaControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    MouMoaService service;

    @Test
    void getAllMouMoaAPI() throws Exception {

        MouMoa mouMoa1 = new MouMoa();
        mouMoa1.setId("1");
        MouMoa mouMoa2 = new MouMoa();
        mouMoa2.setId("2");
        List<MouMoa> mouMoas = Arrays.asList(mouMoa1, mouMoa2);

        when(service.findAll()).thenReturn(mouMoas);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/mou-moas")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id", Matchers.equalTo("1")));

        verify(service).findAll();

    }

    @Test
    void getMouMoaByIdAPI() throws Exception {
        MouMoa mouMoa = new MouMoa();
        mouMoa.setId("1");
        mouMoa.setTypeOfAgreement("Agreement 1");

        when(service.findById(anyString())).thenReturn(mouMoa);

        mockMvc.perform(get("/api/mou-moas/1"))
                // .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.typeOfAgreement", Matchers.equalTo("Agreement 1")));

        verify(service).findById(anyString());
    }

    @Test
    void postMouMoaAPI() throws Exception {
        MouMoa mouMoa = new MouMoa();
        mouMoa.setTypeOfAgreement("Agreement 1");

        when(service.save(any())).thenReturn(mouMoa);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/mou-moas")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mouMoa))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.typeOfAgreement", Matchers.equalTo("Agreement 1")));

        verify(service).save(any());
    }

    @Test
    void putMouMoaAPI() throws Exception {

        MouMoa mouMoa = new MouMoa();
        mouMoa.setId("1");
        mouMoa.setTypeOfAgreement("Agreement 1");

        when(service.update(anyString(), any())).thenReturn(mouMoa);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/mou-moas/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mouMoa))
                        .with(csrf()))
                .andExpect(status().isOk());

        verify(service).update(anyString(), any());
    }

    @Test
    void DeleteMouMoaAPI() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/mou-moas/1")
                        .with(csrf()))
                .andExpect(status().isOk());

        verify(service).deleteById(anyString());
    }

}