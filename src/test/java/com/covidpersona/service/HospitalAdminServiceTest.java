package com.covidpersona.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
	private HospitalAdminRepository hospitalAdminRepo;

	@InjectMocks
	private HospitalAdminService hospitalAdminService;

	@Test
	public void whenSaveHospitalAdmin_shouldReturnHospitalAdmin() {
		HospitalAdmin hospitalAdmin = new HospitalAdmin();
		hospitalAdmin.setName("Kamal");
		when(hospitalAdminRepo.save(ArgumentMatchers.any(HospitalAdmin.class))).thenReturn(hospitalAdmin);
		HospitalAdmin created = hospitalAdminService.addHospAdmin(hospitalAdmin);
		assertThat(created.getName()).isSameAs(hospitalAdmin.getName());
		verify(hospitalAdminRepo).save(hospitalAdmin);
	}

//	@Test
//	public void whenGivenId_shouldUpdateHospitalAdmin_ifFound() {
//		
//		HospitalAdmin hospitalAdmin = new HospitalAdmin();
//		hospitalAdmin.setId(1L);
//		hospitalAdmin.setContactNo("0771122333");
//		hospitalAdmin.setEmail("hospAdmin@gmail.com");
//		hospitalAdmin.sethId(1);
//		hospitalAdmin.setName("Sanath");
//		
//		HospitalAdmin newHospAdmin = new HospitalAdmin();
//		newHospAdmin.setId(hospitalAdmin.getId());
//		newHospAdmin.setContactNo("0718976555");
//		newHospAdmin.setEmail(hospitalAdmin.getEmail());
//		newHospAdmin.sethId(hospitalAdmin.gethId());
//		
//		given(hospitalAdminRepo.findById(hospitalAdmin.getId())).willReturn(Optional.of(hospitalAdmin));
//		 when(hospitalAdminRepo.save(ArgumentMatchers.any(HospitalAdmin.class))).thenReturn(newHospAdmin);        
//		 HospitalAdmin expected= hospitalAdminService.updateHospAdmin(newHospAdmin);
//		assertThat(expected).isSameAs(newHospAdmin);        
//		verify(hospitalAdminRepo).save(newHospAdmin);
//		verify(hospitalAdminRepo).findById(hospitalAdmin.getId());
//	}
//	
}
