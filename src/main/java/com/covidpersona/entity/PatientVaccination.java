//Deepak
package com.covidpersona.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PatientVaccination {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int vaccineId;

	private int certificateNo;
	private String vaccineName;
	private String date;
	private int numberOfVaccine;
	private String hospitalName;

	public int getVaccineId() {
		return vaccineId;
	}

	public void setVaccineId(int vaccineId) {
		this.vaccineId = vaccineId;
	}

	public int getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(int certificateNo) {
		this.certificateNo = certificateNo;
	}

	public String getVaccineName() {
		return vaccineName;
	}

	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getNumberOfVaccine() {
		return numberOfVaccine;
	}

	public void setNumberOfVaccine(int numberOfVaccine) {
		this.numberOfVaccine = numberOfVaccine;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

}
