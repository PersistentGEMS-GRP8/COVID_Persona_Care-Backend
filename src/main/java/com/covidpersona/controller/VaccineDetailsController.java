package com.covidpersona.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class VaccineDetailsController {
	
	@Autowired
	private UserRepo userRepository;
	
	@PostMapping("add")
	public User add(@RequestBody User user)
	{
		return userRepository.save(user);
	}
	
	@GetMapping("users")
	public List<User> users()
	{
		return userRepository.findAll();
	}
	@GetMapping("user/{certificateNo}")
	public User user(@PathVariable(value="certificateNo") int certificateNo)
	{
		return userRepository.findById(certificateNo);
	}

}
