package com.covidpersona.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covidpersona.entity.Receptionist;
import com.covidpersona.repository.ReceptionistRepository;

@Service
public class ReceptionistService {

	@Autowired
	private ReceptionistRepository receptionistRepository;
	
	public List<Receptionist> getReceptionist() {
		return receptionistRepository.findAll();
	}
	
	public Optional<Receptionist> getReceptionist(long id) {
		return receptionistRepository.findById(id);
	}
	
	public Receptionist addReceptionist(Receptionist receptionist) {
		Receptionist savedReceptionist = receptionistRepository.save(receptionist);
		return savedReceptionist;
	}
	
	public Receptionist updateReceptionist(Receptionist receptionist) {
		Receptionist updatedReceptionist = receptionistRepository.save(receptionist);
		return updatedReceptionist;
	}

	public void deleteReceptionist(long id) {
		receptionistRepository.deleteById(id);
	}
}
