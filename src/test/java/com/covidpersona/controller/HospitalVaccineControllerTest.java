package com.covidpersona.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.covidpersona.config.auth.JwtAuthenticationEntryPoint;
import com.covidpersona.config.auth.JwtUtil;
import com.covidpersona.dto.VaccineDto;
import com.covidpersona.entity.HospitalVaccine;
import com.covidpersona.service.HospitalVaccineService;
import com.covidpersona.service.VaccineService;
import com.covidpersona.service.auth.CustomUserDetailsService;
import com.covidpersona.service.auth.PersonaUserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(HospitalVaccineController.class)
public class HospitalVaccineControllerTest {


	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private HospitalVaccineService hospVaccineService;
	
	@MockBean
    private CustomUserDetailsService customUserDetailsService;
 
 
    @MockBean
    private PersonaUserService personaUserService;
 
    @MockBean
    private JwtUtil jwtUtil;
 
    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
    @Autowired
    ObjectMapper mapper;
    
    HospitalVaccine RECORD_1 = new HospitalVaccine(1L,3000,1,1);
    
    @Test
    public void deleteVaccineById_whenDeleteMethod() throws Exception {
    	 doNothing().when(hospVaccineService).deleteVaccine(RECORD_1.getId());
         mockMvc.perform(delete("/vaccinesInHospital/" + Long.toString(RECORD_1.getId()))
                 .contentType(MediaType.APPLICATION_JSON))
                 .andExpect(status().isOk());
    }
    
	@Test
	public void addHospVaccine_whenPostMethod() throws Exception {		
		given(hospVaccineService.addVaccinesToHospital(RECORD_1)).willReturn(RECORD_1);	
		mockMvc.perform(post("/vaccinesInHospital")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsBytes(RECORD_1)))
                .andExpect(status().isOk());
	}
}
