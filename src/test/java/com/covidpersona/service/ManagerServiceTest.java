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
		assertThat(created.getEmail()).isSameAs(manager.getEmail());
		assertThat(created.getContactNo()).isSameAs(manager.getContactNo());
		assertThat(created.gethId()).isSameAs(manager.gethId());
		
		verify(managerRepository).save(manager);
	}
	
	@Test
	public void shouldReturnAllManagers() {
		List<Manager> managers = new ArrayList<>();
		managers.add(new Manager());
		
		given(managerRepository.findAll()).willReturn(managers);
		
		List<Manager> expected = managerService.getManagers();
		
		assertEquals(expected, managers);
		verify(managerRepository).findAll();
		
	}
	
	@Test
	public void whenGivenId_shouldReturnManager() {
		Optional<Manager> manager = Optional.of(new Manager());
		manager.get().setId(22L);
		
		when(managerRepository.findById(manager.get().getId())).thenReturn(manager);
		
		Optional<Manager> expected = managerService.getManager(manager.get().getId());
		
		assertThat(expected).isSameAs(manager);
		verify(managerRepository).findById(manager.get().getId());
	}
	
	@Test
	public void whenUpdateManager_shouldReturnManager() {
		Manager manager = new Manager();
		manager.setId(22L);
		manager.setName("Test Name");
		
		Manager newManager = new Manager();
		newManager.setName("New Test Name");
		newManager.setId(manager.getId());
		newManager.setUserId(manager.getUserId());

		given(managerRepository.findById(manager.getId())).willReturn(Optional.of(manager));
		when(managerRepository.save(ArgumentMatchers.any(Manager.class))).thenReturn(newManager);
		
		Manager expected = managerService.updateManager(newManager);
		
		assertThat(expected).isSameAs(newManager);		
		verify(managerRepository).save(newManager);
		verify(managerRepository).findById(manager.getId());
	
	}
	
	@Test
	public void whenGivenId_shouldDeleteManager() {
		Manager manager = new Manager();
		manager.setName("Test Name");
		manager.setId(1L);
		
//		when(managerRepository.findById(manager.getId())).thenReturn(Optional.of(manager));
		
		managerService.deleteManager(manager.getId());
		
		verify(managerRepository).deleteById(manager.getId());
	}
	
	@Test
	public void whenGivenHId_shouldReturnManagers() {
		List<Manager> managers = new ArrayList<>();
		Manager manager1 = new Manager();
		manager1.sethId(2);
		managers.add(manager1);
		
		given(managerRepository.findByhId(manager1.gethId())).willReturn(managers);
		
		List<Manager> expected = managerService.getManagersByHId(manager1.gethId());
		
		assertEquals(expected, managers);
		verify(managerRepository).findByhId(manager1.gethId());
		
	}
		
}
