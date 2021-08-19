package com.covidpersona.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.covidpersona.config.auth.JwtAuthenticationEntryPoint;
import com.covidpersona.config.auth.JwtUtil;
import com.covidpersona.dto.SpecializationDto;
import com.covidpersona.entity.Specialization;
import com.covidpersona.service.SpecializationService;
import com.covidpersona.service.auth.CustomUserDetailsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(SpecializationController.class)
@WithMockUser(username = "test", password = "test", roles = "ADMIN")
public class SpecializationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private SpecializationService specializationService;

	@MockBean
	private CustomUserDetailsService customUserDetailsService;

	@MockBean
	private JwtUtil jwtUtil;

	@MockBean
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	private final String URL = "/specializations";

	private List<Specialization> spList = new ArrayList<>();

	@BeforeEach
	public void beforeMethod() {
		spList.add(new Specialization(1, "General", null));
		spList.add(new Specialization(2, "General Physician", null));
		spList.add(new Specialization(3, "Cardio", null));
	}

	@Test
	public void testGetAllSpecializations() throws Exception {

		Mockito.when(specializationService.getAllSpecialization()).thenReturn(spList);

		mockMvc.perform(get(URL).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3))).andDo(print());

	}

	@Test
	public void testGetSpecializationsById() throws Exception {

		Mockito.when(specializationService.getSpecialization(Mockito.anyLong())).thenReturn(spList.get(0));

		mockMvc.perform(get(URL + "/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("General"))).andDo(print());

	}

	@Test
	public void testCreateSpecialization() throws JsonProcessingException, Exception {
		Specialization newSpecialization = new Specialization();
		newSpecialization.setName("General");
		Specialization savedSpecialization = new Specialization(1, "General", null);

		Mockito.when(specializationService.addSpecialization(Mockito.any(Specialization.class)))
				.thenReturn(savedSpecialization);

		mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(newSpecialization))).andExpect(status().isOk()).andDo(print());

	}

	@Test
	public void testDeleteSpecialization() throws Exception {
		Specialization specialization = new Specialization(1, "General", null);
		Mockito.when(specializationService.getSpecialization(Mockito.anyLong())).thenReturn(specialization);

		mockMvc.perform(delete(URL + "/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(print());
	}

	@Test
	public void testUpdateSpecialization() throws JsonProcessingException, Exception {
		Specialization updatedSpecialization = new Specialization(1, "Updated", null);
		Mockito.when(specializationService.updateSpecialization(Mockito.any(Specialization.class)))
				.thenReturn(updatedSpecialization);

		mockMvc.perform(put(URL).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updatedSpecialization))).andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("Updated"))).andDo(print());
	}

	@Test
	public void testGetSpecializationWithDoctorCount() throws Exception {
		List<SpecializationDto> spDtos = Arrays.asList(new SpecializationDto(1, "General", 5),
				new SpecializationDto(2, "Spec 2", 5), new SpecializationDto(3, "Spec 3", 5));
		Mockito.when(specializationService.findAllWithDoctorCount(Mockito.anyString())).thenReturn(spDtos);

		mockMvc.perform(get(URL + "/doctor").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3))).andDo(print());

	}

}
