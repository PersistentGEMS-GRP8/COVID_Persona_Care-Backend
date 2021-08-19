package com.covidpersona.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.covidpersona.config.auth.JwtAuthenticationEntryPoint;
import com.covidpersona.config.auth.JwtUtil;
import com.covidpersona.entity.Vaccine;
import com.covidpersona.service.VaccineService;
import com.covidpersona.service.auth.CustomUserDetailsService;
import com.covidpersona.service.auth.PersonaUserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(VaccineController.class)
public class VaccineControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private VaccineService service;
	
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
    
	Vaccine RECORD_1=new Vaccine(1L,"Pfizer");
	Vaccine RECORD_2=new Vaccine(2L,"sinopharm");

	final String URL="/vaccines";
	
	@Test
	public void getAllVaccine_Test() throws Exception {
	    List<Vaccine> vaccines = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2));    
	    Mockito.when(service.getVaccines()).thenReturn(vaccines);    
	    mockMvc.perform(MockMvcRequestBuilders
	            .get(URL)
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());
	}
	
	@Test
	public void addVaccine_Test() throws Exception {		
		given(service.addVaccine(RECORD_1)).willReturn(RECORD_1);	
		mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsBytes(RECORD_1)))
                .andExpect(status().isOk());
	}
}
