//package com.covidpersona.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.covidpersona.config.auth.JwtUtil;
//import com.covidpersona.entity.Appointment;
//import com.covidpersona.entity.Doctor;
//import com.covidpersona.entity.Patient;
//import com.covidpersona.repository.AppointmentRepository;
//import com.covidpersona.service.AppointmentService;
//import com.covidpersona.service.AppointmentServiceImpl;
//import com.covidpersona.service.DoctorService;
//import com.covidpersona.service.PatientService;
//
//@RestController
//public class AppointmentController {
//
//	@Autowired
//	private PatientService patientService;
//
//	@Autowired
//	private AppointmentService appointmentService;
//
//	@Autowired
//	private AppointmentRepository appointmentRepository;
//
//	@Autowired
//	private DoctorService docService;
//
//	// save appointment to database
//	@PostMapping("/saveAppointment/{patientId}")
//	public void saveAppointment(@RequestBody Appointment appointment, @PathVariable long patientId) {
//
//		Patient user = patientService.getPatientById(patientId);
//
//		appointment.setP_id(user.getId()); // To update patientId in the Appointment table
//		appointmentService.saveAppointment(appointment);
//		patientService.updatePatient(appointment.getA_id(), user.getId()); // To update AppointmentNo in the
//																						// Patient Table
//
//	}
//
//	// To view AppointmentDetails
//	@GetMapping("/viewAppointmentDetails/{patientId}")
//	public Appointment viewAppointmentDetails(@PathVariable long appId) {
//
//		return appointmentService.getAppointmentById(appId);
//
//	}
//
//	// Save updated appointment to database
//	/*
//	 * @PostMapping("/updateAppointment") public String
//	 * updateAppointment(@RequestBody Appointment appointment) {
//	 * 
//	 * Patient user =
//	 * patientService.getPatietByEmail(SecurityConfiguration.getLogUser());
//	 * 
//	 * appointmentRepository.updateAppointment(user.getId(), appointment.getDate(),
//	 * appointment.getDoctor().getD_id());
//	 * 
//	 * return "redirect:/"; }
//	 * 
//	 * // To delete an Appointment
//	 * 
//	 * @GetMapping("/deleteAppointment/{id}") public String
//	 * deleteAppointment(@PathVariable(value = "id") long id) {
//	 * 
//	 * Patient user =
//	 * patientService.getPatietByEmail(SecurityConfiguration.getLogUser());
//	 * 
//	 * appointmentRepository.deleteAppointment(user.getAppointmentNO());
//	 * patientService.updatePatientAppDetails(-1, user.getId());
//	 * 
//	 * return "redirect:/"; }
//	 */
//
//}
