package com.covidpersona.service;

import static org.assertj.core.api.Assertions.assertThat;
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
import java.util.Optional;
import static org.mockito.ArgumentMatchers.anyLong;

import com.covidpersona.dto.VaccineDto;
import com.covidpersona.entity.HospitalVaccine;
import com.covidpersona.repository.HospitalVaccineRepository;

@RunWith(MockitoJUnitRunner.class)
public class HospitalVaccineServiceTest {

	@Mock
	private HospitalVaccineRepository hospitalVaccineRepository;

	@InjectMocks
	private HospitalVaccineService hospitalVaccineService;


	@Test
	public void whenGivenHospId_shouldReturnVaccine_ifFound() {
		List<VaccineDto> vaccineList = new ArrayList<>();
		int hospId = 1;

		when(hospitalVaccineRepository.findAllVaccinesInHospital(hospId)).thenReturn(vaccineList);
		List<VaccineDto> expected = hospitalVaccineService.getAllVaccines(hospId);
		assertThat(expected).isSameAs(vaccineList);
		verify(hospitalVaccineRepository).findAllVaccinesInHospital(hospId);
	}

	
	@Test
	public void whenGivenId_shouldDeleteVaccine() {
		HospitalVaccine hospVaccine = new HospitalVaccine();
		hospVaccine.setCount(4000);
		hospVaccine.setId(1L);
		hospVaccine.setHospitalId(1);
		hospVaccine.setVaccineId(3);
		when(hospitalVaccineRepository.findById(hospVaccine.getId())).thenReturn(Optional.of(hospVaccine));
		hospitalVaccineService.deleteVaccine(hospVaccine.getId());
		verify(hospitalVaccineRepository).deleteById(hospVaccine.getId());
	}

	@Test(expected = RuntimeException.class)
	public void should_throw_exception_when_vaccine_doesnt_exist() {
		HospitalVaccine hospVaccine = new HospitalVaccine();
		hospVaccine.setCount(4000);
		hospVaccine.setId(80L);
		hospVaccine.setHospitalId(1);

		given(hospitalVaccineRepository.findById(anyLong())).willReturn(Optional.ofNullable(null));
		hospitalVaccineService.deleteVaccine(hospVaccine.getId());
	}
	
//	@Test
//	public void whenSaveHospVaccine_shouldReturnHospVaccine() {
//		HospitalVaccine hospVaccine = new HospitalVaccine();
//	//	hospVaccine.setId(1L);
//		hospVaccine.setCount(500);
//		hospVaccine.setHospitalId(1);
//		hospVaccine.setVaccineId(1);
//		when(hospitalVaccineRepo.save(ArgumentMatchers.any(HospitalVaccine.class))).thenReturn(hospVaccine);
//		HospitalVaccine created = hospitalVaccineService.addVaccinesToHospital(hospVaccine);
//		assertThat(created.getCount()).isSameAs(hospVaccine.getCount());
//		verify(hospitalVaccineRepo).save(hospVaccine);
//	}
	
//	@Test
//	public void whenGivenId_shouldUpdateVaccine_ifFound() {
//		
//		HospitalVaccine vaccine = new HospitalVaccine();
//		vaccine.setId(1);
//		vaccine.setCount(3500);
//		vaccine.setHospitalId(1);
//		vaccine.setVaccineId(1);
//		
//		HospitalVaccine updatedVaccine = new HospitalVaccine();
//		updatedVaccine.setId(vaccine.getId());
//		updatedVaccine.setCount(7000);
//		updatedVaccine.setHospitalId(vaccine.getHospitalId());
//		updatedVaccine.setVaccineId(vaccine.getHospitalId());
//		
//		given(hospitalVaccineRepository.findById(vaccine.getId())).willReturn(Optional.of(vaccine));
//		 when(hospitalVaccineRepository.save(ArgumentMatchers.any(HospitalVaccine.class))).thenReturn(updatedVaccine);        
//		HospitalVaccine expected= hospitalVaccineService.editVaccineInHospital(updatedVaccine);
//		assertThat(expected).isSameAs(updatedVaccine);        
//		verify(hospitalVaccineRepository).save(updatedVaccine);
//		verify(hospitalVaccineRepository).findById(vaccine.getId());
//	}

}
