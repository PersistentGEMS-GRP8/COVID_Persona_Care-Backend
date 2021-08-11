package com.covidpersona.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covidpersona.dto.DoctorDto;
import com.covidpersona.entity.Doctor;
import com.covidpersona.exception.ResourceNotFoundException;
import com.covidpersona.repository.DoctorRepository;

@Service
public class DoctorService {
	@Autowired
	private DoctorRepository doctorRepository;

	public Doctor addDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
	}

	public List<DoctorDto> getAllDoctor() {
		return doctorRepository.findAllWithSpecialization();
	}

	public List<DoctorDto> getAllDoctorByNameLike(String name) {
		return doctorRepository.findAllWithSpecializationByNameLike(name);
	}

	public Doctor getDoctorById(long id) {
		return doctorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", id));
	}

	/*
	 * public List<Doctor> getDoctorBySpecialization(long id) { return
	 * doctorRepository.findBySpecialization(null); }
	 */

	public Doctor updateDoctor(Doctor doctor) {
		doctorRepository.findById(doctor.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", doctor.getId()));
		return doctorRepository.save(doctor);
	}

	public List<DoctorDto> getAllDoctorByHospital(int hosId) {
		return doctorRepository.findAllWithSpecializationByHospital(hosId);
	}

}
