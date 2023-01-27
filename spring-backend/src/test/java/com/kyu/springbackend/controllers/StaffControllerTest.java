package com.kyu.springbackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyu.springbackend.config.SecurityConfiguration;
import com.kyu.springbackend.model.Staff;
import com.kyu.springbackend.services.staff.StaffService;
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


@WithMockUser
@WebMvcTest(value = StaffController.class, excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfiguration.class)})
@ExtendWith(MockitoExtension.class)
@EnableWebMvc
class StaffControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    StaffService service;

    @Test
    void getAllStaffAPI() throws Exception {

        Staff staff1 = new Staff();
        staff1.setId("1");
        Staff staff2 = new Staff();
        staff2.setId("2");
        List<Staff> staffs = Arrays.asList(staff1, staff2);

        when(service.findAll()).thenReturn(staffs);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/staffs")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id", Matchers.equalTo("1")));

        verify(service).findAll();

    }

    @Test
    void getStaffByIdAPI() throws Exception {
        Staff staff = new Staff();
        staff.setId("1");
        staff.setFirstName("Adam");

        when(service.findById(anyString())).thenReturn(staff);

        mockMvc.perform(get("/api/staffs/1"))
                // .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", Matchers.equalTo("Adam")));

        verify(service).findById(anyString());
    }

    @Test
    void postStaffAPI() throws Exception {
        Staff staff = new Staff();
        staff.setFirstName("Adam");

        when(service.save(any())).thenReturn(staff);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/staffs")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(staff))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", Matchers.equalTo("Adam")));

        verify(service).save(any());
    }

    @Test
    void putStaffAPI() throws Exception {

        Staff staff = new Staff();
        staff.setId("1");
        staff.setFirstName("Adam");

        when(service.update(anyString(), any())).thenReturn(staff);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/staffs/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(staff))
                        .with(csrf()))
                .andExpect(status().isOk());

        verify(service).update(anyString(), any());
    }

    @Test
    void DeleteStaffAPI() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/staffs/1")
                        .with(csrf()))
                .andExpect(status().isOk());

        verify(service).deleteById(anyString());
    }
}