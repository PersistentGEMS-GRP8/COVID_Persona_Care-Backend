package com.covidpersona.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;

@Entity
@Table(name = "patient", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@AllArgsConstructor
public class Patient extends Person {

	public Patient() {
		super();
	}

	@Column(name = "Appointment_No")
	@ColumnDefault("0")
	private long appointmentNO;

	@Column(name = "Vaccination_Status")
	private boolean vaccinationStatus;

	public long getAppointmentNO() {
		return appointmentNO;
	}

	public void setAppointmentNO(long appointmentNO) {
		this.appointmentNO = appointmentNO;
	}

	public boolean isVaccinationStatus() {
		return vaccinationStatus;
	}

	public void setVaccinationStatus(boolean vaccinationStatus) {
		this.vaccinationStatus = vaccinationStatus;
	}
}
