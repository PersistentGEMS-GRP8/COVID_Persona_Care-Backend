package com.covidpersona.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covidpersona.entity.Appointment;
import com.covidpersona.repository.AppointmentRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	// This function is useful when we implement Doctor part
	@Override
	public List<Appointment> getAllAppointment() {
		return appointmentRepository.findAll();
	}

	@Override
	public void saveAppointment(Appointment appointment) {
		this.appointmentRepository.save(appointment);
	}

	@Override
	public Appointment viewAppointmentDetails(long pid) {

		return appointmentRepository.findByP_id(pid);

	}

	@Override
	public Appointment getAppointmentById(long id) {
		Optional<Appointment> optional = appointmentRepository.findById(id);
		Appointment appointment = null;
		if (optional.isPresent()) {
			appointment = optional.get();
		} else {
			throw new RuntimeException(" Appointment not found for id :: " + id);
		}
		return appointment;
	}

}
