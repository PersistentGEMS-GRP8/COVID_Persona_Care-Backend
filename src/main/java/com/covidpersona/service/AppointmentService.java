package com.covidpersona.service;

import java.util.List;

import com.covidpersona.entity.Appointment;

public interface AppointmentService {

	List<Appointment> getAllAppointment();

	void saveAppointment(Appointment appointment);

	public Appointment viewAppointmentDetails(long id);

	Appointment getAppointmentById(long id);

}
