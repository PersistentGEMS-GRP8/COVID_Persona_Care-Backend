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

import com.covidpersona.entity.Manager;
import com.covidpersona.repository.ManagerRepository;

@RunWith(MockitoJUnitRunner.class)
public class ManagerServiceTest {

	@Mock
	private ManagerRepository managerRepository;
	
	@InjectMocks
	private ManagerService managerService;
	
	@Test
	public void whenSaveManager_shouldReturnManager() {
		Manager manager = new Manager();
		manager.setName("Test name");
		manager.setEmail("test@gmail.com");
		manager.setContactNo("0123123123");
		manager.sethId(2);
		
		when(managerRepository.save(ArgumentMatchers.any(Manager.class))).thenReturn(manager);
		
		Manager created = managerService.addManager(manager);
		
		assertThat(created.getName()).isSameAs(manager.getName());
		
		verify(managerRepository).save(manager);
	}
	
}
