package com.covidpersona.dto;

import com.covidpersona.entity.Person;
import com.covidpersona.entity.PersonaUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
	private PersonaUser personaUser;
	private Person person;
}
