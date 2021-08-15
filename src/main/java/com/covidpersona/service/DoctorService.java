package com.covidpersona.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covidpersona.dto.DoctorDto;
import com.covidpersona.entity.Doctor;
import com.covidpersona.exception.InvalidDataException;
import com.covidpersona.exception.ResourceNotFoundException;
import com.covidpersona.repository.DoctorRepository;

@Service
public class DoctorService extends PersonService<Doctor> {
	@Autowired
	private DoctorRepository doctorRepository;

	public long addDoctor(Doctor doctor) {
		Doctor existDoc = doctorRepository.findByEmail(doctor.getEmail());
		if (existDoc != null)
			throw new InvalidDataException("Email already exists");
		return doctorRepository.save(doctor).getId();
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

	public Doctor updateDoctor(Doctor doctor) {
		Doctor existDoc = getDoctorById(doctor.getId());
		doctor.setUserId(existDoc.getUserId());
		doctor.setSpecialization(existDoc.getSpecialization());
		return doctorRepository.save(doctor);
	}

	public List<DoctorDto> getAllDoctorByHospital(int hosId) {
		return doctorRepository.findAllWithSpecializationByHospital(hosId);
	}

	public List<Doctor> getAllDoctorBySpecialization(long specializationId) {
		return doctorRepository.findBySpecialization_id(specializationId);
	}

	@Override
	public Doctor getPersonByUserId(long id) {
		return doctorRepository.findByUserId_Id(id)
				.orElseThrow(() -> new ResourceNotFoundException("Doctor", "user_id", id));
	}

}
