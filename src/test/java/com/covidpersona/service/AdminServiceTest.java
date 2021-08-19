package com.covidpersona.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.covidpersona.entity.Admin;
import com.covidpersona.entity.Manager;
import com.covidpersona.repository.AdminRepository;
import com.covidpersona.repository.ManagerRepository;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceTest {

	@Mock
	private AdminRepository adminRepository;
	
	@InjectMocks
	private AdminService adminService;
	
	@Test
	public void whenSaveManager_shouldReturnManager() {
		Admin admin = new Admin();
		admin.setEmail("test@gmail.com");
		admin.setContactNo("0123123123");
		
		when(adminRepository.save(ArgumentMatchers.any(Admin.class))).thenReturn(admin);
		
		long created = adminService.addAdmin(admin);
		
//		assertThat(created.getName()).isSameAs(admin.getName());
		
		verify(adminRepository).save(admin);
	}
	
}
