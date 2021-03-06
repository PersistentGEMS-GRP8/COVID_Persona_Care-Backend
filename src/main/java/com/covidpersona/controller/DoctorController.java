package com.covidpersona.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.covidpersona.dto.DoctorDto;
import com.covidpersona.dto.RegisterRequestDto;
import com.covidpersona.entity.Doctor;
import com.covidpersona.entity.Hospital;
import com.covidpersona.entity.Specialization;
import com.covidpersona.exception.ResourceNotFoundException;
import com.covidpersona.service.DoctorService;
import com.covidpersona.service.HospitalService;
import com.covidpersona.service.SpecializationService;
import com.covidpersona.service.auth.PersonaUserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/doctors")
@AllArgsConstructor
public class DoctorController {

	private final DoctorService doctorService;
	private final SpecializationService specializationService;
	private final HospitalService hospitalService;
	private final PersonaUserService personaUserService;

	@PostMapping("/{specializationId}")
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	public long addDoctor(@PathVariable long specializationId, @RequestBody RegisterRequestDto doctor) {
		Doctor doc = (Doctor) doctor.getPerson();
		Specialization specialization = specializationService.getSpecialization(specializationId);
		doc.setSpecialization(specialization);
		return personaUserService.RegisterPersonaUser(doctor.getPersonaUser(), doc);
	}

	@GetMapping
	public List<DoctorDto> getAllDoctor(@RequestParam(required = false) String name,
			@RequestParam(required = false) Integer hosId) {
		if (name != null && hosId != null)
			return doctorService.getAllDoctorByHospital(hosId, name);

		if (name != null)
			return doctorService.getAllDoctorByNameLike(name);

		if (hosId != null)
			return doctorService.getAllDoctorByHospital(hosId, "");

		return doctorService.getAllDoctor();
	}

	@GetMapping("/{id}")
	public DoctorDto getDoctorById(@PathVariable long id) {
		return doctorService.getDoctorByIdWithSpecialization(id);
	}

	@GetMapping("/specialization")
	public List<Doctor> getDoctorBySpecialization(@RequestParam Long id) {
		return doctorService.getAllDoctorBySpecialization(id);
	}

	@PutMapping("/{hospitalId}/{doctorId}")
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	public void addDoctorToHospital(@PathVariable int hospitalId, @PathVariable long doctorId) {
		Hospital hospital = hospitalService.getHospital(hospitalId)
				.orElseThrow(() -> new ResourceNotFoundException("Hospital", "id", hospitalId));
		Doctor doctor = doctorService.getDoctorById(doctorId);

		hospital.addDoctor(doctor);

		hospitalService.updateHospital(hospital);
	}

	@DeleteMapping("/{hospitalId}/{doctorId}")
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	public void deleteDoctorToHospital(@PathVariable int hospitalId, @PathVariable long doctorId) {
		Hospital hospital = hospitalService.getHospital(hospitalId)
				.orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", hospitalId));
		Doctor doctor = doctorService.getDoctorById(doctorId);

		hospital.removeDoctor(doctor);

		hospitalService.updateHospital(hospital);
	}

	@PutMapping
	@PreAuthorize("hasRole('ROLE_DOCTOR')")
	public Doctor updateDoctor(@RequestBody Doctor doctor) {
		return doctorService.updateDoctor(doctor);
	}
}
