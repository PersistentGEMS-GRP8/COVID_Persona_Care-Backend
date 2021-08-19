package com.covidpersona.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.covidpersona.entity.HospitalAdmin;
import com.covidpersona.repository.HospitalAdminRepository;

@RunWith(MockitoJUnitRunner.class)
public class HospitalAdminServiceTest {

	@Mock
	private HospitalAdminRepository hospAdminRepository;
	
	@InjectMocks
	private HospitalAdminService hospAdminService;
	
	@Test
	public void addHospitalAdmin_Test() {
		HospitalAdmin hospAdmin = new HospitalAdmin();
		hospAdmin.setName("TestName");
		hospAdmin.setEmail("test@gmail.com");
		hospAdmin.setContactNo("0112233444");
		hospAdmin.sethId(1);
		
		when(hospAdminRepository.save(ArgumentMatchers.any(HospitalAdmin.class))).thenReturn(hospAdmin);
		
		HospitalAdmin created = hospAdminService.addHospAdmin(hospAdmin);
		
		assertThat(created.getName()).isSameAs(hospAdmin.getName());
		assertThat(created.getEmail()).isSameAs(hospAdmin.getEmail());
		assertThat(created.getContactNo()).isSameAs(hospAdmin.getContactNo());
		assertThat(created.gethId()).isSameAs(hospAdmin.gethId());
		
		verify(hospAdminRepository).save(hospAdmin);
	}
	
	@Test
	public void shouldReturnAllHospitalAdmin() {
		List<HospitalAdmin> hospAdmins = new ArrayList<>();
		hospAdmins.add(new HospitalAdmin());		
		given(hospAdminRepository.findAll()).willReturn(hospAdmins);		
		List<HospitalAdmin> expected = hospAdminService.getHospAdmins();		
		assertEquals(expected, hospAdmins);
		verify(hospAdminRepository).findAll();
		
	}
	
	@Test
	public void whenGivenId_shouldReturnHospitalAdmin() {
		Optional<HospitalAdmin> hospAdmin = Optional.of(new HospitalAdmin());
		hospAdmin.get().setId(1L);	
		when(hospAdminRepository.findById(hospAdmin.get().getId())).thenReturn(hospAdmin);		
		Optional<HospitalAdmin> expected = hospAdminService.getHospAdmin(hospAdmin.get().getId());		
		assertThat(expected).isSameAs(hospAdmin);
		verify(hospAdminRepository).findById(hospAdmin.get().getId());
	}
	
	@Test
	public void whenUpdateHospitalAdmin_shouldReturnHospitalAdmin() {
		HospitalAdmin hospAdmin = new HospitalAdmin();
		hospAdmin.setId(1L);
		hospAdmin.setName("Test Name");
		
		HospitalAdmin newHospAdmin = new HospitalAdmin();
		newHospAdmin.setName("New Test Name");
		newHospAdmin.setId(hospAdmin.getId());
		newHospAdmin.setUserId(hospAdmin.getUserId());

		given(hospAdminRepository.findById(hospAdmin.getId())).willReturn(Optional.of(hospAdmin));
		when(hospAdminRepository.save(ArgumentMatchers.any(HospitalAdmin.class))).thenReturn(newHospAdmin);
		
		HospitalAdmin expected = hospAdminService.updateHospAdmin(newHospAdmin);
		
		assertThat(expected).isSameAs(newHospAdmin);		
		verify(hospAdminRepository).save(newHospAdmin);
		verify(hospAdminRepository).findById(hospAdmin.getId());
	
	}
	
	@Test
	public void whenGivenId_shouldDeleteManager() {
		HospitalAdmin hospAdmin = new HospitalAdmin();
		hospAdmin.setName("Test Name");
		hospAdmin.setId(1L);		
		hospAdminService.deleteHospAdmin(hospAdmin.getId());
		verify(hospAdminRepository).deleteById(hospAdmin.getId());
	}
	
		
}
