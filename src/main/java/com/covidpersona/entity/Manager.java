package com.covidpersona.entity;

import javax.persistence.Entity;

@Entity
public class Manager extends Person {

	private int hId;

	public int gethId() {
		return hId;
	}

	public void sethId(int hId) {
		this.hId = hId;
	}

	@Override
	public String toString() {
		return "Manager [hId=" + hId + ", getId()=" + getId() + ", getName()=" + getName() + ", getEmail()="
				+ getEmail() + ", getContactNo()=" + getContactNo() + ", getCreatedAt()=" + getCreatedAt()
				+ ", getUpdatedAt()=" + getUpdatedAt() + "]";
	}

	
}
