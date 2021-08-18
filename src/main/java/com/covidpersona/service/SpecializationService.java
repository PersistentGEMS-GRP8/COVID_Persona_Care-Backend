package com.covidpersona.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covidpersona.dto.SpecializationDto;
import com.covidpersona.entity.Specialization;
import com.covidpersona.exception.InvalidDataException;
import com.covidpersona.exception.ResourceNotFoundException;
import com.covidpersona.repository.SpecializationRepository;

@Service
public class SpecializationService {

	@Autowired
	private SpecializationRepository specializationRepository;

	public Specialization addSpecialization(Specialization specialization) {
		if(findSpecializationByName(specialization.getName()) != null)
			throw new InvalidDataException("Specialization already exists");
		return specializationRepository.save(specialization);
	}

	public List<Specialization> getAllSpecialization() {
		return specializationRepository.findAllByOrderByIdAsc();
	}

	public Specialization getSpecialization(long id) {
		return specializationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Specialization", "id", id));
	}
	
	public List<Specialization> getSpecializationNameLike(String name) {
		return specializationRepository.findByNameContaining(name);
	}
	
	public Specialization findSpecializationByName(String name) {
		return specializationRepository.findByName(name);
	}
	
	public List<SpecializationDto> findAllWithDoctorCount(String name) {
		return specializationRepository.findAllWithDoctorCount(name);
	}
	
	public void deleteSpecialization(long id) {
		getSpecialization(id);
		specializationRepository.deleteById(id);
	}
	
	public Specialization updateSpecialization(Specialization specialization) {
		getSpecialization(specialization.getId());
		return specializationRepository.save(specialization);
	}

}
