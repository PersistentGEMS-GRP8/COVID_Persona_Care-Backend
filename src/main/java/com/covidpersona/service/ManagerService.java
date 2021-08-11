package com.covidpersona.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covidpersona.entity.Manager;
import com.covidpersona.repository.ManagerRepository;

@Service
public class ManagerService {

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
		Manager updatedManager = managerRepository.save(manager);
		return updatedManager;
	}

	public void deleteManager(long id) {
		managerRepository.deleteById(id);
	}
	
	public List<Manager> getManagersByHId(int hId){
		return managerRepository.findByhId(hId);
	}

}
