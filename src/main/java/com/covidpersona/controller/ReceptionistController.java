package com.covidpersona.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covidpersona.entity.Receptionist;
import com.covidpersona.service.ReceptionistService;

@RestController
@RequestMapping("/receptionists")
public class ReceptionistController {
	
	@Autowired
	private ReceptionistService receptionistService;
	
	@GetMapping
	public List<Receptionist> getReceptionist() {
		return receptionistService.getReceptionist();
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
	public ResponseEntity<Receptionist> addReceptionist(@RequestBody Receptionist receptionist) throws URISyntaxException {
		Receptionist savedReceptionist = receptionistService.addReceptionist(receptionist);
		return ResponseEntity.created(new URI("/receptionists/" + savedReceptionist.getId())).body(savedReceptionist);
	}

	@PutMapping
	public ResponseEntity<Receptionist> updateReceptionist(@RequestBody Receptionist receptionist) {
		Receptionist updatedReceptionist = receptionistService.updateReceptionist(receptionist);
		return ResponseEntity.ok(updatedReceptionist);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteReceptionist(@PathVariable long id) {
		receptionistService.deleteReceptionist(id);
		return ResponseEntity.ok().build();
	}

}
