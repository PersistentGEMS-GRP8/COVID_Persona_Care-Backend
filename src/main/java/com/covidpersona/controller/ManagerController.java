package com.covidpersona.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covidpersona.dto.RegisterRequestDto;
import com.covidpersona.entity.Manager;
import com.covidpersona.service.ManagerService;
import com.covidpersona.service.auth.PersonaUserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/managers")
public class ManagerController {

	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private PersonaUserService personaUserService;

	@GetMapping
	public List<Manager> getManagers() {
		return managerService.getManagers();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getManager(@PathVariable long id) {
		Optional<Manager> manager = managerService.getManager(id);
		if (manager.isPresent()) {
			return ResponseEntity.ok(manager);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<Manager> addManager(@RequestBody Manager manager) {
		Manager savedManager = managerService.addManager(manager);
		return ResponseEntity.ok(savedManager);
	}

	@PutMapping
	public ResponseEntity<Manager> updateManager(@RequestBody Manager manager) {
		Manager updatedManager = managerService.updateManager(manager);
		return ResponseEntity.ok(updatedManager);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteManager(@PathVariable long id) {
		managerService.deleteManager(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/getByHId/{hId}")
	public List<Manager> getManagersByHId(@PathVariable int hId) {
		return managerService.getManagersByHId(hId);
	}
	
	@PostMapping("/register")
	public long registerManager(@RequestBody RegisterRequestDto dto) {
		return personaUserService.RegisterPersonaUser(dto.getPersonaUser(), dto.getPerson());
	}
}
