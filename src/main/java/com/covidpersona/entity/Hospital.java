package com.covidpersona.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Hospital {

	@Id
	@GeneratedValue
	private int hId;

	@NotBlank
	@Column(nullable = false, unique = true)
	private String hName;
	
	@NotBlank
	@Column(nullable = false)
	private String location;
	
	@Column(nullable = false)
	private int noOfBeds;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "hospital_doctors", joinColumns = { @JoinColumn(name = "hospital_id") }, inverseJoinColumns = {
			@JoinColumn(name = "doctor_id") })
	@JsonIgnore
	private Set<Doctor> doctors = new HashSet<>();
	
	public void addDoctor(Doctor doctor) {
		this.doctors.add(doctor);
		doctor.getHospitals().add(this);
	}
	
	public void removeDoctor(Doctor doctor) {
		this.doctors.remove(doctor);
		doctor.getHospitals().remove(this);
	}
//
//	@OneToMany(mappedBy = "hospital")
//    Set<HospitalVaccine> hospitalVaccines;
//	
}
