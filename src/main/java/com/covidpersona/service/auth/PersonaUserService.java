package com.covidpersona.service.auth;

import com.covidpersona.entity.Admin;
import com.covidpersona.entity.Doctor;
import com.covidpersona.entity.HospitalAdmin;
import com.covidpersona.entity.Manager;
import com.covidpersona.entity.Person;
import com.covidpersona.entity.PersonaUser;
import com.covidpersona.exception.InvalidDataException;
import com.covidpersona.repository.AdminRepository;
import com.covidpersona.repository.DoctorRepository;
import com.covidpersona.repository.HospitalAdminRepository;
import com.covidpersona.repository.ManagerRepository;
import com.covidpersona.repository.UserRepository;

import lombok.AllArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonaUserService {

	private final UserRepository userRepository;
	private final AdminRepository adminRepository;
	private final HospitalAdminRepository hospitalAdminRepository;
	private final ManagerRepository managerRepository;
	private final DoctorRepository doctorRepository;

	public long RegisterPersonaUser(PersonaUser personaUser, Person person) throws InvalidDataException {
		if (personaUser.getUsername().isEmpty() || personaUser.getUsername() == null) {
			throw new InvalidDataException("username is required");
		}
		if (personaUser.getPassword().isEmpty() || personaUser.getPassword() == null) {
			throw new InvalidDataException("password is required");
		}
		PersonaUser oldUser = userRepository.finddaoUserByUserName(personaUser.getUsername());
		if (oldUser != null) {
			throw new InvalidDataException("username already exists");
		}
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        PersonaUser personaUser = new PersonaUser();
//        personaUser.setUsername(username);
		personaUser.setPassword(passwordEncoder.encode(personaUser.getPassword()));
//        personaUser.setRole(role);
		person.setUserId(personaUser);
		if (person instanceof Admin) {
			Admin admin = (Admin) person;
			return adminRepository.save(admin).getId();
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
			return doctorRepository.save(doctor).getId();
		}

		return userRepository.save(personaUser).getId();
	}
}
