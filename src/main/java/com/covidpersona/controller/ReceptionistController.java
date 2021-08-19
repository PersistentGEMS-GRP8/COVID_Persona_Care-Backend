package com.covidpersona.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.covidpersona.entity.Receptionist;
import com.covidpersona.service.ReceptionistService;
import com.covidpersona.service.auth.PersonaUserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/receptionists")
public class ReceptionistController {
	
	private final Logger log = LoggerFactory.getLogger(ManagerController.class);
	
	@Autowired
	private ReceptionistService receptionistService;
	
	@Autowired
	private PersonaUserService personaUserService;
	
	@GetMapping
	public List<Receptionist> getReceptionist() {
		return receptionistService.getReceptionists();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getReceptionist(@PathVariable long id) {
		Optional<Receptionist> receptionist = receptionistService.getReceptionist(id);
		if (receptionist.isPresent()) {
			return ResponseEntity.ok(receptionist);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<Receptionist> addReceptionist(@RequestBody Receptionist receptionist) {
		log.info("Request to create receptionist: {}", receptionist);
		Receptionist savedReceptionist = receptionistService.addReceptionist(receptionist);
		return ResponseEntity.ok(savedReceptionist);
	}

	@PutMapping
	public ResponseEntity<Receptionist> updateReceptionist(@RequestBody Receptionist receptionist) {
		log.info("Request to update receptionist: {}", receptionist);
		Receptionist updatedReceptionist = receptionistService.updateReceptionist(receptionist);
		return ResponseEntity.ok(updatedReceptionist);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteReceptionist(@PathVariable long id) {
		log.info("Request to delete receptionist: {}", id);
		receptionistService.deleteReceptionist(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/getByHId/{hId}")
	public List<Receptionist> getReceptionistsByHId(@PathVariable int hId) {
		return receptionistService.getReceptionistsByHId(hId);
	}

	@PostMapping("/register")
	public long registerReceptionist(@RequestBody RegisterRequestDto dto) {
		log.info("Request to register receptionist: {}", dto.getPerson());
		return personaUserService.RegisterPersonaUser(dto.getPersonaUser(), dto.getPerson());
	}
}
