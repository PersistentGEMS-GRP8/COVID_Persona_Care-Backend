package com.covidpersona.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covidpersona.entity.Specialization;
import com.covidpersona.exception.ResourceNotFoundException;
import com.covidpersona.repository.SpecializationRepository;

@Service
public class SpecializationService {

	@Autowired
	private SpecializationRepository specializationRepository;

	public Specialization addSpecialization(Specialization specialization) {
		return specializationRepository.save(specialization);
	}

	public List<Specialization> getAllSpecialization() {
		return specializationRepository.findAll();
	}

	public Specialization getSpecialization(long id) {
		return specializationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Specialization", "id", id));
	}

}
