package com.covidpersona.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
public class HospitalVaccine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private int count;
	
	@Column(nullable = false)
	private int hospitalId;
	
	@Column(nullable = false)
	private int vaccineId;
	
//	@ManyToOne
//    @JoinColumn(name = "hospital_id")
//    Hospital hospital;
//	
//	@ManyToOne
//    @JoinColumn(name = "vaccine_id")
//    Vaccine vaccine;
}
