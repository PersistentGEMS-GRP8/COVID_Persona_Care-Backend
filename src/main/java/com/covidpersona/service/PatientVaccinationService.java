//Deepak
package com.covidpersona.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covidpersona.entity.PatientVaccination;
import com.covidpersona.repository.PatientVaccinationRepository;

@Service
public class PatientVaccinationService {

	@Autowired
	private PatientVaccinationRepository patientVaccinationRepository;
	
	public PatientVaccination addPatientVaccination(PatientVaccination patientVaccination) {
		PatientVaccination savedVaccination = patientVaccinationRepository.save(patientVaccination);
		return savedVaccination;
	}
}
