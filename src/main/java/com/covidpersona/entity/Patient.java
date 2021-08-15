package com.covidpersona.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Patient {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String patientname;
    private String vaccinationType;
    private int doseNumber;
    private String Date;
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Patient(Long id, String patientname, String vaccinationType, int doseNumber, String date) {
		super();
		this.id = id;
		this.patientname = patientname;
		this.vaccinationType = vaccinationType;
		this.doseNumber = doseNumber;
		Date = date;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPatientname() {
		return patientname;
	}
	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}
	public String getVaccinationType() {
		return vaccinationType;
	}
	public void setVaccinationType(String vaccinationType) {
		this.vaccinationType = vaccinationType;
	}
	public int getDoseNumber() {
		return doseNumber;
	}
	public void setDoseNumber(int doseNumber) {
		this.doseNumber = doseNumber;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
    
	

}
