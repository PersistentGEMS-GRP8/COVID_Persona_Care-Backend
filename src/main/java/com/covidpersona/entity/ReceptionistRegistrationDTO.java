package com.covidpersona.entity;

public class ReceptionistRegistrationDTO {

	private Receptionist receptionist;
	private PersonaUser personaUser;

	public Receptionist getReceptionist() {
		return receptionist;
	}

	public void setReceptionist(Receptionist receptionist) {
		this.receptionist = receptionist;
	}

	public PersonaUser getPersonaUser() {
		return personaUser;
	}

	public void setPersonaUser(PersonaUser personaUser) {
		this.personaUser = personaUser;
	}

	@Override
	public String toString() {
		return "ReceptionistRegistrationDTO [receptionist=" + receptionist + ", personaUser=" + personaUser + "]";
	}
}
