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
	
	
}
