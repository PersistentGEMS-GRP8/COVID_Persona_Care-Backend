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

import com.covidpersona.entity.Receptionist;
import com.covidpersona.repository.ReceptionistRepository;

@RunWith(MockitoJUnitRunner.class)
public class ReceptionistServiceTest {

	@Mock
	private ReceptionistRepository receptionistRepository;
	
	@InjectMocks
	private ReceptionistService receptionistService;
	
	@Test
	public void whenSaveReceptionist_shouldReturnReceptionist() {
		Receptionist receptionist = new Receptionist();
		receptionist.setName("Test name");
		receptionist.setEmail("test@gmail.com");
		receptionist.setContactNo("0123123123");
		receptionist.sethId(2);
		
		when(receptionistRepository.save(ArgumentMatchers.any(Receptionist.class))).thenReturn(receptionist);
		
		Receptionist created = receptionistService.addReceptionist(receptionist);
		
		assertThat(created.getName()).isSameAs(receptionist.getName());
		assertThat(created.getEmail()).isSameAs(receptionist.getEmail());
		assertThat(created.getContactNo()).isSameAs(receptionist.getContactNo());
		assertThat(created.gethId()).isSameAs(receptionist.gethId());
		
		verify(receptionistRepository).save(receptionist);
	}
	
	@Test
	public void shouldReturnAllReceptionists() {
		List<Receptionist> receptionists = new ArrayList<>();
		receptionists.add(new Receptionist());
		
		given(receptionistRepository.findAll()).willReturn(receptionists);
		
		List<Receptionist> expected = receptionistService.getReceptionists();
		
		assertEquals(expected, receptionists);
		verify(receptionistRepository).findAll();
		
	}
	
	@Test
	public void whenGivenId_shouldReturnReceptionist() {
		Optional<Receptionist> receptionist = Optional.of(new Receptionist());
		receptionist.get().setId(22L);
		
		when(receptionistRepository.findById(receptionist.get().getId())).thenReturn(receptionist);
		
		Optional<Receptionist> expected = receptionistService.getReceptionist(receptionist.get().getId());
		
		assertThat(expected).isSameAs(receptionist);
		verify(receptionistRepository).findById(receptionist.get().getId());
	}
	
	@Test
	public void whenUpdateReceptionist_shouldReturnReceptionist() {
		Receptionist receptionist = new Receptionist();
		receptionist.setId(22L);
		receptionist.setName("Test Name");
		
		Receptionist newReceptionist = new Receptionist();
		newReceptionist.setName("New Test Name");
		newReceptionist.setId(receptionist.getId());
		newReceptionist.setUserId(receptionist.getUserId());

		given(receptionistRepository.findById(receptionist.getId())).willReturn(Optional.of(receptionist));
		when(receptionistRepository.save(ArgumentMatchers.any(Receptionist.class))).thenReturn(newReceptionist);
		
		Receptionist expected = receptionistService.updateReceptionist(newReceptionist);
		
		assertThat(expected).isSameAs(newReceptionist);		
		verify(receptionistRepository).save(newReceptionist);
		verify(receptionistRepository).findById(receptionist.getId());
	
	}
	
	@Test
	public void whenGivenId_shouldDeleteReceptionist() {
		Receptionist receptionist = new Receptionist();
		receptionist.setName("Test Name");
		receptionist.setId(1L);
		
//		when(receptionistRepository.findById(receptionist.getId())).thenReturn(Optional.of(receptionist));
		
		receptionistService.deleteReceptionist(receptionist.getId());
		
		verify(receptionistRepository).deleteById(receptionist.getId());
	}
	
	@Test
	public void whenGivenHId_shouldReturnReceptionists() {
		List<Receptionist> receptionists = new ArrayList<>();
		Receptionist receptionist1 = new Receptionist();
		receptionist1.sethId(2);
		receptionists.add(receptionist1);
		
		given(receptionistRepository.findByhId(receptionist1.gethId())).willReturn(receptionists);
		
		List<Receptionist> expected = receptionistService.getReceptionistsByHId(receptionist1.gethId());
		
		assertEquals(expected, receptionists);
		verify(receptionistRepository).findByhId(receptionist1.gethId());
		
	}
}
