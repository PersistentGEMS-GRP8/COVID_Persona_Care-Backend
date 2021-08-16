package com.covidpersona.service.auth;

import com.covidpersona.entity.Admin;
import com.covidpersona.entity.Doctor;
import com.covidpersona.entity.HospitalAdmin;
import com.covidpersona.entity.Manager;
import com.covidpersona.entity.Patient;
import com.covidpersona.entity.Person;
import com.covidpersona.entity.PersonaUser;
import com.covidpersona.entity.Receptionist;
import com.covidpersona.exception.InvalidDataException;
import com.covidpersona.repository.HospitalAdminRepository;
import com.covidpersona.repository.ManagerRepository;
import com.covidpersona.repository.ReceptionistRepository;
import com.covidpersona.repository.PatientRepository;
import com.covidpersona.repository.UserRepository;
import com.covidpersona.service.AdminService;
import com.covidpersona.service.DoctorService;

import lombok.AllArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonaUserService {

	private final UserRepository userRepository;
	private final AdminService adminService;
	private final HospitalAdminRepository hospitalAdminRepository;
	private final ManagerRepository managerRepository;
	private final ReceptionistRepository receptionistRepository;
	private final PatientRepository patientRepository;
	private final DoctorService doctorService;

	public long RegisterPersonaUser(PersonaUser personaUser, Person person) throws InvalidDataException {
		if (personaUser.getUsername().isEmpty() || personaUser.getUsername() == null) {
			throw new InvalidDataException("username is required");
		}
		if (personaUser.getPassword().isEmpty() || personaUser.getPassword() == null) {
			throw new InvalidDataException("password is required");
		}
		PersonaUser oldUser = userRepository.finddaoUserByUserName(personaUser.getUsername());
		if (oldUser != null) {
			throw new InvalidDataException("Username already exists");
		}
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		personaUser.setPassword(passwordEncoder.encode(personaUser.getPassword()));
		person.setUserId(personaUser);
		if (person instanceof Admin) {
			Admin admin = (Admin) person;
			return adminService.addAdmin(admin);
		}

		if (person instanceof HospitalAdmin) {
			HospitalAdmin hospitalAdmin = (HospitalAdmin) person;
			return hospitalAdminRepository.save(hospitalAdmin).getId();
		}

		if (person instanceof Manager) {
			Manager manager = (Manager) person;
			return managerRepository.save(manager).getId();
		}

		if (person instanceof Doctor) {
			Doctor doctor = (Doctor) person;
			return doctorService.addDoctor(doctor);
		}

		if (person instanceof Receptionist) {
			Receptionist receptionist = (Receptionist) person;
			return receptionistRepository.save(receptionist).getId();
		}

		if (person instanceof Patient) {
			Patient patient = (Patient) person;
			return patientRepository.save(patient).getId();

		}

		return userRepository.save(personaUser).getId();
	}
}
