package com.covidpersona.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import com.covidpersona.dto.DoctorDto;
import com.covidpersona.dto.RegisterRequestDto;
import com.covidpersona.entity.Doctor;
import com.covidpersona.entity.Hospital;
import com.covidpersona.entity.Person;
import com.covidpersona.entity.PersonaUser;
import com.covidpersona.entity.Specialization;
import com.covidpersona.service.DoctorService;
import com.covidpersona.service.HospitalService;
import com.covidpersona.service.SpecializationService;
import com.covidpersona.service.auth.CustomUserDetailsService;
import com.covidpersona.service.auth.PersonaUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(DoctorController.class)
public class DoctorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private DoctorService doctorService;

	@MockBean
	private SpecializationService specializationService;

	@MockBean
	private CustomUserDetailsService customUserDetailsService;

	@MockBean
	private HospitalService hospitalService;

	@MockBean
	private PersonaUserService personaUserService;

	@MockBean
	private JwtUtil jwtUtil;

	@MockBean
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	private final String URL = "/doctors";

	List<DoctorDto> doctors = new ArrayList<>();
	List<Doctor> docList = new ArrayList<>();
	Set<Doctor> docSet = new HashSet<>();
	Hospital hospital = new Hospital();
	Doctor doctor2 = new Doctor();

	Specialization specialization;

	@BeforeEach
	public void beforeMethod() {
		System.out.println("----Before----");
		doctors.add(new DoctorDto(1, "Doctor1", "doctor1@test.com", "1234567891", 1, "General"));
		doctors.add(new DoctorDto(2, "Doctor2", "doctor2@test.com", "1234567891", 1, "General"));
		doctors.add(new DoctorDto(3, "Doctor3", "doctor3@test.com", "1234567891", 1, "General"));
		doctors.add(new DoctorDto(4, "Doctor4", "doctor4@test.com", "1234567891", 1, "General"));

		Doctor doctor1 = new Doctor();
		doctor1.setId(1);
		doctor1.setName("Doctor 1");
		doctor1.setEmail("doctor1@test.com");
		doctor1.setContactNo("1234567890");
		doctor1.setCreatedAt(new Date());
		doctor1.setUpdatedAt(new Date());
		doctor1.setSpecialization(specialization);

		doctor2.setId(2);
		doctor2.setName("Doctor 2");
		doctor2.setEmail("doctor2@test.com");
		doctor2.setContactNo("1234567890");
		doctor2.setCreatedAt(new Date());
		doctor2.setUpdatedAt(new Date());
		doctor2.setSpecialization(specialization);

		docList.add(doctor1);
		docList.add(doctor2);

		specialization = new Specialization(1, "General", null);

		docSet.add(doctor1);

		hospital.sethId(1);
		hospital.sethName("Hospital");
		hospital.setDoctors(docSet);
	}

	@Test
	public void testGetAllDoctors() throws Exception {

		Mockito.when(doctorService.getAllDoctor()).thenReturn(doctors);

		mockMvc.perform(get(URL).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(4))).andDo(print());

	}

	@Test
	public void testGetDcotorById() throws Exception {

		Mockito.when(doctorService.getDoctorByIdWithSpecialization(Mockito.anyLong())).thenReturn(doctors.get(0));

		mockMvc.perform(get(URL + "/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", notNullValue())).andExpect(jsonPath("$.name", is("Doctor1"))).andDo(print());

	}

	@Test
	public void testGetDoctorBySpecialization() throws Exception {

		Mockito.when(doctorService.getAllDoctorBySpecialization(Mockito.anyLong())).thenReturn(docList);

		mockMvc.perform(get(URL + "/specialization").queryParam("id", "1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$", hasSize(2))).andDo(print());

	}

	@Test
	@WithMockUser(username = "test", password = "test", roles = "MANAGER")
	public void testCreateDoctor() throws JsonProcessingException, Exception {

		Doctor doctor = new Doctor();

		doctor.setName("Doctor");
		doctor.setEmail("doctor@test.com");
		doctor.setContactNo("1234567890");

		PersonaUser personaUser = new PersonaUser();
		personaUser.setUsername("doctor");
		personaUser.setPassword("123456");
		personaUser.setRole("ROLE_DOCTOR");

		Doctor savedDoctor = new Doctor();
		savedDoctor.setId(1);
		savedDoctor.setName("Doctor");
		savedDoctor.setEmail("doctor@test.com");
		savedDoctor.setContactNo("1234567890");
		savedDoctor.setCreatedAt(new Date());
		savedDoctor.setUpdatedAt(new Date());
		savedDoctor.setSpecialization(specialization);

		RegisterRequestDto registerRequestDto = new RegisterRequestDto(personaUser, doctor);

		Mockito.when(specializationService.getSpecialization(Mockito.anyLong())).thenReturn(specialization);

		Mockito.when(personaUserService.RegisterPersonaUser(Mockito.any(PersonaUser.class), Mockito.any(Person.class)))
				.thenReturn(savedDoctor.getId());

		mockMvc.perform(post(URL + "/1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(registerRequestDto))).andExpect(status().isOk())
				.andExpect(jsonPath("$", is(1))).andDo(print());

	}

	@Test
	@WithMockUser(username = "test", password = "test", roles = "DOCTOR")
	public void testUpdateDoctor() throws Exception {

		PersonaUser personaUser = new PersonaUser();
		personaUser.setUsername("admin");
		personaUser.setPassword("123456");
		personaUser.setRole("ROLE_ADMIN");

		Doctor updatedDoctor = new Doctor();
		updatedDoctor.setId(1L);
		updatedDoctor.setName("Doctor Update");
		updatedDoctor.setEmail("doctor@test.com");
		updatedDoctor.setContactNo("0112896354");
		updatedDoctor.setCreatedAt(new Date());
		updatedDoctor.setUpdatedAt(new Date());
		updatedDoctor.setUserId(personaUser);
		updatedDoctor.setSpecialization(specialization);

		Mockito.when(doctorService.updateDoctor(Mockito.any(Doctor.class))).thenReturn(updatedDoctor);

		mockMvc.perform(put(URL).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updatedDoctor))).andExpect(status().isOk())
				.andExpect(jsonPath("$", notNullValue())).andExpect(jsonPath("$.name", is("Doctor Update")))
				.andDo(print());
	}

	@Test
	@WithMockUser(username = "test", password = "test", roles = "MANAGER")
	public void testAddDoctorToHospital() throws Exception {

		Mockito.when(hospitalService.getHospital(Mockito.anyInt())).thenReturn(Optional.of(hospital));
		Mockito.when(doctorService.getDoctorById(Mockito.anyLong())).thenReturn(doctor2);
		hospital.addDoctor(doctor2);
		Mockito.when(hospitalService.updateHospital(Mockito.any(Hospital.class))).thenReturn(hospital);

		mockMvc.perform(put(URL + "/1/2").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(print());
	}

	@Test
	@WithMockUser(username = "test", password = "test", roles = "MANAGER")
	public void testDeleteDoctorToHospital() throws Exception {

		Mockito.when(hospitalService.getHospital(Mockito.anyInt())).thenReturn(Optional.of(hospital));
		Mockito.when(doctorService.getDoctorById(Mockito.anyLong())).thenReturn(doctor2);
		hospital.removeDoctor(doctor2);
		Mockito.when(hospitalService.updateHospital(Mockito.any(Hospital.class))).thenReturn(hospital);

		mockMvc.perform(delete(URL + "/1/2").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(print());
	}

}
