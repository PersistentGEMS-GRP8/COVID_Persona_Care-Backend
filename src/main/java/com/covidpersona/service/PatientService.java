package com.covidpersona.service;

import java.util.List;
import java.util.Optional;

import com.covidpersona.entity.Patient;
import com.covidpersona.entity.PersonaUser;
import com.covidpersona.service.auth.PersonaUserService;

public interface PatientService{
	
	List<Patient> getPatientList();
	
	public Patient addPatient(Patient patient);
	
	void updatePatient(long a_id, long id);
	
	public Patient getPatientByEmail(String email);

	Optional<Patient> getPatientById(long id);
	
//	public void updatePatientAppDetails(long a_id,long id);
	

}
