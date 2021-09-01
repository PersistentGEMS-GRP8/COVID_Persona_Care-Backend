package com.covidpersona.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covidpersona.entity.Receptionist;
import com.covidpersona.exception.ResourceNotFoundException;
import com.covidpersona.repository.ReceptionistRepository;

@Service
public class ReceptionistService extends PersonService<Receptionist> {

	@Autowired
	private ReceptionistRepository receptionistRepository;

	public List<Receptionist> getReceptionists() {
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
		Optional<Receptionist> existReceptionist = getReceptionist(receptionist.getId());
		receptionist.setUserId(existReceptionist.get().getUserId());
		Receptionist updatedReceptionist = receptionistRepository.save(receptionist);
		return updatedReceptionist;
	}

	public void deleteReceptionist(long id) {
		receptionistRepository.deleteById(id);
	}

	public List<Receptionist> getReceptionistsByHId(int hId) {
		return receptionistRepository.findByhId(hId);
	}

	@Override
	public Receptionist getPersonByUserId(long id) {
		return receptionistRepository.findByUserId_Id(id)
				.orElseThrow(() -> new ResourceNotFoundException("Receptionist", "user_id", id));
	}
}
