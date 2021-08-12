package com.covidpersona.entity;

public class ManagerRegistrationDTO {

	private Manager manager;
	private PersonaUser personaUser;

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public PersonaUser getPersonaUser() {
		return personaUser;
	}

	public void setPersonaUser(PersonaUser personaUser) {
		this.personaUser = personaUser;
	}

	@Override
	public String toString() {
		return "ManagerRegistrationDTO [manager=" + manager + ", personaUser=" + personaUser + "]";
	}

}
