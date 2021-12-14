package com.covidpersona.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.covidpersona.entity.Patient;
import com.covidpersona.entity.PersonaUser;
import com.covidpersona.entity.Receptionist;
import com.covidpersona.exception.ResourceNotFoundException;
import com.covidpersona.repository.PatientRepository;

@Service
@Primary
public class PatientServiceImpl extends PersonService<Patient> implements PatientService {

	@Autowired
	private PatientRepository patientRepository;
	
	
	@Override
	public List<Patient> getPatientList() {
		return patientRepository.findAll();
	}

	// To save Patient Details in the Database
	@Override
	public Patient addPatient(Patient patient) {
		Patient savedPatient = patientRepository.save(patient);
		return savedPatient;
	}

//	public long getUserIdByUsername(String username) {
//		Patient user = patientRepository.findByEmail(username);
//		return user.getId();
//	}

	@Override
	public Patient getPatientByEmail(String email) {

		Patient user = patientRepository.findByEmail(email);
		return user;
	}

	@Override
	public void updatePatient(long a_id, long id) {

		this.patientRepository.findById(a_id, id);

	}
	
	public Patient updatePatient(Patient patient) {
		Optional<Patient> existPatient = getPatientById(patient.getId());
		patient.setUserId(existPatient.get().getUserId());
		return patientRepository.save(patient);
	}

	@Override
	public Optional<Patient> getPatientById(long id) {
		return patientRepository.findById(id);
	}

	@Override
	public Patient getPersonByUserId(long id) {
		return patientRepository.findByUserId_Id(id)
				.orElseThrow(() -> new ResourceNotFoundException("Patient", "user_id", id));
	}

	public void deletePatient(long id) {
		patientRepository.deleteById(id);
	}

//	public Optional<Patient> getPatient(long id) {
//		// TODO Auto-generated method stub
//		return patientRepository.getById(null)
//	}



}
