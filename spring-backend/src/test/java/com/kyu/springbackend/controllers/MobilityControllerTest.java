package com.kyu.springbackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyu.springbackend.config.SecurityConfiguration;
import com.kyu.springbackend.model.Mobility;
import com.kyu.springbackend.services.mobility.MobilityService;
import com.kyu.springbackend.services.mobility.MobilityService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser
@WebMvcTest(value = MobilityController.class, excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfiguration.class)})
@ExtendWith(MockitoExtension.class)
@EnableWebMvc
class MobilityControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    MobilityService service;

    @Test
    void getAllMobilityAPI() throws Exception {

        Mobility mobility1 = new Mobility();
        mobility1.setId("1");
        Mobility mobility2 = new Mobility();
        mobility2.setId("2");
        List<Mobility> mobilities = Arrays.asList(mobility1, mobility2);

        when(service.findAll()).thenReturn(mobilities);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/mobilities")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id", Matchers.equalTo("1")));

        verify(service).findAll();

    }

    @Test
    void getMobilityByIdAPI() throws Exception {
        Mobility mobility = new Mobility();
        mobility.setId("1");
        mobility.setProgram("Program 1");

        when(service.findById(anyString())).thenReturn(mobility);

        mockMvc.perform(get("/api/mobilities/1"))
                // .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.program", Matchers.equalTo("Program 1")));

        verify(service).findById(anyString());
    }

    @Test
    void postMobilityAPI() throws Exception {
        Mobility mobility = new Mobility();
        mobility.setProgram("Program 1");

        when(service.save(any())).thenReturn(mobility);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/mobilities")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mobility))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.program", Matchers.equalTo("Program 1")));

        verify(service).save(any());
    }

    @Test
    void putMobilityAPI() throws Exception {

        Mobility mobility = new Mobility();
        mobility.setId("1");
        mobility.setProgram("Program 1");

        when(service.update(anyString(), any())).thenReturn(mobility);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/mobilities/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mobility))
                        .with(csrf()))
                .andExpect(status().isOk());

        verify(service).update(anyString(), any());
    }

    @Test
    void DeleteMobilityAPI() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/mobilities/1")
                        .with(csrf()))
                .andExpect(status().isOk());

        verify(service).deleteById(anyString());
    }

}