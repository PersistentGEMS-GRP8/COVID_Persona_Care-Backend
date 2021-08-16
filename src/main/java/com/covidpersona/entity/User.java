package com.covidpersona.entity;

import javax.persistence.Entity;

@Entity
public class User {
	private int certificateNo;
	private String VaccineName;
	private String Date;
	private int NumberOfVaccine;
	private String HospitalName;
	public int getCertificateNo() {
		return certificateNo;
	}
	public void setCertificateNo(int certificateNo) {
		this.certificateNo = certificateNo;
	}
	public String getVaccineName() {
		return VaccineName;
	}
	public void setVaccineName(String vaccineName) {
		VaccineName = vaccineName;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public int getNumberOfVaccine() {
		return NumberOfVaccine;
	}
	public void setNumberOfVaccine(int numberOfVaccine) {
		NumberOfVaccine = numberOfVaccine;
	}
	public String getHospitalName() {
		return HospitalName;
	}
	public void setHospitalName(String hospitalName) {
		HospitalName = hospitalName;
	}

}
