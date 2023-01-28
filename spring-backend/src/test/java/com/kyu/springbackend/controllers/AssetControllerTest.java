package com.kyu.springbackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyu.springbackend.config.SecurityConfiguration;
import com.kyu.springbackend.model.Asset;
import com.kyu.springbackend.services.asset.AssetService;
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
@WebMvcTest(value = AssetController.class, excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfiguration.class)})
@ExtendWith(MockitoExtension.class)
@EnableWebMvc
class AssetControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    AssetService service;

    @Test
    void getAllAssetAPI() throws Exception {

        Asset asset1 = new Asset();
        asset1.setId("1");
        Asset asset2 = new Asset();
        asset2.setId("2");
        List<Asset> assets = Arrays.asList(asset1, asset2);

        when(service.findAll()).thenReturn(assets);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/assets")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id", Matchers.equalTo("1")));

        verify(service).findAll();

    }

    @Test
    void getAssetByIdAPI() throws Exception {
        Asset asset = new Asset();
        asset.setId("1");
        asset.setItem("Chair");

        when(service.findById(anyString())).thenReturn(asset);

        mockMvc.perform(get("/api/assets/1"))
                // .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.item", Matchers.equalTo("Chair")));

        verify(service).findById(anyString());
    }

    @Test
    void postAssetAPI() throws Exception {
        Asset asset = new Asset();
        asset.setItem("Chair");

        when(service.save(any())).thenReturn(asset);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/assets")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(asset))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.item", Matchers.equalTo("Chair")));

        verify(service).save(any());
    }

    @Test
    void putAssetAPI() throws Exception {

        Asset asset = new Asset();
        asset.setId("1");
        asset.setItem("Chair");

        when(service.update(anyString(), any())).thenReturn(asset);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/assets/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(asset))
                        .with(csrf()))
                .andExpect(status().isOk());

        verify(service).update(anyString(), any());
    }

    @Test
    void DeleteAssetAPI() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/assets/1")
                        .with(csrf()))
                .andExpect(status().isOk());

        verify(service).deleteById(anyString());
    }
}