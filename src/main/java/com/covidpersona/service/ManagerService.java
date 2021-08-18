package com.covidpersona.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covidpersona.entity.Manager;
import com.covidpersona.exception.ResourceNotFoundException;
import com.covidpersona.repository.ManagerRepository;

@Service
public class ManagerService extends PersonService<Manager> {

	@Autowired
	private ManagerRepository managerRepository;

	public List<Manager> getManagers() {
		return managerRepository.findAll();
	}

	public Optional<Manager> getManager(long id) {
		return managerRepository.findById(id);
	}

	public Manager addManager(Manager manager) {
		Manager savedManager = managerRepository.save(manager);
		return savedManager;
	}

	public Manager updateManager(Manager manager) {
		Optional<Manager> existManager = getManager(manager.getId());
		manager.setUserId(existManager.get().getUserId());
		Manager updatedManager = managerRepository.save(manager);
		return updatedManager;
	}

	public void deleteManager(long id) {
		managerRepository.deleteById(id);
	}

	public List<Manager> getManagersByHId(int hId) {
		return managerRepository.findByhId(hId);
	}

	@Override
	public Manager getPersonByUserId(long id) {
		return managerRepository.findByUserId_Id(id)
				.orElseThrow(() -> new ResourceNotFoundException("Manager", "user_id", id));
	}

}
