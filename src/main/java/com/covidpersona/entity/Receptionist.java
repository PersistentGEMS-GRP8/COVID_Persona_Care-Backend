package com.covidpersona.entity;

import javax.persistence.Entity;

@Entity
public class Receptionist extends Person {
	
	private int rId;

	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}
	
}
