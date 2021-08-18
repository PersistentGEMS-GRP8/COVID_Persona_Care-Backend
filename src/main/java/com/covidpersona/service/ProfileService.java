package com.covidpersona.service;

import org.springframework.stereotype.Service;

import com.covidpersona.config.Roles;
import com.covidpersona.entity.Person;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProfileService {
	private final AdminService adminService;
	private final HospitalAdminService hospitalAdminService;
	private final ManagerService managerService;
	private final ReceptionistService receptionistService;
	private final DoctorService doctorService;
	private final PatientServiceImpl patientService;

	public Person getPersonData(long id, String role) {

		if (role.equals(Roles.ROLE_ADMIN.toString()))
			return adminService.getPersonByUserId(id);

		if (role.equals(Roles.ROLE_HOSPITALADMIN.toString())) {
			return hospitalAdminService.getPersonByUserId(id);
		}

		if (role.equals(Roles.ROLE_MANAGER.toString()))
			return managerService.getPersonByUserId(id);

		if (role.equals(Roles.ROLE_RECEPTIONIST.toString()))
			return receptionistService.getPersonByUserId(id);

		if (role.equals(Roles.ROLE_DOCTOR.toString()))
			return doctorService.getPersonByUserId(id);
		
		if(role.equals(Roles.ROLE_PATIENT.toString()))
			return patientService.getPersonByUserId(id);

		return null;

	}
}
