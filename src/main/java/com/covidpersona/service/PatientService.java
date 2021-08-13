package com.covidpersona.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covidpersona.entity.Patient;
import com.covidpersona.exception.ResourceNotFoundException;
import com.covidpersona.repository.PatientRepository;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;

	public Patient addPatient(Patient patient) {
		return patientRepository.save(patient);
	}

	public Patient updatePatient(Patient patient) {
		patientRepository.findById(patient.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Patient", "id", patient.getId()));
		return patientRepository.save(patient);
	}

	public void deletePatient(long id) {
		patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));
		patientRepository.deleteById(id);
	}

	public List<Patient> getAllPatient() {
		return patientRepository.findAll();
	}

	public Patient getPatient(long id) {
		Patient patient = patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));
		return patient;
	}
	
}




