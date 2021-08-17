package com.covidpersona.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.covidpersona.entity.Patient;
import com.covidpersona.entity.PersonaUser;
import com.covidpersona.exception.ResourceNotFoundException;
import com.covidpersona.repository.PatientRepository;

@Service
@Primary
public class PatientServiceImpl extends PersonService<Patient> implements PatientService {

	@Autowired
	private PatientRepository patientRepository;

	// To save Patient Details in the Database
	@Override
	public void save(Patient patient) {

		this.patientRepository.save(patient);
	}

//	public long getUserIdByUsername(String username) {
//		Patient user = patientRepository.findByEmail(username);
//		return user.getId();
//	}

	@Override
	public Patient getPatietByEmail(String email) {

		Patient user = patientRepository.findByEmail(email);
		return user;
	}

	@Override
	public void updatePatientAppDetails(long a_id, long id) {

		this.patientRepository.findById(a_id, id);

	}

	@Override
	public Patient getPatientById(long id) {
		return patientRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Receptionist", "user_id", id));
	}

	@Override
	public Patient getPersonByUserId(long id) {
		return patientRepository.findByUserId_Id(id)
				.orElseThrow(() -> new ResourceNotFoundException("Patient", "user_id", id));
	}

}
