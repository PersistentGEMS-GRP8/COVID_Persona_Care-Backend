package com.covidpersona.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.covidpersona.config.auth.JwtAuthenticationEntryPoint;
import com.covidpersona.config.auth.JwtUtil;
import com.covidpersona.entity.Hospital;
import com.covidpersona.service.HospitalService;
import com.covidpersona.service.auth.CustomUserDetailsService;
import com.covidpersona.service.auth.PersonaUserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WithMockUser(username = "test", password = "test", roles = "ADMIN")
@RunWith(SpringRunner.class)
@WebMvcTest(HospitalController.class)
public class HospitalControllerTest {
	
	@MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private PersonaUserService personaUserService;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private HospitalService hospitalService;
	
	
	@Autowired
    ObjectMapper mapper;
	
	@Test
	public void createHospital_whenPostMethod() throws Exception {
		Hospital hospital = new Hospital();
		hospital.sethName("Test Name");
		
		given(hospitalService.addHospital(hospital)).willReturn(hospital);
		
		mockMvc.perform(post("/hospitals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsBytes(hospital)))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.hName", is(hospital.gethName())));
	}
	
	@Test
    public void removeHospitalById_whenDeleteMethod() throws Exception {
		Hospital hospital = new Hospital();
		hospital.sethName("Test Name");
		hospital.sethId(22);

        doNothing().when(hospitalService).deleteHospital(hospital.gethId());

        mockMvc.perform(delete("/hospitals/" + Long.toString(hospital.gethId()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
	
	@Test
    public void getHospitalById_whenGetMethod() throws Exception {

		Optional<Hospital> hospital = Optional.of(new Hospital());
		hospital.get().sethName("Test Name");
		hospital.get().sethId(22);

        given(hospitalService.getHospital(hospital.get().gethId())).willReturn(hospital);

        mockMvc.perform(get("/hospitals/" + Long.toString(hospital.get().gethId()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("hName", is(hospital.get().gethName())));
    }
	
	@Test
    public void listAllHospitals_whenGetMethod() throws Exception {

		Hospital hospital = new Hospital();
		hospital.sethName("Test name");

        List<Hospital> hospitals = new ArrayList<>();
        hospitals.add(hospital);

        given(hospitalService.getHospitals()).willReturn(hospitals);

        mockMvc.perform(get("/hospitals")
                .contentType(MediaType.APPLICATION_JSON))
        		.andDo(print())
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].hName", is(hospital.gethName())));
    }
	
}
