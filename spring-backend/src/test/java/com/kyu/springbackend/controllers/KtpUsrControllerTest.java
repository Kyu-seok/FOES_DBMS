package com.kyu.springbackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyu.springbackend.config.SecurityConfiguration;
import com.kyu.springbackend.model.KtpUsr;
import com.kyu.springbackend.services.ktpusr.KtpUsrService;
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

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;

@WithMockUser
@WebMvcTest(value = KtpUsrController.class, excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfiguration.class)})
@ExtendWith(MockitoExtension.class)
@EnableWebMvc
class KtpUsrControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    KtpUsrService service;

    @Test
    void getAllKtpUsrAPI() throws Exception {

        KtpUsr ktpUsr1 = new KtpUsr();
        ktpUsr1.setId("1");
        KtpUsr ktpUsr2 = new KtpUsr();
        ktpUsr2.setId("2");
        List<KtpUsr> ktpUsrs = Arrays.asList(ktpUsr1, ktpUsr2);

        when(service.findAll()).thenReturn(ktpUsrs);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/ktp-usrs")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id", Matchers.equalTo("1")));

        verify(service).findAll();

    }

    @Test
    void getKtpUsrByIdAPI() throws Exception {
        KtpUsr ktpUsr = new KtpUsr();
        ktpUsr.setId("1");
        ktpUsr.setProgramName("Program 1");

        when(service.findById(anyString())).thenReturn(ktpUsr);

        mockMvc.perform(get("/api/ktp-usrs/1"))
                // .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.programName", Matchers.equalTo("Program 1")));

        verify(service).findById(anyString());
    }

    @Test
    void postKtpUsrAPI() throws Exception {
        KtpUsr ktpUsr = new KtpUsr();
        ktpUsr.setProgramName("Program 1");

        when(service.save(any())).thenReturn(ktpUsr);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/ktp-usrs")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ktpUsr))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.programName", Matchers.equalTo("Program 1")));

        verify(service).save(any());
    }

    @Test
    void putKtpUsrAPI() throws Exception {

        KtpUsr ktpUsr = new KtpUsr();
        ktpUsr.setId("1");
        ktpUsr.setProgramName("Program 1");

        when(service.update(anyString(), any())).thenReturn(ktpUsr);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/ktp-usrs/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ktpUsr))
                        .with(csrf()))
                .andExpect(status().isOk());

        verify(service).update(anyString(), any());
    }

    @Test
    void DeleteKtpUsrAPI() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/ktp-usrs/1")
                        .with(csrf()))
                .andExpect(status().isOk());

        verify(service).deleteById(anyString());
    }

}