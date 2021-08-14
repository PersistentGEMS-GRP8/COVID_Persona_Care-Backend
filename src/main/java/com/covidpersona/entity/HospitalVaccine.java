package com.covidpersona.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.ToString;

@Entity
@ToString
public class HospitalVaccine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private long count;
	
	@Column(nullable = false)
	private long hospitalId;
	
	@Column(nullable = false)
	private long vaccineId;
	
//	@ManyToOne
//    @JoinColumn(name = "hospital_id")
//    Hospital hospital;
//	
//	@ManyToOne
//    @JoinColumn(name = "vaccine_id")
//    Vaccine vaccine;
}
