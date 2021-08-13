package com.covidpersona.entity;

import javax.persistence.Entity;

@Entity
public class Patient extends Person{
	
	private int PatientId;

	public int getPatientId() {
		return PatientId;
	}

	public void setPatientId(int patientId) {
		PatientId = patientId;
	}

	@Override
	public String toString() {
		return "Patient [PatientId=" + PatientId + ", getId()=" + getId() + ", getName()=" + getName() + ", getEmail()="
				+ getEmail() + ", getContactNo()=" + getContactNo()+  ", getCreatedAt()=" + getCreatedAt()
				+ ", getUpdatedAt()=" + getUpdatedAt() + "]";
	}


}


