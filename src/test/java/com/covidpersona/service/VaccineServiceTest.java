package com.covidpersona.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.BDDMockito.given;

import com.covidpersona.entity.Vaccine;
import com.covidpersona.repository.VaccineRepository;

@RunWith(MockitoJUnitRunner.class)
public class VaccineServiceTest {

	@Mock
	private VaccineRepository vaccineRepo;

	@InjectMocks
	private VaccineService vaccineService;

	@Test
	public void whenSaveVaccine_shouldReturnVaccine() {
		Vaccine vaccine = new Vaccine();
		vaccine.setName("Pfizer");
		when(vaccineRepo.save(ArgumentMatchers.any(Vaccine.class))).thenReturn(vaccine);
		Vaccine created = vaccineService.addVaccine(vaccine);
		assertThat(created.getName()).isSameAs(vaccine.getName());
		verify(vaccineRepo).save(vaccine);
	}

	@Test
	public void shouldReturnAllVaccines() {
		List<Vaccine> vaccines = new ArrayList();
		vaccines.add(new Vaccine());
		given(vaccineRepo.findAll()).willReturn(vaccines);
		List<Vaccine> expected = vaccineService.getVaccines();
		assertEquals(expected, vaccines);
		verify(vaccineRepo).findAll();
	}
}
