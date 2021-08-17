//Deepak
package com.covidpersona.service;

import java.util.List;
import java.util.Optional;

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
	
	public List<PatientVaccination> getPatientVaccinations() {
		return patientVaccinationRepository.findAll();
	}
	
	public Optional<PatientVaccination> getPatientVaccination(int id) {
		return patientVaccinationRepository.findById(id);
	}
	
	public PatientVaccination updatePatientVaccination(PatientVaccination patientVaccination) {
		PatientVaccination updatedVaccination = patientVaccinationRepository.save(patientVaccination);
		return updatedVaccination;
	}
	
	public void deletePatientVaccination(int id) {
		patientVaccinationRepository.deleteById(id);
	}
	
}
