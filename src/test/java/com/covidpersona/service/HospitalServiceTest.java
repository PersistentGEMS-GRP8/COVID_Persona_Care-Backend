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
import java.util.Optional;

import com.covidpersona.entity.Hospital;
import com.covidpersona.repository.HospitalRepository;

//Testing Hospital Service Layer
@RunWith(MockitoJUnitRunner.class)
public class HospitalServiceTest {
	@Mock
	private HospitalRepository hospitalRepository;
	@InjectMocks
	private HospitalService hospitalService;
	
	//Test Add Hospital
	@Test
	public void whenSaveHospital_shouldReturnHospital() {
		Hospital hospital = new Hospital();
		hospital.sethName("TestName");
		hospital.setLocation("TestLocation");
		hospital.setNoOfBeds(3);
		when(hospitalRepository.save(ArgumentMatchers.any(Hospital.class))).thenReturn(hospital);
		
		Hospital created = hospitalService.addHospital(hospital);
		assertThat(created.gethName()).isSameAs(hospital.gethName());
		assertThat(created.getLocation()).isSameAs(hospital.getLocation());
		assertThat(created.getNoOfBeds()).isSameAs(hospital.getNoOfBeds());

		verify(hospitalRepository).save(hospital);
	}
	
	//Test Get Hospital List - getHospitals()
	public void shouldReturnAllHospitals() {
		List<Hospital> hospitals = new ArrayList<>();
		hospitals.add(new Hospital());
		
		given(hospitalRepository.findAll()).willReturn(hospitals);
		List<Hospital> expected = hospitalService.getHospitals();
		assertEquals(expected, hospitals);
		verify(hospitalRepository).findAll();
		}
	
	//Test Get Hospital by ID
	@Test
    public void whenGivenId_shouldReturnHospital_ifFound() {
		Hospital hospital = new Hospital();
		hospital.sethId(89);

        when(hospitalRepository.findById(hospital.gethId())).thenReturn(Optional.of(hospital));

        Optional<Hospital> expected = hospitalService.getHospital(hospital.gethId());

        assertThat(expected).isSameAs(hospital);
        verify(hospitalRepository).findById(hospital.gethId());
    }
	
	//Test Update Hospital
	 @Test
	 public void whenGivenId_shouldUpdateHospital_ifFound() {
		 	Hospital hospital = new Hospital();
		 	hospital.sethId(89);
		 	hospital.sethName("Test Name");

	        Hospital newHospital = new Hospital();
	        hospital.sethName("New Test Name");

	        given(hospitalRepository.findById(hospital.gethId())).willReturn(Optional.of(hospital));
	        hospitalService.updateHospital(newHospital);

	        verify(hospitalRepository).save(newHospital);
	        verify(hospitalRepository).findById(hospital.gethId());
	    }
	
	//Test Delete Hospital
	@Test
	public void whenGivenId_shouldDeleteHospital_ifFound(){
		Hospital hospital = new Hospital();
		hospital.sethName("Test Name");
		hospital.sethId(1);
		when(hospitalRepository.findById(hospital.gethId())).thenReturn(Optional.of(hospital));
		hospitalService.deleteHospital(hospital.gethId());
		verify(hospitalRepository).deleteById(hospital.gethId());
		}
	


}
