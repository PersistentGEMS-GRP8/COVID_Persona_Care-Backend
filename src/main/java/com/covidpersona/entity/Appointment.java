package com.covidpersona.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "appointment")
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long a_id;

	@Column(name = "p_id")
	private long p_id;

	@ManyToOne
	@JoinColumn(name = "d_id", referencedColumnName = "id")
	public Doctor doctor;

	@Column(name = "Appdate")
	private String date;

//    @Column(name="Hospital_Name")
//    private String hospitalName;

	// @Column(name="Doctor_Name")
	// private String doctorName;

}
