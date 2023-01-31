package com.kyu.springbackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyu.springbackend.config.SecurityConfiguration;
import com.kyu.springbackend.model.ResearchAward;
import com.kyu.springbackend.services.researchAward.ResearchAwardService;
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
@WebMvcTest(value = ResearchAwardController.class, excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfiguration.class)})
@ExtendWith(MockitoExtension.class)
@EnableWebMvc
class ResearchAwardControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ResearchAwardService service;

    @Test
    void getAllResearchAwardAPI() throws Exception {

        ResearchAward researchAward1 = new ResearchAward();
        researchAward1.setId("1");
        ResearchAward researchAward2 = new ResearchAward();
        researchAward2.setId("2");
        List<ResearchAward> researchAwards = Arrays.asList(researchAward1, researchAward2);

        when(service.findAll()).thenReturn(researchAwards);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/research-awards")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id", Matchers.equalTo("1")));

        verify(service).findAll();

    }

    @Test
    void getResearchAwardByIdAPI() throws Exception {
        ResearchAward researchAward = new ResearchAward();
        researchAward.setId("1");
        researchAward.setProjectTitle("Project 1");

        when(service.findById(anyString())).thenReturn(researchAward);

        mockMvc.perform(get("/api/research-awards/1"))
                // .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.projectTitle", Matchers.equalTo("Project 1")));

        verify(service).findById(anyString());
    }

    @Test
    void postResearchAwardAPI() throws Exception {
        ResearchAward researchAward = new ResearchAward();
        researchAward.setProjectTitle("Project 1");

        when(service.save(any())).thenReturn(researchAward);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/research-awards")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(researchAward))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.projectTitle", Matchers.equalTo("Project 1")));

        verify(service).save(any());
    }

    @Test
    void putResearchAwardAPI() throws Exception {

        ResearchAward researchAward = new ResearchAward();
        researchAward.setId("1");
        researchAward.setProjectTitle("Project 1");

        when(service.update(anyString(), any())).thenReturn(researchAward);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/research-awards/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(researchAward))
                        .with(csrf()))
                .andExpect(status().isOk());

        verify(service).update(anyString(), any());
    }

    @Test
    void DeleteResearchAwardAPI() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/research-awards/1")
                        .with(csrf()))
                .andExpect(status().isOk());

        verify(service).deleteById(anyString());
    }

}