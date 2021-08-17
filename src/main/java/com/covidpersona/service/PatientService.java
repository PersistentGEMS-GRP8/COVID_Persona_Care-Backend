package com.covidpersona.service;

import com.covidpersona.entity.Patient;
import com.covidpersona.entity.PersonaUser;
import com.covidpersona.service.auth.PersonaUserService;

public interface PatientService{
	
	public Patient getPatietByEmail(String email);

	public void save(Patient patient);
	
	public void updatePatientAppDetails(long a_id,long id);
	
	Patient getPatientById(long id);


}
