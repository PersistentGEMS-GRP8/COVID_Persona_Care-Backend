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
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
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
import com.covidpersona.dto.RegisterRequestDto;
import com.covidpersona.entity.Admin;
import com.covidpersona.entity.Person;
import com.covidpersona.entity.PersonaUser;
import com.covidpersona.service.AdminService;
import com.covidpersona.service.auth.CustomUserDetailsService;
import com.covidpersona.service.auth.PersonaUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(AdminController.class)
@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
public class AdminControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private AdminService adminService;

	@MockBean
	private PersonaUserService personaUserService;

	@MockBean
	private CustomUserDetailsService customUserDetailsService;

	@MockBean
	private JwtUtil jwtUtil;

	@MockBean
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	Admin admin1 = new Admin();
	Admin admin2 = new Admin();

	final String URL = "/admin";

	@BeforeEach
	public void beforeMethod() {
		System.out.println("----Before----");
		admin1.setId(1L);
		admin1.setName("Admin 1");
		admin1.setEmail("admin1@test.com");
		admin1.setContactNo("0112896354");
		admin1.setCreatedAt(new Date());
		admin1.setUpdatedAt(new Date());

		admin2.setId(2L);
		admin2.setName("Admin 2");
		admin2.setEmail("admin2@test.com");
		admin2.setContactNo("0112896354");
		admin2.setCreatedAt(new Date());
		admin2.setUpdatedAt(new Date());
	}

	@Test
	public void testGetAllAdmin() throws Exception {

		List<Admin> admins = new ArrayList<>(Arrays.asList(admin1, admin2));

		Mockito.when(adminService.getAllAdmin()).thenReturn(admins);

		mockMvc.perform(get(URL).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2))).andDo(print());

	}

	@Test
	public void testGetAdminById() throws Exception {

		Mockito.when(adminService.getAdmin(Mockito.anyLong())).thenReturn(admin1);

		mockMvc.perform(get(URL + "/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", notNullValue())).andExpect(jsonPath("$.name", is("Admin 1"))).andDo(print());

	}

	@Test
	public void testDeleteAdmin() throws Exception {
		mockMvc.perform(delete(URL + "/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(print());
	}

	@Test
	public void testUpdateAdmin() throws Exception {

		PersonaUser personaUser = new PersonaUser();
		personaUser.setUsername("admin");
		personaUser.setPassword("123456");
		personaUser.setRole("ROLE_ADMIN");

		Admin updatedAdmin = new Admin();
		updatedAdmin.setId(1L);
		updatedAdmin.setName("Admin Update");
		updatedAdmin.setEmail("admin1@test.com");
		updatedAdmin.setContactNo("0112896354");
		updatedAdmin.setCreatedAt(new Date());
		updatedAdmin.setUpdatedAt(new Date());
		updatedAdmin.setUserId(personaUser);

		Mockito.when(adminService.updateAdmin(Mockito.any(Admin.class))).thenReturn(updatedAdmin);

		mockMvc.perform(
				put(URL).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(updatedAdmin)))
				.andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.name", is("Admin Update"))).andDo(print());
	}

	@Test
	public void testAddAdmin() throws JsonProcessingException, Exception {

		Admin newAdmin = new Admin();
		newAdmin.setName("Admin 1");
		newAdmin.setEmail("admin1@test.com");
		newAdmin.setContactNo("0112896354");

		PersonaUser personaUser = new PersonaUser();
		personaUser.setUsername("admin");
		personaUser.setPassword("123456");
		personaUser.setRole("ROLE_ADMIN");

		RegisterRequestDto registerRequestDto = new RegisterRequestDto(personaUser, newAdmin);

		Admin savedAdmin = new Admin();
		savedAdmin.setId(1);
		savedAdmin.setName("Admin 1");
		savedAdmin.setEmail("admin1@test.com");
		savedAdmin.setContactNo("0112896354");
		savedAdmin.setCreatedAt(new Date());
		savedAdmin.setUpdatedAt(new Date());

		Mockito.when(personaUserService.RegisterPersonaUser(Mockito.any(PersonaUser.class), Mockito.any(Person.class)))
				.thenReturn(savedAdmin.getId());

		mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(registerRequestDto))).andExpect(status().isOk())
				.andExpect(jsonPath("$", is(1))).andDo(print());
	}
}
